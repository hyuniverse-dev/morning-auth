package com.morning.auth.biz.back.manager.dto.res

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "password.valid")
@JsonIgnoreProperties("\$\$beanFactory")
class ResRuleDTO {
    @Schema(description = "최소 길이 검사 패턴", type = "String")
    var minPattern: String = ""

    @Schema(description = "최대 길이 검사 패턴", type = "String")
    var maxPattern: String = ""

    @Schema(description = "숫자 1개 이상 포함 검사 패턴", type = "String")
    var numberPattern: String = ""

    @Schema(description = "특수문자 1개 이상 포함 검사 패턴", type = "String")
    var specialPattern: String = ""

    @Schema(description = "문자 1개 이상 포함 검사 패턴", type = "String")
    var characterPattern: String = ""
}
