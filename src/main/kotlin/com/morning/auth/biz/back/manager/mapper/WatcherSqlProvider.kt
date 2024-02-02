package com.morning.auth.biz.back.manager.mapper

class WatcherSqlProvider {

    companion object {
        const val DATETIME_PAGING_FILTER = """
        AND DATE(m.regist_datetime) BETWEEN COALESCE(NULLIF(#{searchFilter.fromDate}, ''), '1900-01-01') AND COALESCE(NULLIF(#{searchFilter.toDate}, ''), '2999-12-31')
        LIMIT #{size} OFFSET #{offset}
        """

        const val DATETIME_FILTER = """
        AND DATE(m.regist_datetime) BETWEEN COALESCE(NULLIF(#{searchFilter.fromDate}, ''), '1900-01-01') AND COALESCE(NULLIF(#{searchFilter.toDate}, ''), '2999-12-31')
        """

        const val WATCHER_SEARCH_FILTER = """
        (CASE #{searchFilter.searchCode}
            WHEN '10' THEN m.name LIKE concat('%', #{searchFilter.searchKeyword}, '%')
            WHEN '20' then m.phone LIKE concat('%', #{searchFilter.searchKeyword},'%')
            ELSE TRUE
           END)
        AND md.manager_div = 'AWC'
        """
    }

    fun registWatcher(): String {
        return """
        INSERT INTO morning_auth.manager
               ( manager_code, login_id, password, use_yn, service_div, role_code, temporary_password_yn, name, phone, email, birth_date
               , group_name, nationality_code, address, register_code, regist_datetime, modifier_code
               , modify_datetime)
        VALUES (#{managerCode}, #{loginId}, #{password}, #{useYn}, #{serviceDiv}, #{roleCode}, #{temporaryPasswordYn}, #{name}, #{phone}, #{email}, #{birthDate}
               , #{groupName}, #{nationalityCode}, #{address}, #{registerCode}, now(), #{modifierCode}
               , now())
        """.trimIndent()
    }

    fun registWatcherDetail(): String {
        return """
        INSERT INTO morning_tracking.manager_detail
               ( manager_code, manager_div, register_code, regist_datetime, modifier_code, modify_datetime)
        VALUES (#{managerCode}, 'AWC', #{registerCode}, now(), #{modifierCode}, now());
        """.trimIndent()
    }

    fun fetchWatcherList(): String {
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
               md.manager_div,
               (SELECT im.login_id
                  FROM morning_auth.manager im
                 WHERE im.manager_code = m.register_code) register_id,
               m.regist_datetime,
               (SELECT im.login_id
                  FROM morning_auth.manager im
                 WHERE im.manager_code = m.modifier_code) modifier_id,
               m.modify_datetime
          FROM morning_auth.manager m
         INNER JOIN morning_tracking.manager_detail md ON m.manager_code = md.manager_code
         WHERE $WATCHER_SEARCH_FILTER
               $DATETIME_PAGING_FILTER
        """.trimIndent()
    }

    fun fetchTotalCount(): String {
        return """
        SELECT count(m.manager_code)
          FROM morning_auth.manager m
         INNER JOIN morning_tracking.manager_detail md ON m.manager_code = md.manager_code
         WHERE md.manager_div = 'AWC'
        """.trimIndent()
    }

    fun fetchSearchCount(): String {
        return """
        SELECT count(m.manager_code)
          FROM morning_auth.manager m
         INNER JOIN morning_tracking.manager_detail md ON m.manager_code = md.manager_code
         WHERE $WATCHER_SEARCH_FILTER
               $DATETIME_FILTER
        """.trimIndent()
    }

    fun fetchWatcher(): String {
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
               md.manager_div,
               (SELECT im.login_id
                  FROM morning_auth.manager im
                 WHERE im.manager_code = m.register_code) register_id,
               m.regist_datetime,
               (SELECT im.login_id
                  FROM morning_auth.manager im
                 WHERE im.manager_code = m.modifier_code) modifier_id,
               m.modify_datetime
          FROM morning_auth.manager m
         INNER JOIN morning_tracking.manager_detail md ON m.manager_code = md.manager_code
         WHERE m.manager_code = #{managerCode}
        """.trimIndent()
    }
}
