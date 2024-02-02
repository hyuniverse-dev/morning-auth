package com.morning.auth.common.model

import com.fasterxml.jackson.annotation.JsonInclude
import io.swagger.v3.oas.annotations.media.Schema

@JsonInclude(JsonInclude.Include.NON_NULL)
class PagingResultVO(
    @Schema(description = "페이징정보", type = "Object")
    var paginationInfo: PaginationInfo? = null,

    @Schema(description = "추가정보", type = "Object")
    var more: Any? = null,

    @Schema(description = "결과값", type = "Object")
    var result: Any? = null
) {
    constructor(paginationInfo: PaginationInfo?, result: Any?) : this() {
        this.paginationInfo = paginationInfo
        this.result = result
    }
}
