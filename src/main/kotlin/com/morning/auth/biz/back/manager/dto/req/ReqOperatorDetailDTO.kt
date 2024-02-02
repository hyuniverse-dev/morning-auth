package com.morning.auth.biz.back.manager.dto.req

import com.fasterxml.jackson.annotation.JsonIgnore
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank


class ReqOperatorDetailDTO(
    @Schema(description = "관리자 코드", hidden = true)
    var managerCode: String?,

    @Schema(description = "운영자명", nullable = false, example = "운영자옥순이")
    @field: NotBlank(message = "{managerName.not.blank}")
    var managerName: String,

    @Schema(description = "핸드폰", nullable = false, example = "010-1234-1234")
    @field: NotBlank(message = "{phone.not.blank}")
    var phone: String,

    @Schema(description = "이메일", nullable = false, example = "operator@mncf.io")
    @field: NotBlank(message = "{email.not.blank}")
    var email: String,

    @Schema(description = "소속", nullable = false, example = "(주)모닝커피소프트")
    @field: NotBlank(message = "{groupName.not.blank}")
    var groupName: String,
) {
    //Todo: 화면으로부터 접속중인 관리자 코드로 교체
    @JsonIgnore
    var registerCode: String = ""

    //Todo: 화면으로부터 접속중인 관리자 코드로 교체
    @JsonIgnore
    var modifierCode: String = ""


    init {
        this.managerName = managerName.trim()
        this.phone = phone.trim().replace("-", "")
        this.groupName = groupName.trim()
        this.email = email.trim()
    }
}
