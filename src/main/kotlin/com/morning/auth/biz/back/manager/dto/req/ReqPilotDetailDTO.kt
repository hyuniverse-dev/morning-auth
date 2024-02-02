package com.morning.auth.biz.back.manager.dto.req

import com.fasterxml.jackson.annotation.JsonIgnore
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern

class ReqPilotDetailDTO(
    @Schema(description = "특기사항", nullable = true, type = "String", example = "write your special ability")
    var specialAbility: String?,

    @Schema(description = "자격번호", nullable = true, type = "String", example = "write your license number")
    @field: NotBlank(message = "{licenseNo.not.blank}")
    var licenseNo: String?,

    @Schema(
        description = "자격증 종류[무인비행기(DAP), 무인헬리콥터(DHC), 무인멀티콥터(DMC)]",
        type = "String",
        nullable = true,
        example = "DAP"
    )
    @field: Pattern(regexp = "DAP|DHC|DMC", message = "{licenseType.pattern}")
    var licenseType: String?,

    @Schema(
        description = "자격증 등급[1종(TP1), 2종(TP2), 3종(TP3), 4종(TP4)]",
        type = "String",
        nullable = true,
        example = "TP1"
    )
    @field:Pattern(regexp = "TP1|TP2|TP3|TP4", message = "{licenseGrade.pattern}")
    var licenseGrade: String?,

    @Schema(description = "전문교육이수여부[이수(Y), 미이수(N)]", type = "String", nullable = true, example = "Y")
    @field: Pattern(regexp = "Y|N", message = "{trainingCourseYn.pattern}")
    var trainingCourseYn: String?,

    @Schema(description = "보험가입증명서", type = "String", nullable = true, example = "https://...")
    var insuranceCertificate: String?,

    @Schema(description = "조종자격증명서", type = "String", nullable = true, example = "https://...")
    var pilotLicense: String?,

    @Schema(description = "초경량비행장치사진", type = "String", nullable = true, example = "https://...")
    var devicePhoto: String?,

    @Schema(description = "초경량비행장치 제원 및 성능", type = "String", nullable = true, example = "https://...")
    var deviceSpec: String?,

    @Schema(description = "초경량비행장치 신고증명서", type = "String", nullable = true, example = "https://...")
    var deviceCertificate: String?,

    @Schema(description = "초경량비행장치 안정성 증명서", type = "String", nullable = true, example = "https://...")
    var deviceStabilityCertificate: String?,

    @Schema(description = "초경량비행장치 사용 사업자 등록증", type = "String", nullable = true, example = "https://...")
    var deviceBusinessLicense: String?
) {
    @JsonIgnore
    var managerCode: String = ""

    //Todo: 화면으로부터 접속중인 관리자 코드로 교체
    @JsonIgnore
    var registerCode: String = ""

    //Todo: 화면으로부터 접속중인 관리자 코드로 교체
    @JsonIgnore
    var modifierCode: String = ""
}
