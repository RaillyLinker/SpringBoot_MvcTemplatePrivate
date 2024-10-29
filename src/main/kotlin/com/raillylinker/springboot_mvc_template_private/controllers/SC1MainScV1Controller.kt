package com.raillylinker.springboot_mvc_template_private.controllers

import com.raillylinker.springboot_mvc_template_private.services.SC1MainScV1Service
import io.swagger.v3.oas.annotations.Hidden
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import jakarta.servlet.http.HttpSession
import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.ModelAndView


// 본 컨트롤러는 프로젝트 단위로 지원하는 웹 서비스 기능들을 모아둔 것이며, MVC 화면 생성 방식을 보여줍니다.
// RestAPI 와는 달리 API 문서를 Swagger 로 공개할 필요는 없기에 아래 @Hidden 어노테이션으로 숨김 처리를 했습니다.
// 그럼에도 Swagger 작성 방식으로 API 작성 방식을 고수하는 이유는,
// Swagger 작성 방식이 코드상으로 따로 주석을 달 필요 없이 체계적으로 API 를 설명하기 좋아서이며,
// RestAPI 와의 구분 없이 코드 형식에 통일성을 부여하여 코드 해석이 쉽도록 하기 위한 조치입니다.
@Hidden
@Tag(name = "/main/sc/v1 APIs", description = "SC1 : main 웹 페이지에 대한 API 컨트롤러")
@Controller
@RequestMapping("/main/sc/v1")
class SC1MainScV1Controller(
    private val service: SC1MainScV1Service
) {
    // <멤버 변수 공간>


    // ---------------------------------------------------------------------------------------------
    // <매핑 함수 공간>
    @Operation(
        summary = "N1 : 홈페이지",
        description = "루트 홈페이지 화면을 반환합니다.\n\n"
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "정상 동작"
            )
        ]
    )
    @GetMapping(
        path = ["/home"],
        consumes = [MediaType.ALL_VALUE],
        produces = [MediaType.TEXT_HTML_VALUE]
    )
    fun api1HomePage(
        @Parameter(hidden = true)
        httpServletRequest: HttpServletRequest,
        @Parameter(hidden = true)
        httpServletResponse: HttpServletResponse,
        @Parameter(hidden = true)
        session: HttpSession
    ): ModelAndView? {
        return service.api1HomePage(httpServletRequest, httpServletResponse, session)
    }
}