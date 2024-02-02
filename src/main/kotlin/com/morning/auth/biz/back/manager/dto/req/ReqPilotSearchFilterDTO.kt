package com.morning.auth.biz.back.manager.dto.req

import com.fasterxml.jackson.annotation.JsonIgnore
import com.morning.auth.common.utils.ValidUtils
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Min
import org.springframework.format.annotation.DateTimeFormat

data class ReqPilotSearchFilterDTO(
    @field: Min(value = 1, message = "{page.min}")
    @Schema(description = "페이지 번호", type = "int", example = "1")
    var page: Int = 0,

    @field: Min(value = 1, message = "{size.min}")
    @Schema(description = "페이지당 행 개수", type = "int", example = "10")
    var size: Int = 0,

    @JsonIgnore
    var offset: Int = 0,

    @field: DateTimeFormat(pattern = "yyyy-MM-dd")
    @Schema(description = "검색시작일 (YYYY-MM-DD)", type = "String", nullable = true, example = "2023-10-01")
    var fromDate: String? = "",

    @field: DateTimeFormat(pattern = "yyyy-MM-dd")
    @Schema(description = "검색종료일 (YYYY-MM-DD)", type = "String", nullable = true, example = "2023-10-30")
    var toDate: String? = "",

    @Schema(description = "자격증 등급 : ['']전체, ['TP1']1종, ['TP2']2종, ['TP3']3종, ['TP4']4종", type = "String", nullable = true, example = "TP1")
    var licenseGrade: String? = "",

    @Schema(description = "검색조건 : ['']전체 ['10']관리자명, ['20']업체명, ['30']면허등급", nullable = true, type = "String")
    var searchCode: String? = "",

    @Schema(description = "검색어", nullable = true, type = "String", example = "옥순")
    var searchKeyword: String = "",
) {
    init {
        this.offset = (page - 1) * size
    }
}
