package com.morning.auth.common.response

import io.swagger.v3.oas.annotations.media.Schema

class FailResponse(code: Int, message: String) : Response<Unit> {
    @Schema(description = "Http 응답 상태코드", type = "Integer")
    val code: Int = code
    @Schema(description = "오류 메시지", type = "String")
    val message: String = message
}