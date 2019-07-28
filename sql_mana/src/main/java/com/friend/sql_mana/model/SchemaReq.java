package com.friend.sql_mana.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class SchemaReq {

    @NotBlank(message = "数据库地址不能为空")
    private String address;
    @NotBlank(message = "数据库不能为空")
    private String schema;
    @NotBlank(message = "用户名不能为空")
    private String username;
    @NotNull(message = "密码不能为空")
    private String password;
    @NotNull(message = "端口不能为空")
    private Integer port;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return "SchemaReq{" +
                "address='" + address + '\'' +
                ", schema='" + schema + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", port=" + port +
                '}';
    }
}
