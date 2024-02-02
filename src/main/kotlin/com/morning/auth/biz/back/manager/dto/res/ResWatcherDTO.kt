package com.morning.auth.biz.back.manager.dto.res

import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate
import java.time.LocalDateTime

data class ResWatcherDTO(
    @Schema(description = "관리자코드", type = "String")
    val managerCode: String,

    @Schema(description = "로그인아이디", type = "String")
    val loginId: String?,

    @Schema(description = "사용여부 : Y(사용),N(미사용)", type = "String")
    val useYn: String?,

    @Schema(description = "서비스구분[BO, PO, FO]", type = "String")
    val serviceDiv: String,

    @Schema(description = "접근권한", type = "String")
    val roleCode: String?,

    @Schema(description = "권한명", type = "String")
    val roleName: String,

    @Schema(description = "임시비밀번호 사용여부[사용(Y), 미사용(N)]", type = "String")
    val temporaryPasswordYn: String,

    @Schema(description = "관제사명", type = "String")
    val name: String?,

    @Schema(description = "휴대폰번호", type = "String")
    val phone: String?,

    @Schema(description = "이메일", type = "String")
    val email: String?,

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Schema(description = "생년월일", type = "String")
    val birthDate: LocalDate?,

    @Schema(description = "국적코드", type = "String")
    val nationalityCode: String?,

    @Schema(description = "주소", type = "String")
    val address: String?,

    @Schema(description = "소속명", type = "String")
    val groupName: String?,

    @Schema(description = "권한구분 [슈퍼관리자(AMA), 운영자(AOP), 관제사(AWC), 파일럿(APL)]", type = "String")
    val managerDiv: String,

    @Schema(description = "등록자 아이디", type = "String")
    val registerId: String,

    @Schema(description = "등록일시", type = "String")
    val registDatetime: LocalDateTime,

    @Schema(description = "수정자 아이디", type = "String")
    val modifierId: String,

    @Schema(description = "수정일시", type = "String")
    val modifyDatetime: LocalDateTime
)
