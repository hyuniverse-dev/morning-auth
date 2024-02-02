package com.morning.auth.biz.sample.dto.req

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Min


data class ReqSampleSearchFilterDTO(
    @Schema(description = "샘플명", nullable = true, type = "String")
    var sampleName: String? = "",

    @Min(value = 1, message = "{page.min}")
    @Schema(description = "페이지 번호", type = "int")
    var page: Int,

    @Min(value = 1, message = "{size.min}")
    @Schema(description = "페이지당 행 개수", type = "int")
    var size: Int,

    @Schema(description = "검색시작일", type = "LocalDateTime")
    var fromDate: String?,

    @Schema(description = "검색종료일", type = "LocalDateTime")
    var toDate: String?
) {
    init {
        if (fromDate != null && toDate != null) {
            this.fromDate = fromDate + " 00:00:00"
            this.toDate = toDate + " 23:59:59"
        }
    }
}
