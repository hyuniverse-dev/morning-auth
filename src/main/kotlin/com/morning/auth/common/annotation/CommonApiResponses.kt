package com.morning.auth.common.annotation

import com.morning.auth.common.constants.HttpStatusDescriptions
import com.morning.auth.common.response.FailResponse
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses

@ApiResponses(value = [
    ApiResponse(responseCode = "200", description = HttpStatusDescriptions.OK, useReturnTypeSchema = true),
    ApiResponse(responseCode = "400", description = HttpStatusDescriptions.BAD_REQUEST, content = [Content(schema = Schema(implementation = FailResponse::class))]),
    ApiResponse(responseCode = "404", description = HttpStatusDescriptions.NOT_FOUND, content = [Content(schema = Schema(implementation = FailResponse::class))]),
    ApiResponse(responseCode = "500", description = HttpStatusDescriptions.INTERNAL_SERVER_ERROR, content = [Content(schema = Schema(implementation = FailResponse::class))])
])
annotation class CommonApiResponses
