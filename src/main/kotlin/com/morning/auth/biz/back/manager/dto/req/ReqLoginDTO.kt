package com.morning.auth.biz.back.manager.dto.req

import com.morning.auth.common.utils.CryptoUtils
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank

data class ReqLoginDTO(
    @Schema(description = "로그인아이디", nullable = false, example = "master@mncf.io")
    @field: NotBlank(message = "{loginId.not.blank}")
    val loginId: String,

    @Schema(description = "비밀번호", nullable = false, example = "test1234!")
    @field: NotBlank(message = "{password.not.blank}")
    var password: String?,
) {
    init {
        if (password?.isNotBlank() == true) {
            this.password = CryptoUtils.digestText(password!!)
        }
    }
}
