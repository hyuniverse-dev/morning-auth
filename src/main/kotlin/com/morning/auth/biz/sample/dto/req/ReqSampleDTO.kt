package com.morning.auth.biz.sample.dto.req

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank

data class ReqSampleDTO(
    @Schema(description = "샘플번호", nullable = false)
    val sampleNo: Long?,

    @field: NotBlank(message = "{sampleName.not.blank}")
    @Schema(description = "샘플명", nullable = false, example = "이것은 샘플입니다.")
    var sampleName: String?,
)
