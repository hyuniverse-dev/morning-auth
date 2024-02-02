package com.morning.auth.biz.sample.dto.res

import io.swagger.v3.oas.annotations.media.Schema

data class ResSampleDTO (
    @Schema(description = "샘플번호", nullable = false)
    val sampleNo: Long,

    @Schema(description = "샘플명", nullable = false, example = "이것은 샘플입니다.")
    val sampleName: String
)