package com.raillylinker.springboot_mvc_template_private.services

import com.raillylinker.springboot_mvc_template_private.controllers.C1Controller
import jakarta.servlet.http.HttpServletResponse
import org.springframework.web.servlet.ModelAndView

interface C1Service {
    // (루트 홈페이지 반환 함수)
    fun api1GetRoot(
        httpServletResponse: HttpServletResponse
    ): ModelAndView?


    ////
    // (Project Runtime Config Redis Key-Value 모두 조회)
    fun api2SelectAllProjectRuntimeConfigsRedisKeyValue(httpServletResponse: HttpServletResponse): C1Controller.Api2SelectAllProjectRuntimeConfigsRedisKeyValueOutputVo?


    ////
    // (Redis Project Runtime Config actuatorAllowIpList 입력)
    fun api3InsertProjectRuntimeConfigActuatorAllowIpList(
        httpServletResponse: HttpServletResponse,
        inputVo: C1Controller.Api3InsertProjectRuntimeConfigActuatorAllowIpListInputVo
    )


    ////
    // (Redis Project Runtime Config loggingDenyIpList 입력)
    fun api4InsertProjectRuntimeConfigLoggingDenyIpList(
        httpServletResponse: HttpServletResponse,
        inputVo: C1Controller.Api4InsertProjectRuntimeConfigLoggingDenyIpListInputVo
    )
}