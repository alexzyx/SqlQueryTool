package com.friend.sql_mana.service;

import com.alibaba.druid.util.JdbcUtils;
import com.friend.sql_mana.model.JdbcResultResp;
import com.friend.sql_mana.model.SchemaReq;
import com.friend.sql_mana.model.SchemaResp;
import com.friend.sql_mana.model.SqlVerifyReq;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.*;

@Service
public class SchemaService {

    @Autowired
    private DataSourceManager dataSourceManager;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public SchemaReq initSchemaReq = new SchemaReq();
    @Bean
    private void initDataBase() {
        initSchemaReq.setAddress("127.0.0.1");
        initSchemaReq.setPassword("123");
        initSchemaReq.setPort(3306);
        initSchemaReq.setSchema("febs_security");
        initSchemaReq.setUsername("root");
        dataSourceManager.initDataSource(initSchemaReq);
    }


    public void addSchema(SchemaReq schemaReq) {
        dataSourceManager.initDataSource(schemaReq);
    }

    public Map<String, List<String>> tablesAndSchemas(String schema) {
        Set<String> schemas = dataSourceManager.getDataSourceMap().keySet();
        Map<String, List<String>> items = new HashMap<>(2);
        if (StringUtils.isBlank(schema)) {
            items.put("tables", new ArrayList<>());
        }
        if (!StringUtils.isBlank(schema)) {
            items.put("tables", jdbcTemplate.allTablesInSchema(schema));
        }
        items.put("schemas", new ArrayList<>(schemas));
        return items;
    }

    public List<SchemaResp> getSchemaResps() {
        Map<String, DataSource> dataSourceMap = dataSourceManager.getDataSourceMap();
        List<SchemaResp> schemaResps = new ArrayList<>(dataSourceMap.size());
        dataSourceMap.forEach((key, value) -> {
            Map<String, List<String>> tableMaps = new HashMap<>();
            List<String> tables = jdbcTemplate.allTablesInSchema(key);
            for (String table : tables) {
                List<String> columns = jdbcTemplate.allColumnInTable(key, table);
                tableMaps.put(table, columns);
            }
            schemaResps.add(SchemaResp.builder().schema(key).tables(tableMaps).build());
        });
        return schemaResps;
    }

    public SchemaResp getSchemaResp(String schema) {
        List<String> tables = jdbcTemplate.allTablesInSchema(schema);
        Map<String, List<String>> tableMaps = new HashMap<>();
        for (String table : tables) {
            List<String> columns = jdbcTemplate.allColumnInTable(schema, table);
            tableMaps.put(table, columns);
        }
        return SchemaResp.builder().schema(schema).tables(tableMaps).build();
    }


    public JdbcResultResp executeDqlSql(SqlVerifyReq sqlVerifyReq) {
        Map<String, DataSource> dataSourceMap = dataSourceManager.getDataSourceMap();
        DataSource dataSource = dataSourceMap.get(sqlVerifyReq.getSchema());

        //todo 对敏感字段做脱敏查询
        JdbcResultResp jdbcResultResp = jdbcTemplate.executeSql(dataSource, sqlVerifyReq.getSql());
        return jdbcResultResp;
    }

    public void executeDmlSql(SqlVerifyReq sqlVerifyReq) throws SQLException {
        Map<String, DataSource> dataSourceMap = dataSourceManager.getDataSourceMap();
        DataSource dataSource = dataSourceMap.get(sqlVerifyReq.getSchema());

        JdbcUtils.execute(dataSource, sqlVerifyReq.getSql());

    }
}
