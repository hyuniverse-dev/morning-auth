package com.morning.auth.biz.sample.mapper

import com.morning.auth.biz.sample.mapper.SampleSQLProvider.compaion.DATE_FILTER

class SampleSQLProvider {

    object compaion {
        const val DATE_FILTER =
            """
            AND IF(#{searchFilterDTO.fromDate} is not null, regist_date >= #{searchFilterDTO.fromDate}, TRUE)
            AND IF(#{searchFilterDTO.toDate} is not null, regist_date <= #{searchFilterDTO.toDate}, TRUE)
            """
    }

    fun registSample(): String {
        return """
            INSERT INTO morning_auth.sample
                   (sample_name)
            VALUES (#{reqSample.sampleName})
        """.trimIndent()
    }

    fun fetchSampleList(): String {
        return """
            SELECT sample_name, sample_no
              FROM morning_auth.sample
             WHERE IF(#{searchFilterDTO.sampleName} != '', sample_name LIKE  concat('%',#{searchFilterDTO.sampleName},'%'), TRUE)
                   $DATE_FILTER
             LIMIT #{size} OFFSET #{offset}
        """.trimIndent()
    }

    fun fetchSample(): String {
        return """
            SELECT *
              FROM morning_auth.sample
             WHERE sample_no = #{sampleNo}
        """.trimIndent()
    }

    fun fetchSampleByDTO(): String {
        return """
            SELECT sample_name, sample_no
              FROM morning_auth.sample
             WHERE sample_name = #{reqSample.sampleName}
        """.trimIndent()
    }

    fun modifySample(): String {
        return """
            UPDATE morning_auth.sample
               SET sample_name = #{reqSample.sampleName}
             WHERE sample_no = #{sampleNo}
         """.trimIndent()
    }

    fun removeSample(): String {
        return """
            DELETE FROM morning_auth.sample
             WHERE sample_no = #{sampleNo}
        """.trimIndent()
    }

}

