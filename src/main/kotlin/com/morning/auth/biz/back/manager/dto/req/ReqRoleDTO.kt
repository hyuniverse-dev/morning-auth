package com.morning.auth.biz.back.manager.dto.req

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import com.morning.auth.common.utils.generateUUID
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty

data class ReqRoleDTO(
    @field: NotBlank(message = "{roleName.not.blank}")
    @Schema(description = "권한명", type = "String", nullable = false, example = "운영자권한-팀장")
    var roleName: String = "",

    @field:NotEmpty(message = "{menus.not.empty}")
    @Schema(description = "메뉴번호 리스트", type = "List", nullable = false, example = "[1, 2]")
    val menus: Set<Int> = setOf(),
) {
    @JsonIgnore
    private val roleCode: String = generateUUID()

    //Todo: 화면으로부터 접속중인 관리자 코드로 대체
    @JsonIgnore
    private val registerCode: String = "e3697962871846e79d552a0fa2c78e08"

    // Todo: 화면으로부터 접속중인 관리자 코드로 대체
    @JsonIgnore
    private val modifierCode: String = "e3697962871846e79d552a0fa2c78e08"

    @JsonIgnore
    var menuNo: Int = 0

    init {
        this.roleName = roleName.trim()
    }
}
