package com.morning.auth.biz.back.manager.dto.req

import com.fasterxml.jackson.annotation.JsonIgnore
import com.morning.auth.common.utils.CryptoUtils
import com.morning.auth.common.utils.generateUUID
import com.morning.auth.common.utils.validPasswordPattern
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate

class ReqPilotDTO(
    @Schema(description = "로그인아이디", nullable = false, example = "tester@mncf.io")
    @field: NotBlank(message = "{loginId.not.blank}")
    var loginId: String?,

    @Schema(description = "비밀번호", nullable = false, example = "test1234!")
    @field: NotBlank(message = "{password.not.blank}")
    var password: String?,

    @Schema(description = "사용여부 : Y(사용),N(미사용)", nullable = false, example = "Y")
    @field:Pattern(regexp = "Y|N", message = "{useYn.pattern}")
    var useYn: String?,

    @Schema(
        description = "접근권한",
        nullable = false,
        example = "4b9a81007a2211eeb1d10242ac130002"
    )
    @field: NotBlank(message = "{roleCode.not.blank}")
    var roleCode: String?,

    @Schema(description = "관제사명", nullable = false, example = "옥순")
    @field: NotBlank(message = "{name.not.blank}")
    var name: String?,

    @Schema(description = "휴대폰번호", nullable = false, example = "010-1234-1234")
    @field: NotBlank(message = "{phone.not.blank}")
    var phone: String?,

    @Schema(description = "이메일", nullable = false, example = "operator@mncf.io")
    @field: NotBlank(message = "{email.not.blank}")
    var email: String?,

    @Schema(description = "소속명", nullable = false, example = "(주)모닝커피소프트")
    var groupName: String?,

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Schema(description = "생년월일", nullable = false, example = "1999-12-25")
    var birthDate: LocalDate?,

    @Schema(description = "국적코드", nullable = false, example = "KR")
    @field: NotBlank(message = "{nationalityCode.not.blank}")
    var nationalityCode: String?,

    @Schema(description = "주소", nullable = false, example = "서울특별시 서초구 명달로 116")
    @field: NotBlank(message = "{address.not.blank}")
    var address: String?,

    @Schema(description = "파일럿 상세", nullable = false)
    var pilotDetail: ReqPilotDetailDTO
) {
    @JsonIgnore
    val managerCode: String = generateUUID()

    //Todo: 화면으로부터 접속중인 관리자 코드로 대체
    @JsonIgnore
    val registerCode: String = "e3697962871846e79d552a0fa2c78e08"

    // Todo: 화면으로부터 접속중인 관리자 코드로 대체
    @JsonIgnore
    val modifierCode: String = "e3697962871846e79d552a0fa2c78e08"

    @JsonIgnore
    var serviceDiv: String = "FO"

    @JsonIgnore
    var temporaryPasswordYn: String = "Y"

    @JsonIgnore
    private var isValid: Boolean = false


    init {
        if (password?.isNotBlank() == true) {
            val matcher = validPasswordPattern(password!!)
            if (matcher.matches()) {
                this.password = CryptoUtils.digestText(password!!)
                this.isValid = true
            }
        }
        this.phone = phone?.replace("-", "")
        this.pilotDetail.managerCode = managerCode
        this.pilotDetail.registerCode = registerCode
        this.pilotDetail.modifierCode = modifierCode
    }

    @Schema(hidden = true)
    fun getPasswordResult(): Boolean {
        return isValid
    }
}

