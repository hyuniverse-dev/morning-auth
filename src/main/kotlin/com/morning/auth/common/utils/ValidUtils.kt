package com.morning.auth.common.utils

import com.morning.auth.common.constants.ValidationMessage
import org.apache.commons.lang3.StringUtils
import java.time.LocalDateTime
import java.util.*
import java.util.regex.Pattern


object ValidUtils {
    // LocatDatetime 형식
    fun validBetweenDate(beginDate: LocalDateTime?, endDate: LocalDateTime?) {
        if (beginDate == null || endDate == null) {
            return
        }
        if (beginDate.isAfter(endDate)) {
            throw IllegalArgumentException(ValidationMessage.START_DATE_CANNOT_BE_GREATER_THAN_END_DATE)
        }
    }

    // Date 형식
    fun validBetweenDate(beginDate: Date?, endDate: Date?) {
        if (beginDate == null || endDate == null) {
            return
        }
        if (beginDate.time - endDate.time > 0) {
            throw IllegalArgumentException(ValidationMessage.START_DATE_CANNOT_BE_GREATER_THAN_END_DATE)
        }
    }

    // YYYY-MM-DD or YYYYMMDD 형식
    fun validBetweenDate(beginDate: String, endDate: String) {
        if (StringUtils.isEmpty(beginDate) || StringUtils.isEmpty(endDate)) {
            return
        }
        if (beginDate.replace("-", "").toInt() - endDate.replace("-", "").toInt() > 0) {
            throw IllegalArgumentException(ValidationMessage.START_DATE_CANNOT_BE_GREATER_THAN_END_DATE)
        }
    }

    // IP Address 체크
    fun validIpAddress(ipAddress: String?) {
        if (StringUtils.isBlank(ipAddress)) {
            throw IllegalArgumentException(ValidationMessage.NOT_A_VALID_IP_ADDRESS)
        }
        val t = StringTokenizer(ipAddress, ".")
        val a = t.nextToken().toInt()
        val b = t.nextToken().toInt()
        val c = t.nextToken().toInt()
        val d = t.nextToken().toInt()

        if (a in 0..255 && b in 0..255 && c in 0..255 && d in 0..255) {
            // 정상적인 IP Address
        } else {
            throw IllegalArgumentException(ValidationMessage.NOT_A_VALID_IP_ADDRESS)
        }
    }

    /**
     * 비빌번호 검증
     * 비밀번호는 영문+숫자+특수문자 조합으로 9자~16자 이내로 사용
     * @param password
     */
    fun validPassword(password: String?): String? {
        val min = 9
        val max = 16

        // 영어, 숫자, 특수문자 포함한 MIN to MAX 글자 정규식
        val regex = "^((?=.*\\d)(?=.*[a-zA-Z])(?=.*[\\W]).{$min,$max})$"
        val result: Boolean = Pattern.matches(regex, password)
        if (!result) {
            throw IllegalArgumentException("비밀번호는 영문+숫자+특수문자 조합으로 9자~16자 이내로 조합해야 합니다.")
        }
        return password
    }
}

