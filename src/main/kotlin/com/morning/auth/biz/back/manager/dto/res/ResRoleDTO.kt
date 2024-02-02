package com.morning.auth.biz.back.manager.dto.res

import com.fasterxml.jackson.annotation.JsonInclude
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

@JsonInclude(JsonInclude.Include.NON_NULL)
data class ResRoleDTO(
    @Schema(description = "권한코드", type = "string")
    val roleCode: String,

    @Schema(description = "권한명", type = "string")
    val roleName: String
){
    @Schema(description = "등록일자", type = "LocalDateTime")
    val registDatetime: LocalDateTime? = null

    @Schema(description = "등록자", type = "string")
    val registerId: String? = null

    @Schema(description = "수정일자", type = "LocalDateTime")
    val modifyDateTime: LocalDateTime? = null
}
