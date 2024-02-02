package com.morning.auth.biz.sample.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.morning.auth.biz.sample.dto.req.ReqSampleDTO
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
class MyBatisSampleControllerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc;

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Test
    fun registSample() {
        val requestBody = objectMapper.writeValueAsString(ReqSampleDTO(sampleNo = 0, sampleName = "신규생플 작성"))

        val url = "/api/mybatis/sample"

        mockMvc.perform(
            post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
        )
            .andExpect(status().isOk)
    }

    @Test
    fun modifySample() {
        val sampleNo = 18
        val requestBody = objectMapper.writeValueAsString(ReqSampleDTO(sampleNo = 0, sampleName = "생플갱신"))

        val url = "/api/mybatis/sample/${sampleNo}"

        mockMvc.perform(
            put(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
        )
            .andExpect(status().isOk)
    }
}
