package com.morning.auth.biz.front.manager.mapper

class MonitorSqlProvider {

    fun fetchPassword(): String{
        return """
        SELECT password
          FROM morning_auth.manager
         WHERE login_id = #{loginId}
        """.trimIndent()
    }

    fun fetchManagerById(): String{
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
               m.group_name,
               (SELECT im.login_id
                  FROM morning_auth.manager im
                 WHERE im.manager_code = m.register_code) register_id,
               m.regist_datetime,
               (SELECT im.login_id
                  FROM morning_auth.manager im
                 WHERE im.manager_code = m.modifier_code) modifier_id,
               m.modify_datetime
          FROM morning_auth.manager m
         WHERE m.login_id = #{loginId}
        """.trimIndent()
    }

    fun fetchManagerByCode(): String{
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
               m.group_name,
               (SELECT im.login_id
                  FROM morning_auth.manager im
                 WHERE im.manager_code = m.register_code) register_id,
               m.regist_datetime,
               (SELECT im.login_id
                  FROM morning_auth.manager im
                 WHERE im.manager_code = m.modifier_code) modifier_id,
               m.modify_datetime
          FROM morning_auth.manager m
         WHERE m.manager_code = #{managerCode}
        """.trimIndent()
    }
}
