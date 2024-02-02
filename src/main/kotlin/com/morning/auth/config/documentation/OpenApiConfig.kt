package com.morning.auth.config.documentation

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class OpenApiConfig {
    @Bean
    fun openAPI(): OpenAPI {
        val info: Info = Info()
                .title("모닝인증시스템")
                .version("1.0.0")
                .description("회원가입 및 인증,권한 관리를 위한 인증시스템")

        val securityScheme: SecurityScheme = SecurityScheme()
                .type(SecurityScheme.Type.APIKEY)
                .`in`(SecurityScheme.In.HEADER)
                .name("Authorization")

        val securityRequirement: SecurityRequirement = SecurityRequirement()
                .addList("Authorization")

        return OpenAPI()
                .components(Components().addSecuritySchemes("Authorization", securityScheme))
                .security(listOf(securityRequirement))
                .info(info)
    }
}