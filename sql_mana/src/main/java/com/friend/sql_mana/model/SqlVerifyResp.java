package com.friend.sql_mana.model;

import lombok.*;

/**
 * sql审核对象
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SqlVerifyResp {
    private String verifyInfo;
}
