package com.friend.sql_mana.controller;

import com.friend.sql_mana.model.*;
import com.friend.sql_mana.service.SchemaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@Api(value="schema相关api")
public class SchemaController {

    @Autowired
    private SchemaService schemaService;

    @ApiOperation(value="展示首页信息value", notes = "展示首页信息notes")
    @PostMapping(value = "/schemas")
    public ResponseEntity<GenericResponse> addSchema(@RequestBody @Valid SchemaReq schemaReq, BindingResult result) {
        schemaService.addSchema(schemaReq);
        return ResponseEntity.ok(GenericResponse.SUCCESS);
    }

    @GetMapping(value = "/schemas/{schema}")
    public List<SchemaResp> allTablesBySchema(@PathVariable("schema") String shcema, Model model) {
        SchemaResp schemaResp = schemaService.getSchemaResp(shcema);
        return Collections.singletonList(schemaResp);
    }

    @GetMapping(value = "/schemas/all")
    public List<SchemaResp> allSchemas(Model model) {
        List<SchemaResp> schemaResps = schemaService.getSchemaResps();
        return schemaResps;
    }

    @PostMapping(value = "/schemas/tables/sql::execute")
    public JdbcResultResp executeSql(@RequestBody @Valid SqlVerifyReq sqlVerifyReq, BindingResult result) {
        return schemaService.executeSql(sqlVerifyReq);
    }

    @ResponseBody
    @GetMapping(value = "/schemas/tables/{schema}")
    public Map<String, List<String>> getablesBySchema(@PathVariable("schema") String schema) {
        return schemaService.tablesAndSchemas(schema);
    }

}
