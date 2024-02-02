package com.morning.auth.biz.back.manager.dto.res

import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDate
import java.time.LocalDateTime

data class ResPilotDTO(
    @Schema(description = "관리자코드", type = "String")
    val managerCode: String,

    @Schema(description = "로그인아이디", type = "String")
    val loginId: String,

    @Schema(description = "사용여부[사용(Y), 미사용(N)]", type = "String")
    val useYn: String,

    @Schema(description = "서비스구분[BO, PO, FO]", type = "String")
    val serviceDiv: String,

    @Schema(description = "권한코드", type = "String")
    val roleCode: String,

    @Schema(description = "권한명", type = "String")
    val roleName: String,

    @Schema(description = "임시비밀번호 사용여부[사용(Y), 미사용(N)]", type = "String")
    val temporaryPasswordYn: String,

    @Schema(description = "파일럿명", type = "String")
    val name: String,

    @Schema(description = "휴대폰번호", type = "String")
    val phone: String,

    @Schema(description = "이메일", type = "String")
    val email: String,

    @Schema(description = "생년워링ㄹ", type = "String")
    val birthDate: LocalDate?,

    @Schema(description = "국적코드", type = "String")
    val nationalityCode: String?,

    @Schema(description = "주소", type = "String")
    val address: String?,

    @Schema(description = "소속명", type = "String")
    val groupName: String?,

    @Schema(description = "권한구분 [슈퍼관리자(AMA), 운영자(AOP), 관제사(AWC), 파일럿(APL)]", type = "String")
    val managerDiv: String,

    @Schema(description = "특기사항", type = "String")
    val specialAbility: String?,

    @Schema(description = "자격번호", type = "String")
    val licenseNo: String?,

    @Schema(description = "자격증 종류 [무인비행기(DAP), 무인헬리콥터(DHC), 무인멀티콥터(DMC)]", type = "String")
    val licenseType: String?,

    @Schema(description = "자격증 등급 [1종(TP1), 2종(TP2), 3종(TP3), 4종(TP4)]", type = "String")
    val licenseGrade: String?,

    @Schema(description = "전문교육이수여부[이수(Y), 미이수(N)]", type = "String")
    val trainingCourseYn: String?,

    @Schema(description = "보험가입증명서", type = "String")
    val insuranceCertificate: String?,

    @Schema(description = "조장자자격증명서", type = "String")
    val pilotLicense: String?,

    @Schema(description = "초경량비행장치 사진", type = "String")
    val devicePhoto: String?,

    @Schema(description = "초경량비행장치 제원 및 성능", type = "String")
    val deviceSpec: String?,

    @Schema(description = "초경량비행장치 신고증명서", type = "String")
    val deviceCertificate: String?,

    @Schema(description = "초경량비행장치 안정성 증명서", type = "String")
    val deviceStabilityCertificate: String?,

    @Schema(description = "초경량비행장치 사용 사업자 등록증", type = "String")
    val deviceBusinessLicense: String?,

    @Schema(description = "등록자 아이디", type = "String")
    val registerId: String?,

    @Schema(description = "등록일시", type = "LocalDateTime")
    val registDatetime: LocalDateTime,

    @Schema(description = "수정자 아이디", type = "String")
    val modifierId: String,

    @Schema(description = "수정일시", type = "LocalDateTime")
    val modifyDatetime: LocalDateTime,
)

