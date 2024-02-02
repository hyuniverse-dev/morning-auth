package com.morning.auth.config.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig {
    @Bean
    @Throws(Exception::class)
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            // Security 에서 기본으로 생성하는 Login 페이지 사용 안 함
            .csrf { c: CsrfConfigurer<HttpSecurity> -> c.disable() }
            // 세션 사용안함
            .sessionManagement { session ->
                session.sessionCreationPolicy(
                    SessionCreationPolicy.STATELESS,
                )
            }
            .authorizeHttpRequests { request ->
                request
                    .requestMatchers("/swagger-ui/**", "/api-docs/**").permitAll()
                    .requestMatchers("/api/login", "/api/logout").permitAll()
                    .requestMatchers("/api/**").permitAll()
                    .anyRequest().authenticated()
            }

        return http.build()
    }
}
