package com.morning.auth.common.exception

import com.fasterxml.jackson.databind.exc.MismatchedInputException
import com.fasterxml.jackson.module.kotlin.MissingKotlinParameterException
import com.morning.auth.common.constants.HttpCodes
import com.morning.auth.common.constants.HttpStatusDescriptions
import com.morning.auth.common.response.FailResponse
import jakarta.validation.ConstraintViolationException
import org.apache.commons.lang3.exception.ExceptionUtils
import org.slf4j.LoggerFactory
import org.springframework.beans.BeanInstantiationException
import org.springframework.beans.factory.annotation.Value
import org.springframework.validation.BindException
import org.springframework.validation.ObjectError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
import org.springframework.web.servlet.NoHandlerFoundException


@RestControllerAdvice
class GlobalControllerExceptionHandler {
    private val logger = LoggerFactory.getLogger(GlobalControllerExceptionHandler::class.java)

    @Value("\${spring.profiles.active}")
    private val activeProfile: String? = null

    // @RequestPram @PathVariable 예외
    @ExceptionHandler(ConstraintViolationException::class)
    fun constraintViolationExceptionHandler(e: ConstraintViolationException): FailResponse {
        logger.error(ExceptionUtils.getMessage(e))
        return FailResponse(HttpCodes.BAD_REQUEST, if(activeProfile == "prd") HttpStatusDescriptions.BAD_REQUEST else ExceptionUtils.getMessage(e))
    }

    // @RequestBody 예외
    @ExceptionHandler(BindException::class, MethodArgumentNotValidException::class)
    fun methodArgumentNotValidExceptionHandler(e: MethodArgumentNotValidException): FailResponse {
        logger.error(ExceptionUtils.getMessage(e))

        val errorMessage = StringBuilder()
        val allErrors: List<ObjectError> = e.bindingResult.allErrors

        for (error in allErrors) {
            errorMessage.append("[").append(error.defaultMessage).append("]")
        }
        return FailResponse(HttpCodes.BAD_REQUEST, if(activeProfile == "prd") HttpStatusDescriptions.BAD_REQUEST else errorMessage.toString())
    }

    // Paramter 의 Type 의 Mismatch 예외
    @ExceptionHandler(MethodArgumentTypeMismatchException::class)
    fun methodArgumentTypeMismatchExceptionHandler(e: MethodArgumentTypeMismatchException): FailResponse {
        logger.error(ExceptionUtils.getMessage(e))
        return FailResponse(HttpCodes.BAD_REQUEST, if(activeProfile == "prd") HttpStatusDescriptions.BAD_REQUEST else ExceptionUtils.getMessage(e))
    }

    // 입력값이 잘못된 경우 예외
    @ExceptionHandler(IllegalArgumentException::class)
    fun illegalArgumentExceptionHandler(e: IllegalArgumentException): FailResponse {
        logger.error(ExceptionUtils.getMessage(e))
        return FailResponse(HttpCodes.BAD_REQUEST, if(activeProfile == "prd") HttpStatusDescriptions.BAD_REQUEST else ExceptionUtils.getMessage(e))
    }

    // Page Not Found 예외
    @ExceptionHandler(NoHandlerFoundException::class)
    fun noHandlerFoundExceptionHandler(e: NoHandlerFoundException): FailResponse {
        logger.error(ExceptionUtils.getMessage(e))
        return FailResponse(HttpCodes.NOT_FOUND, if(activeProfile == "prd") HttpStatusDescriptions.NOT_FOUND else ExceptionUtils.getMessage(e))
    }

    // Method Not Allowed 예외 (Exception Catch 가 안되어 exception Handler 에서 처리
//    @ExceptionHandler(HttpRequestMethodNotSupportedException::class)
//    fun httpRequestMethodNotSupportedExceptionHadler(e: NoHandlerFoundException): FailResponse {
//        logger.error(ExceptionUtils.getMessage(e))
//        return FailResponse(HttpCodes.METHOD_NOT_ALLOWED, if(activeProfile == "prd") HttpStatusDescriptions.METHOD_NOT_ALLOWED else ExceptionUtils.getMessage(e))
//    }

    @ExceptionHandler(MissingKotlinParameterException::class)
    fun missingKotlinParameterExceptionHandler(e: NoHandlerFoundException): FailResponse {
        logger.error(ExceptionUtils.getMessage(e))
        return FailResponse(HttpCodes.NOT_FOUND, if(activeProfile == "prd") HttpStatusDescriptions.NOT_FOUND else ExceptionUtils.getMessage(e))
    }

//    @ExceptionHandler(BeanInstantiationException::class)
//    fun beanInstantiationException(e: NoHandlerFoundException): FailResponse {
//        logger.error(ExceptionUtils.getMessage(e))
//        return FailResponse(HttpCodes.NOT_FOUND, if(activeProfile == "prd") HttpStatusDescriptions.NOT_FOUND else ExceptionUtils.getMessage(e))
//    }

    @ExceptionHandler(Exception::class)
    fun exceptionHandler(e: Exception): FailResponse {
        logger.error(ExceptionUtils.getMessage(e))
        // TODO @ExceptionHandler(HttpRequestMethodNotSupportedException::class) 으로 처리
        return when(e.javaClass.name) {
            "org.springframework.web.HttpRequestMethodNotSupportedException" -> FailResponse(HttpCodes.METHOD_NOT_ALLOWED, if(activeProfile == "prd") HttpStatusDescriptions.METHOD_NOT_ALLOWED else ExceptionUtils.getMessage(e))
            else -> FailResponse(HttpCodes.INTERNAL_SERVER_ERROR, if(activeProfile == "prd") HttpStatusDescriptions.INTERNAL_SERVER_ERROR else ExceptionUtils.getMessage(e))
        }
    }

    @ExceptionHandler(RuntimeException::class)
    fun runtimeExceptionHandler(e: RuntimeException): FailResponse {
        logger.error(ExceptionUtils.getMessage(e))
        return FailResponse(HttpCodes.INTERNAL_SERVER_ERROR, if(activeProfile == "prd") HttpStatusDescriptions.INTERNAL_SERVER_ERROR else ExceptionUtils.getMessage(e))
    }
}
