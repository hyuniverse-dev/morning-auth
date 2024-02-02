package com.morning.auth.biz.back.manager.dto.res

import com.fasterxml.jackson.annotation.JsonInclude
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import java.time.LocalDate
import java.time.LocalDateTime

@JsonInclude(JsonInclude.Include.NON_EMPTY)
data class ResOperatorDTO(
    @Schema(description = "관리자코드", type = "String")
    val managerCode: String,

    @Schema(description = "로그인아이디", type = "String")
    val loginId: String,

    @Schema(description = "사용여부 : Y(사용),N(미사용)", nullable = false, example = "Y")
    val useYn: String,

    @Schema(description = "서비스 구분[BO, PO, FO]", nullable = false, example = "BO")
    val serviceDiv: String,

    @Schema(description = "권한 코드", type = "String")
    val roleCode: String,

    @Schema(description = "권한명", type = "String")
    val roleName: String,

    @Schema(description = "운영자명", type = "String")
    val name: String,

    @Schema(description = "전화번호", type = "String")
    val phone: String,

    @Schema(description = "이메일", nullable = false, example = "operator@mncf.io")
    val email: String,

    @Schema(description = "생년월일", nullable = false, example = "1999-12-25")
    var birthDate: LocalDate?,

    @Schema(description = "국적코드", nullable = false, example = "KR")
    var nationalityCode: String?,

    @Schema(description = "주소", nullable = false, example = "서울특별시 서초구 명달로 116")
    @field: NotBlank(message = "{address.not.blank}")
    var address: String?,

    @Schema(description = "등록자아이디", type = "String")
    val registerId: String,

    @Schema(description = "등록일시", type = "LocalDateTime")
    val registDatetime: LocalDateTime,

    @Schema(description = "수정자아이디", type = "String")
    val modifierId: String,

    @Schema(description = "수정일시", type = "LocalDateTime")
    val modifyDatetime: LocalDateTime,

    @Schema(description = "임시비밀번호 사용여부", type = "String")
    val temporaryPasswordYn: String
)
