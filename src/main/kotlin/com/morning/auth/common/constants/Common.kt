package com.morning.auth.common.constants

object HttpCodes {
    const val OK = 200

    const val BAD_REQUEST = 400
    const val UNAUTHORIZED = 401
    const val FORBIDDEN = 403
    const val NOT_FOUND = 404
    const val METHOD_NOT_ALLOWED = 405

    const val INTERNAL_SERVER_ERROR = 500
}

object HttpStatusDescriptions {
    const val OK = "요청을 성공했습니다."

    const val BAD_REQUEST = "잘못된 요청입니다."
    const val UNAUTHORIZED = "인증되지 않았습니다."
    const val FORBIDDEN = "권한이 없습니다."
    const val NOT_FOUND = "요청을 찾을 수 없습니다."
    const val METHOD_NOT_ALLOWED = "허용되지 않는 메소드입니다."

    const val INTERNAL_SERVER_ERROR = "내부 오류가 발생했습니다."
}