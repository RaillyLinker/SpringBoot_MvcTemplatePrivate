package com.raillylinker.springboot_mvc_template_private.services

import com.raillylinker.springboot_mvc_template_private.controllers.C2Service1TkV1RequestTestController
import jakarta.servlet.http.HttpServletResponse
import org.springframework.core.io.Resource
import org.springframework.web.context.request.async.DeferredResult
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter

interface C2Service1TkV1RequestTestService {
    // (기본 요청 테스트 API)
    fun api1BasicRequestTest(httpServletResponse: HttpServletResponse): String?


    ////
    // (요청 Redirect 테스트)
    fun api2RedirectTest(httpServletResponse: HttpServletResponse): ModelAndView?


    ////
    // (요청 Forward 테스트)
    fun api3ForwardTest(httpServletResponse: HttpServletResponse): ModelAndView?


    ////
    // (Get 요청 테스트 (Query Parameter))
    fun api4GetRequestTest(
        httpServletResponse: HttpServletResponse,
        queryParamString: String,
        queryParamStringNullable: String?,
        queryParamInt: Int,
        queryParamIntNullable: Int?,
        queryParamDouble: Double,
        queryParamDoubleNullable: Double?,
        queryParamBoolean: Boolean,
        queryParamBooleanNullable: Boolean?,
        queryParamStringList: List<String>,
        queryParamStringListNullable: List<String>?
    ): C2Service1TkV1RequestTestController.Api4GetRequestTestOutputVo?


    ////
    // (Get 요청 테스트 (Path Parameter))
    fun api5GetRequestTestWithPathParam(
        httpServletResponse: HttpServletResponse,
        pathParamInt: Int
    ): C2Service1TkV1RequestTestController.Api5GetRequestTestWithPathParamOutputVo?


    ////
    // (Post 요청 테스트 (application-json))
    fun api6PostRequestTestWithApplicationJsonTypeRequestBody(
        httpServletResponse: HttpServletResponse,
        inputVo: C2Service1TkV1RequestTestController.Api6PostRequestTestWithApplicationJsonTypeRequestBodyInputVo
    ): C2Service1TkV1RequestTestController.Api6PostRequestTestWithApplicationJsonTypeRequestBodyOutputVo?


    ////
    // (Post 요청 테스트 (application-json, 객체 파라미터 포함))
    fun api6Dot1PostRequestTestWithApplicationJsonTypeRequestBody2(
        httpServletResponse: HttpServletResponse,
        inputVo: C2Service1TkV1RequestTestController.Api6Dot1PostRequestTestWithApplicationJsonTypeRequestBody2InputVo
    ): C2Service1TkV1RequestTestController.Api6Dot1PostRequestTestWithApplicationJsonTypeRequestBody2OutputVo?


    ////
    // (Post 요청 테스트 (입출력값 없음))
    fun api6Dot2PostRequestTestWithNoInputAndOutput(
        httpServletResponse: HttpServletResponse
    )


    ////
    // (Post 요청 테스트 (x-www-form-urlencoded))
    fun api7PostRequestTestWithFormTypeRequestBody(
        httpServletResponse: HttpServletResponse,
        inputVo: C2Service1TkV1RequestTestController.Api7PostRequestTestWithFormTypeRequestBodyInputVo
    ): C2Service1TkV1RequestTestController.Api7PostRequestTestWithFormTypeRequestBodyOutputVo?


    ////
    // (Post 요청 테스트 (multipart/form-data))
    fun api8PostRequestTestWithMultipartFormTypeRequestBody(
        httpServletResponse: HttpServletResponse,
        inputVo: C2Service1TkV1RequestTestController.Api8PostRequestTestWithMultipartFormTypeRequestBodyInputVo
    ): C2Service1TkV1RequestTestController.Api8PostRequestTestWithMultipartFormTypeRequestBodyOutputVo?


    ////
    // (Post 요청 테스트2 (multipart/form-data))
    fun api9PostRequestTestWithMultipartFormTypeRequestBody2(
        httpServletResponse: HttpServletResponse,
        inputVo: C2Service1TkV1RequestTestController.Api9PostRequestTestWithMultipartFormTypeRequestBody2InputVo
    ): C2Service1TkV1RequestTestController.Api9PostRequestTestWithMultipartFormTypeRequestBody2OutputVo?


    ////
    // (Post 요청 테스트 (multipart/form-data - JsonString))
    fun api10PostRequestTestWithMultipartFormTypeRequestBody3(
        httpServletResponse: HttpServletResponse,
        inputVo: C2Service1TkV1RequestTestController.Api10PostRequestTestWithMultipartFormTypeRequestBody3InputVo
    ): C2Service1TkV1RequestTestController.Api10PostRequestTestWithMultipartFormTypeRequestBody3OutputVo?


    ////
    // (인위적 에러 발생 테스트)
    fun api11GenerateErrorTest(httpServletResponse: HttpServletResponse)

    ////
    // (결과 코드 발생 테스트)
    fun api12ReturnResultCodeThroughHeaders(
        httpServletResponse: HttpServletResponse,
        errorType: C2Service1TkV1RequestTestController.Api12ReturnResultCodeThroughHeadersErrorTypeEnum?
    )


    ////
    // (인위적 응답 지연 테스트)
    fun api13ResponseDelayTest(httpServletResponse: HttpServletResponse, delayTimeSec: Long)


    ////
    // (text/string 반환 샘플)
    fun api14ReturnTextStringTest(httpServletResponse: HttpServletResponse): String?


    ////
    // (text/html 반환 샘플)
    fun api15ReturnTextHtmlTest(httpServletResponse: HttpServletResponse): ModelAndView?


    ////
    // (byte 반환 샘플)
    fun api16ReturnByteDataTest(httpServletResponse: HttpServletResponse): Resource?


    ////
    // (비디오 스트리밍 샘플)
    fun api17VideoStreamingTest(
        videoHeight: C2Service1TkV1RequestTestController.Api17VideoStreamingTestVideoHeight,
        httpServletResponse: HttpServletResponse
    ): Resource?


    ////
    // (오디오 스트리밍 샘플)
    fun api18AudioStreamingTest(httpServletResponse: HttpServletResponse): Resource?


    ////
    // (비동기 처리 결과 반환 샘플)
    fun api19AsynchronousResponseTest(httpServletResponse: HttpServletResponse): DeferredResult<C2Service1TkV1RequestTestController.Api19AsynchronousResponseTestOutputVo>?


    ////
    // (클라이언트가 특정 SSE 이벤트를 구독)
    fun api20SseTestSubscribe(httpServletResponse: HttpServletResponse, lastSseEventId: String?): SseEmitter?


    ////
    // (SSE 이벤트 전송 트리거 테스트)
    fun api21SseTestEventTrigger(httpServletResponse: HttpServletResponse)


    ////
    // (빈 리스트 받기 테스트)
    fun api22EmptyListRequestTest(
        httpServletResponse: HttpServletResponse,
        stringList: List<String>,
        inputVo: C2Service1TkV1RequestTestController.Api22EmptyListRequestTestInputVo
    ): C2Service1TkV1RequestTestController.Api22EmptyListRequestTestOutputVo?
}