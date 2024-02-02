package com.morning.auth.biz.back.manager.dto.req

import com.fasterxml.jackson.annotation.JsonIgnore

class ReqWatcherDetailDTO(
    @JsonIgnore
    var managerCode: String,

    //Todo: 화면으로부터 접속중인 관리자 코드로 교체
    @JsonIgnore
    var registerCode: String,

    //Todo: 화면으로부터 접속중인 관리자 코드로 교체
    @JsonIgnore
    var modifierCode: String,
)
