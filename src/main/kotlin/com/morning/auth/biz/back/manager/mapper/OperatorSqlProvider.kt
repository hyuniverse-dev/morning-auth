package com.morning.auth.biz.back.manager.mapper

class OperatorSqlProvider {

    companion object {
        const val DATETIME_PAGING_FILTER = """
        AND DATE(m.regist_datetime) BETWEEN COALESCE(NULLIF(#{searchFilter.fromDate}, ''), '1900-01-01') AND COALESCE(NULLIF(#{searchFilter.toDate}, ''), '2999-12-31')
        LIMIT #{size} OFFSET #{offset}
        """

        const val DATETIME_FILTER = """
        AND DATE(m.regist_datetime) BETWEEN COALESCE(NULLIF(#{searchFilter.fromDate}, ''), '1900-01-01') AND COALESCE(NULLIF(#{searchFilter.toDate}, ''), '2999-12-31')
        """

        const val OPERATOR_SEARCH_FILTER = """
        (CASE #{searchFilter.searchCode}
            WHEN '10' THEN m.name LIKE concat('%', #{searchFilter.searchKeyword}, '%')
            WHEN '20' THEN m.phone LIKE concat('%', #{searchFilter.searchKeyword},'%')
            ELSE TRUE
           END)
        AND m.service_div = 'BO'
        """
    }

    fun registOperator(): String {
        return """
        INSERT INTO morning_auth.manager
               ( manager_code, login_id, password, use_yn, service_div, role_code, temporary_password_yn, name, phone, email, birth_date
               , group_name, nationality_code, address, register_code, regist_datetime, modifier_code
               , modify_datetime)
        VALUES (#{managerCode}, #{loginId}, #{password}, #{useYn}, 'BO', #{roleCode}, #{temporaryPasswordYn}, #{name}, #{phone}, #{email}, #{birthDate}
               , #{groupName}, #{nationalityCode}, #{address}, #{registerCode}, now(), #{modifierCode}
               , now())
        """.trimIndent()
    }

    fun fetchOperatorList(): String {
        return """
        SELECT m.manager_code,
               m.login_id,
               m.use_yn,
               m.service_div,
               m.role_code,
               (SELECT role_name
                  FROM morning_auth.manager_role mr
                 WHERE mr.role_code = m.role_code) role_name,
               m.temporary_password_yn,
               m.name,
               m.phone,
               m.email,
               m.birth_date,
               m.nationality_code,
               m.address,
               (SELECT im.login_id
                  FROM morning_auth.manager im
                 WHERE im.manager_code = m.register_code) register_id,
               m.regist_datetime,
               (SELECT im.login_id
                  FROM morning_auth.manager im
                 WHERE im.manager_code = m.modifier_code) modifier_id,
               m.modify_datetime
          FROM morning_auth.manager m
         WHERE $OPERATOR_SEARCH_FILTER
               $DATETIME_PAGING_FILTER
        """.trimIndent()
    }

    fun fetchTotalCount(): String {
        return """
        SELECT count(m.manager_code)
          FROM morning_auth.manager m
         WHERE m.service_div = 'BO'
        """.trimIndent()
    }

    fun fetchSearchCount(): String {
        return """
        SELECT count(m.manager_code)
          FROM morning_auth.manager m
         WHERE $OPERATOR_SEARCH_FILTER
               $DATETIME_FILTER
        """.trimIndent()
    }

    fun fetchOperator(): String {
        return """
        SELECT manager_code,
               login_id,
               use_yn,
               service_div,
               role_code,
               (SELECT role_name
                  FROM morning_auth.manager_role mr
                 WHERE mr.role_code = m.role_code) role_name,
               temporary_password_yn,
               name,
               phone,
               email,
               birth_date,
               nationality_code,
               address,
               (SELECT im.login_id
                  FROM morning_auth.manager im
                 WHERE im.manager_code = m.register_code) register_id,
               regist_datetime,
               (SELECT im.login_id
                  FROM morning_auth.manager im
                 WHERE im.manager_code = m.modifier_code) modifier_id,
               modify_datetime
          FROM morning_auth.manager m
         WHERE manager_code = #{managerCode}
        """.trimIndent()
    }

    fun fetchPasswordById(): String {
        return """
        SELECT password
          FROM morning_auth.manager
         WHERE login_id = #{loginId}
    """.trimIndent()
    }

    fun fetchOperatorById(): String {
        return """
        SELECT manager_code,
               login_id,
               use_yn,
               service_div,
               role_code,
               (SELECT role_name
                  FROM morning_auth.manager_role mr
                 WHERE mr.role_code = m.role_code) role_name,
               temporary_password_yn,
               name,
               phone,
               email,
               birth_date,
               nationality_code,
               address,
               (SELECT im.login_id
                  FROM morning_auth.manager im
                 WHERE im.manager_code = m.register_code) register_id,
               regist_datetime,
               (SELECT im.login_id
                  FROM morning_auth.manager im
                 WHERE im.manager_code = m.modifier_code) modifier_id,
               modify_datetime
          FROM morning_auth.manager m
         WHERE m.login_id = #{loginId}
        """.trimIndent()
    }
}
