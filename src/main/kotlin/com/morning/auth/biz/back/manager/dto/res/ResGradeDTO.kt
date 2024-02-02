package com.morning.auth.biz.back.manager.dto.res

import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

data class ResGradeDTO(

    @Schema(description = "등급 코드[TP1(1등급), TP2(2등급), TP3(3등급), TP4(4등급)]", type = "String")
    val code: String,

    @Schema(description = "언어 코드", type = "String")
    val languageCode: String,

    @Schema(description = "코드명", type = "String")
    val codeName: String,

    @Schema(description = "정렬 순서", type = "String")
    val sortSequence: String,

    @Schema(description = "등록일시", type = "String")
    val registDatetime: LocalDateTime,

    @Schema(description = "수저일시", type = "String")
    val modifyDatetime: LocalDateTime
)
