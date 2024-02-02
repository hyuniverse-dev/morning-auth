package com.morning.auth.common.exception

import com.fasterxml.jackson.databind.ObjectMapper
import com.morning.auth.biz.sample.dto.req.ReqSampleDTO
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
class GlobalControllerExceptionHandlerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc;
    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Test
    @DisplayName("Bad Request 예외처리")
    fun badRequestExceptionTest() {
        val sampleNo = 0L
        val requestBody = objectMapper.writeValueAsString(ReqSampleDTO(sampleNo = 0, sampleName = ""))

        val url = "/api/mybatis/sample/${sampleNo}"

        mockMvc.perform(put(url)
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestBody))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.code", `is`(400)))
    }

    @Test
    @DisplayName("Page Not Found 예외처리")
    fun notFoundExceptionTest() {
        val url = "/api/mybatis/sample/10/10"

        mockMvc.perform(get(url)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.code", `is`(404)))
    }

    @Test
    @DisplayName("Method Not Allowed 예외처리")
    fun methodNotAllowed() {
        val url = "/api/mybatis/sample/10"

        mockMvc.perform(post(url)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.code", `is`(405)))
    }
}
