package com.morning.auth.biz.back.manager.mapper

class PilotSqlProvider {

    companion object {
        const val DATETIME_PAGING_FILTER = """
        AND DATE(m.regist_datetime) BETWEEN COALESCE(NULLIF(#{searchFilter.fromDate}, ''), '1900-01-01') AND COALESCE(NULLIF(#{searchFilter.toDate}, ''), '2999-12-31')
        LIMIT #{size} OFFSET #{offset}
        """

        const val DATETIME_FILTER = """
        AND DATE(m.regist_datetime) BETWEEN COALESCE(NULLIF(#{searchFilter.fromDate}, ''), '1900-01-01') AND COALESCE(NULLIF(#{searchFilter.toDate}, ''), '2999-12-31')
        """

        const val PILOT_SEARCH_FILTER = """
        (CASE #{searchFilter.searchCode}
            WHEN '10' THEN m.name LIKE concat('%', #{searchFilter.searchKeyword}, '%')
            WHEN '20' THEN m.group_name LIKE concat('%', #{searchFilter.searchKeyword},'%')
            ELSE TRUE
           END)
        AND (CASE WHEN #{searchFilter.licenseGrade} = '' THEN TRUE ELSE md.license_grade = #{searchFilter.licenseGrade} END)
        AND md.manager_div = 'APL'
        """
    }

    fun registPilot(): String {
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

    fun registPilotDetail(): String {
        return """
        INSERT INTO morning_tracking.manager_detail
               ( manager_code, manager_div, special_ability, license_no, license_type, license_grade, training_course_yn
               , insurance_certificate, pilot_license, device_photo, device_spec, device_certificate
               , device_stability_certificate, device_business_license, register_code, regist_datetime, modifier_code, modify_datetime)
        VALUES (#{managerCode}, 'APL', #{specialAbility}, #{licenseNo}, #{licenseType}, #{licenseGrade}, #{trainingCourseYn}
               , #{insuranceCertificate}, #{pilotLicense}, #{devicePhoto}, #{deviceSpec}, #{deviceCertificate}
               , #{deviceStabilityCertificate}, #{deviceBusinessLicense}, #{registerCode}, now(), #{modifierCode}, now());
        """.trimIndent()
    }

    fun fetchPilotList(): String {
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
               md.special_ability,
               md.license_no,
               md.license_type,
               md.license_grade,
               md.training_course_yn,
               md.insurance_certificate,
               md.pilot_license,
               md.device_photo,
               md.device_spec,
               md.device_certificate,
               md.device_stability_certificate,
               md.device_business_license,
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
         WHERE $PILOT_SEARCH_FILTER
               $DATETIME_PAGING_FILTER
        """.trimIndent()
    }

    fun fetchTotalCount(): String {
        return """
            SELECT count(m.manager_code)
          FROM morning_auth.manager m
         INNER JOIN morning_tracking.manager_detail md ON m.manager_code = md.manager_code
         WHERE md.manager_div = 'APL'
        """.trimIndent()
    }

    fun fetchSearchCount(): String {
        return """
        SELECT count(m.manager_code)
          FROM morning_auth.manager m
         INNER JOIN morning_tracking.manager_detail md ON m.manager_code = md.manager_code
         WHERE $PILOT_SEARCH_FILTER
               $DATETIME_FILTER
        """.trimIndent()
    }

    fun fetchPilot(): String {
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
               md.special_ability,
               md.license_no,
               md.license_type,
               md.license_grade,
               md.training_course_yn,
               md.insurance_certificate,
               md.pilot_license,
               md.device_photo,
               md.device_spec,
               md.device_certificate,
               md.device_stability_certificate,
               md.device_business_license,
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

