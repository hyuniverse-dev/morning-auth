package com.morning.auth.biz.back.manager.dto.res

import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

data class ResMenuDTO(
    @Schema(description = "메뉴번호", type = "Int")
    val menuNo: Int,

    @Schema(description = "메뉴구분[대시보드(DSB), 운영관리(OPM), 파일럿관리(PLM), 장비관리(EQM), 임무관리(MSM)]", type = "String")
    val menuDiv: String,

    @Schema(description = "메뉴이름", type = "String")
    val menuName: String,

    @Schema(description = "메뉴Url", type = "String")
    val menuUrl: String,

    @Schema(description = "등록자코드", type = "String")
    val registerCode: String,

    @Schema(description = "등록일시", type = "LocalDateTime")
    val registDatetime: LocalDateTime,

    @Schema(description = "수정자코드", type = "String")
    val modifierCode: String,

    @Schema(description = "수정일시", type = "LocalDateTime")
    val modifyDatetime: LocalDateTime,
)
