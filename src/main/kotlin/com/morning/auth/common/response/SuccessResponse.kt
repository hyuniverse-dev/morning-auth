package com.morning.auth.common.response

import com.morning.auth.common.constants.HttpCodes
import io.swagger.v3.oas.annotations.media.Schema

class SuccessResponse<T>(data: T) : Response<T> {
    @Schema(description = "Http 응답 상태코드", type = "Integer")
    val code: Int = HttpCodes.OK
    @Schema(description = "결과값", type = "Any")
    var result: T? = data
}