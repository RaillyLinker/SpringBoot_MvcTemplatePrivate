package com.raillylinker.springboot_mvc_template_private.services

import com.raillylinker.springboot_mvc_template_private.controllers.C3Service1TkV1RequestFromServerTestController
import jakarta.servlet.http.HttpServletResponse

interface C3Service1TkV1RequestFromServerTestService {
    // (기본 요청 테스트)
    fun api1BasicRequestTest(httpServletResponse: HttpServletResponse): String?


    ////
    // (Redirect 테스트)
    fun api2RedirectTest(httpServletResponse: HttpServletResponse): String?


    ////
    // (Forward 테스트)
    fun api3ForwardTest(httpServletResponse: HttpServletResponse): String?


    ////
    // (Get 요청 테스트 (Query Parameter))
    fun api4GetRequestTest(httpServletResponse: HttpServletResponse): C3Service1TkV1RequestFromServerTestController.Api4GetRequestTestOutputVo?


    ////
    // (Get 요청 테스트 (Path Parameter))
    fun api5GetRequestTestWithPathParam(httpServletResponse: HttpServletResponse): C3Service1TkV1RequestFromServerTestController.Api5GetRequestTestWithPathParamOutputVo?


    ////
    // (Post 요청 테스트 (Request Body, application/json))
    fun api6PostRequestTestWithApplicationJsonTypeRequestBody(httpServletResponse: HttpServletResponse): C3Service1TkV1RequestFromServerTestController.Api6PostRequestTestWithApplicationJsonTypeRequestBodyOutputVo?


    ////
    // (Post 요청 테스트 (Request Body, x-www-form-urlencoded))
    fun api7PostRequestTestWithFormTypeRequestBody(httpServletResponse: HttpServletResponse): C3Service1TkV1RequestFromServerTestController.Api7PostRequestTestWithFormTypeRequestBodyOutputVo?


    ////
    // (Post 요청 테스트 (Request Body, multipart/form-data))
    fun api8PostRequestTestWithMultipartFormTypeRequestBody(httpServletResponse: HttpServletResponse): C3Service1TkV1RequestFromServerTestController.Api8PostRequestTestWithMultipartFormTypeRequestBodyOutputVo?


    ////
    // (Post 요청 테스트 (Request Body, multipart/form-data, MultipartFile List))
    fun api9PostRequestTestWithMultipartFormTypeRequestBody2(httpServletResponse: HttpServletResponse): C3Service1TkV1RequestFromServerTestController.Api9PostRequestTestWithMultipartFormTypeRequestBody2OutputVo?


    ////
    // (Post 요청 테스트 (Request Body, multipart/form-data, with jsonString))
    fun api10PostRequestTestWithMultipartFormTypeRequestBody3(httpServletResponse: HttpServletResponse): C3Service1TkV1RequestFromServerTestController.Api10PostRequestTestWithMultipartFormTypeRequestBody3OutputVo?


    ////
    // (에러 발생 테스트)
    fun api11GenerateErrorTest(httpServletResponse: HttpServletResponse)


    ////
    // (api-result-code 반환 테스트)
    fun api12ReturnResultCodeThroughHeaders(httpServletResponse: HttpServletResponse)


    ////
    // (응답 지연 발생 테스트)
    fun api13ResponseDelayTest(httpServletResponse: HttpServletResponse, delayTimeSec: Long)


    ////
    // (text/string 형식 Response 받아오기)
    fun api14ReturnTextStringTest(httpServletResponse: HttpServletResponse): String?


    ////
    // (text/html 형식 Response 받아오기)
    fun api15ReturnTextHtmlTest(httpServletResponse: HttpServletResponse): String?


    ////
    // (DeferredResult Get 요청 테스트)
    fun api16AsynchronousResponseTest(httpServletResponse: HttpServletResponse): C3Service1TkV1RequestFromServerTestController.Api16AsynchronousResponseTestOutputVo?


    ////
//    fun api17SseSubscribeTest(httpServletResponse: HttpServletResponse)


//    ////
//    fun api18WebsocketConnectTest(httpServletResponse: HttpServletResponse)

//    data class Api18MessagePayloadVo(
//        val sender: String, // 송신자 (실제로는 JWT 로 보안 처리를 할 것)
//        val message: String
//    )
}