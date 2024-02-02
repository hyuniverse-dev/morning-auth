package com.morning.auth.biz.back.manager.mapper

class ManagerSqlProvider {

    companion object {
        const val ROLE_SEARCH_FILTER = """
        CASE #{searchFilter.searchCode}
            WHEN '10' THEN role_name like concat('%', #{searchFilter.searchKeyword}, '%')
            WHEN '20' THEN register_code in (SELECT m.register_code FROM morning_auth.manager m WHERE login_id like concat('%', #{searchFilter.searchKeyword}, '%'))
            ELSE TRUE
        END
        """

        const val ROLE_DATETIME_FILTER = """
            AND DATE(regist_datetime) BETWEEN COALESCE(NULLIF(#{searchFilter.fromDate}, ''), '1900-01-01') AND COALESCE(NULLIF(#{searchFilter.toDate}, ''), '2999-12-31')
        """
    }

    fun fetchRoleList(): String {
        return """
        SELECT role_code,
               role_name,
               regist_datetime,
               ( SELECT im.login_id
                   FROM morning_auth.manager im
                  WHERE im.manager_code = register_code
               ) register_id,
               modify_datetime
          FROM morning_auth.manager_role
         WHERE $ROLE_SEARCH_FILTER
               $ROLE_DATETIME_FILTER
         LIMIT #{size} OFFSET #{offset}
        """.trimIndent()
    }

    fun fetchRole(): String {
        return """
        SELECT role_code,
               role_name
        FROM morning_auth.manager_role
        """.trimIndent()
    }

    fun fetchRoleTotalCount(): String {
        return """
        SELECT count(role_code)
          FROM morning_auth.manager_role
        """.trimIndent()
    }

    fun fetchRoleSearchCount(): String{
        return """
        SELECT count(role_code)
          FROM morning_auth.manager_role
         WHERE $ROLE_SEARCH_FILTER
               $ROLE_DATETIME_FILTER
        """.trimIndent()
    }

    fun fetchGradeList(): String {
        return """
        SELECT code,
               language_code,
               code_name,
               sort_sequence,
               regist_datetime,
               modify_datetime
         FROM morning_tracking.common_code
        WHERE group_code = '10002'
        """.trimIndent()
    }

    fun fetchMenuList(): String {
        return """
        SELECT menu_no,
               menu_div,
               menu_name,
               menu_url,
               register_code,
               regist_datetime,
               modifier_code,
               modify_datetime
          FROM morning_auth.manager_menu u
        """.trimIndent()
    }

    fun registRole(): String {
        return """
        INSERT INTO morning_auth.manager_role
               (role_code, role_name, register_code, regist_datetime, modifier_code, modify_datetime)
        VALUES (#{roleCode}, #{roleName}, #{registerCode}, now(), #{modifierCode}, now())
        """.trimIndent()
    }

    fun registRoleMenu(): String {
        return """
        INSERT INTO morning_auth.role_apply_menu
               (role_code, menu_no, register_code, regist_datetime)
        VALUES (#{roleCode}, #{menuNo}, #{registerCode}, now());
        """.trimIndent()
    }
}
