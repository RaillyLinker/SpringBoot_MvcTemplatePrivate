package com.raillylinker.springboot_mvc_template_private.services

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import jakarta.servlet.http.HttpSession
import org.springframework.web.servlet.ModelAndView

interface SC1MainScV1Service {
    // (홈페이지 반환)
    fun api1HomePage(
        httpServletRequest: HttpServletRequest,
        httpServletResponse: HttpServletResponse,
        session: HttpSession
    ): ModelAndView?
}