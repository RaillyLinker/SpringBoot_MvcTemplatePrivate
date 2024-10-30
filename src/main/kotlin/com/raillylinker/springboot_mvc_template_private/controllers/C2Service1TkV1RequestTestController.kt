package com.raillylinker.springboot_mvc_template_private.controllers

import com.fasterxml.jackson.annotation.JsonProperty
import com.raillylinker.springboot_mvc_template_private.services.C2Service1TkV1RequestTestService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.headers.Header
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.servlet.http.HttpServletResponse
import org.springframework.core.io.Resource
import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import org.springframework.web.context.request.async.DeferredResult
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter


@Tag(name = "/service1/tk/v1/request-test APIs", description = "C2 : 요청 / 응답에 대한 테스트 API 컨트롤러")
@Controller
@RequestMapping("/service1/tk/v1/request-test")
class C2Service1TkV1RequestTestController(
    private val service: C2Service1TkV1RequestTestService
) {
    // <멤버 변수 공간>


    // ---------------------------------------------------------------------------------------------
    // <매핑 함수 공간>
    @Operation(
        summary = "N1 : 기본 요청 테스트 API",
        description = "이 API 를 요청하면 현재 실행중인 프로필 이름을 반환합니다.\n\n"
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
        path = [""],
        consumes = [MediaType.ALL_VALUE],
        produces = [MediaType.TEXT_PLAIN_VALUE]
    )
    @ResponseBody
    fun api1BasicRequestTest(
        @Parameter(hidden = true)
        httpServletResponse: HttpServletResponse
    ): String? {
        return service.api1BasicRequestTest(httpServletResponse)
    }


    ////
    @Operation(
        summary = "N2 : 요청 Redirect 테스트 API",
        description = "이 API 를 요청하면 /service1/tk/v1/request-test 로 Redirect 됩니다.\n\n"
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
        path = ["/redirect-to-blank"],
        consumes = [MediaType.ALL_VALUE],
        produces = [MediaType.ALL_VALUE]
    )
    fun api2RedirectTest(
        @Parameter(hidden = true)
        httpServletResponse: HttpServletResponse
    ): ModelAndView? {
        return service.api2RedirectTest(httpServletResponse)
    }


    ////
    @Operation(
        summary = "N3 : 요청 Forward 테스트 API",
        description = "이 API 를 요청하면 /service1/tk/v1/request-test 로 Forward 됩니다.\n\n"
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
        path = ["/forward-to-blank"],
        consumes = [MediaType.ALL_VALUE],
        produces = [MediaType.ALL_VALUE]
    )
    fun api3ForwardTest(
        @Parameter(hidden = true)
        httpServletResponse: HttpServletResponse
    ): ModelAndView? {
        return service.api3ForwardTest(httpServletResponse)
    }


    ////
    @Operation(
        summary = "N4 : Get 요청 테스트 (Query Parameter)",
        description = "Query Parameter 를 받는 Get 메소드 요청 테스트\n\n"
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
        path = ["/get-request"],
        consumes = [MediaType.ALL_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    @ResponseBody
    fun api4GetRequestTest(
        @Parameter(hidden = true)
        httpServletResponse: HttpServletResponse,
        @Parameter(name = "queryParamString", description = "String Query 파라미터", example = "testString")
        @RequestParam("queryParamString")
        queryParamString: String,
        @Parameter(
            name = "queryParamStringNullable",
            description = "String Query 파라미터 Nullable",
            example = "testString"
        )
        @RequestParam("queryParamStringNullable")
        queryParamStringNullable: String?,
        @Parameter(name = "queryParamInt", description = "Int Query 파라미터", example = "1")
        @RequestParam("queryParamInt")
        queryParamInt: Int,
        @Parameter(name = "queryParamIntNullable", description = "Int Query 파라미터 Nullable", example = "1")
        @RequestParam("queryParamIntNullable")
        queryParamIntNullable: Int?,
        @Parameter(name = "queryParamDouble", description = "Double Query 파라미터", example = "1.1")
        @RequestParam("queryParamDouble")
        queryParamDouble: Double,
        @Parameter(name = "queryParamDoubleNullable", description = "Double Query 파라미터 Nullable", example = "1.1")
        @RequestParam("queryParamDoubleNullable")
        queryParamDoubleNullable: Double?,
        @Parameter(name = "queryParamBoolean", description = "Boolean Query 파라미터", example = "true")
        @RequestParam("queryParamBoolean")
        queryParamBoolean: Boolean,
        @Parameter(name = "queryParamBooleanNullable", description = "Boolean Query 파라미터 Nullable", example = "true")
        @RequestParam("queryParamBooleanNullable")
        queryParamBooleanNullable: Boolean?,
        @Parameter(
            name = "queryParamStringList",
            description = "StringList Query 파라미터",
            example = "[\"testString1\", \"testString2\"]"
        )
        @RequestParam("queryParamStringList")
        queryParamStringList: List<String>,
        @Parameter(
            name = "queryParamStringListNullable", description = "StringList Query 파라미터 Nullable",
            example = "[\"testString1\", \"testString2\"]"
        )
        @RequestParam("queryParamStringListNullable")
        queryParamStringListNullable: List<String>?
    ): Api4GetRequestTestOutputVo? {
        return service.api4GetRequestTest(
            httpServletResponse,
            queryParamString,
            queryParamStringNullable,
            queryParamInt,
            queryParamIntNullable,
            queryParamDouble,
            queryParamDoubleNullable,
            queryParamBoolean,
            queryParamBooleanNullable,
            queryParamStringList,
            queryParamStringListNullable
        )
    }

    data class Api4GetRequestTestOutputVo(
        @Schema(description = "입력한 String Query 파라미터", required = true, example = "testString")
        @JsonProperty("queryParamString")
        val queryParamString: String,
        @Schema(description = "입력한 String Nullable Query 파라미터", required = false, example = "testString")
        @JsonProperty("queryParamStringNullable")
        val queryParamStringNullable: String?,
        @Schema(description = "입력한 Int Query 파라미터", required = true, example = "1")
        @JsonProperty("queryParamInt")
        val queryParamInt: Int,
        @Schema(description = "입력한 Int Nullable Query 파라미터", required = false, example = "1")
        @JsonProperty("queryParamIntNullable")
        val queryParamIntNullable: Int?,
        @Schema(description = "입력한 Double Query 파라미터", required = true, example = "1.1")
        @JsonProperty("queryParamDouble")
        val queryParamDouble: Double,
        @Schema(description = "입력한 Double Nullable Query 파라미터", required = false, example = "1.1")
        @JsonProperty("queryParamDoubleNullable")
        val queryParamDoubleNullable: Double?,
        @Schema(description = "입력한 Boolean Query 파라미터", required = true, example = "true")
        @JsonProperty("queryParamBoolean")
        val queryParamBoolean: Boolean,
        @Schema(description = "입력한 Boolean Nullable Query 파라미터", required = false, example = "true")
        @JsonProperty("queryParamBooleanNullable")
        val queryParamBooleanNullable: Boolean?,
        @Schema(
            description = "입력한 StringList Query 파라미터",
            required = true,
            example = "[\"testString1\", \"testString2\"]"
        )
        @JsonProperty("queryParamStringList")
        val queryParamStringList: List<String>,
        @Schema(
            description = "입력한 StringList Nullable Query 파라미터",
            required = false,
            example = "[\"testString1\", \"testString2\"]"
        )
        @JsonProperty("queryParamStringListNullable")
        val queryParamStringListNullable: List<String>?
    )


    ////
    @Operation(
        summary = "N5 : Get 요청 테스트 (Path Parameter)",
        description = "Path Parameter 를 받는 Get 메소드 요청 테스트\n\n"
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
        path = ["/get-request/{pathParamInt}"],
        consumes = [MediaType.ALL_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    @ResponseBody
    fun api5GetRequestTestWithPathParam(
        @Parameter(hidden = true)
        httpServletResponse: HttpServletResponse,
        @Parameter(name = "pathParamInt", description = "Int Path 파라미터", example = "1")
        @PathVariable("pathParamInt")
        pathParamInt: Int
    ): Api5GetRequestTestWithPathParamOutputVo? {
        return service.api5GetRequestTestWithPathParam(
            httpServletResponse,
            pathParamInt
        )
    }

    data class Api5GetRequestTestWithPathParamOutputVo(
        @Schema(description = "입력한 Int Path 파라미터", required = true, example = "1")
        @JsonProperty("pathParamInt")
        val pathParamInt: Int
    )


    ////
    @Operation(
        summary = "N6 : Post 요청 테스트 (application-json)",
        description = "application-json 형태의 Request Body 를 받는 Post 메소드 요청 테스트\n\n"
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "정상 동작"
            )
        ]
    )
    @PostMapping(
        path = ["/post-request-application-json"],
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    @ResponseBody
    fun api6PostRequestTestWithApplicationJsonTypeRequestBody(
        @Parameter(hidden = true)
        httpServletResponse: HttpServletResponse,
        @RequestBody
        inputVo: Api6PostRequestTestWithApplicationJsonTypeRequestBodyInputVo
    ): Api6PostRequestTestWithApplicationJsonTypeRequestBodyOutputVo? {
        return service.api6PostRequestTestWithApplicationJsonTypeRequestBody(
            httpServletResponse,
            inputVo
        )
    }

    data class Api6PostRequestTestWithApplicationJsonTypeRequestBodyInputVo(
        @Schema(description = "String Body 파라미터", required = true, example = "testString")
        @JsonProperty("requestBodyString")
        val requestBodyString: String,
        @Schema(description = "String Nullable Body 파라미터", required = false, example = "testString")
        @JsonProperty("requestBodyStringNullable")
        val requestBodyStringNullable: String?,
        @Schema(description = "Int Body 파라미터", required = true, example = "1")
        @JsonProperty("requestBodyInt")
        val requestBodyInt: Int,
        @Schema(description = "Int Nullable Body 파라미터", required = false, example = "1")
        @JsonProperty("requestBodyIntNullable")
        val requestBodyIntNullable: Int?,
        @Schema(description = "Double Body 파라미터", required = true, example = "1.1")
        @JsonProperty("requestBodyDouble")
        val requestBodyDouble: Double,
        @Schema(description = "Double Nullable Body 파라미터", required = false, example = "1.1")
        @JsonProperty("requestBodyDoubleNullable")
        val requestBodyDoubleNullable: Double?,
        @Schema(description = "Boolean Body 파라미터", required = true, example = "true")
        @JsonProperty("requestBodyBoolean")
        val requestBodyBoolean: Boolean,
        @Schema(description = "Boolean Nullable Body 파라미터", required = false, example = "true")
        @JsonProperty("requestBodyBooleanNullable")
        val requestBodyBooleanNullable: Boolean?,
        @Schema(description = "StringList Body 파라미터", required = true, example = "[\"testString1\", \"testString2\"]")
        @JsonProperty("requestBodyStringList")
        val requestBodyStringList: List<String>,
        @Schema(
            description = "StringList Nullable Body 파라미터",
            required = false,
            example = "[\"testString1\", \"testString2\"]"
        )
        @JsonProperty("requestBodyStringListNullable")
        val requestBodyStringListNullable: List<String>?
    )

    data class Api6PostRequestTestWithApplicationJsonTypeRequestBodyOutputVo(
        @Schema(description = "입력한 String Body 파라미터", required = true, example = "testString")
        @JsonProperty("requestBodyString")
        val requestBodyString: String,
        @Schema(description = "입력한 String Nullable Body 파라미터", required = false, example = "testString")
        @JsonProperty("requestBodyStringNullable")
        val requestBodyStringNullable: String?,
        @Schema(description = "입력한 Int Body 파라미터", required = true, example = "1")
        @JsonProperty("requestBodyInt")
        val requestBodyInt: Int,
        @Schema(description = "입력한 Int Nullable Body 파라미터", required = false, example = "1")
        @JsonProperty("requestBodyIntNullable")
        val requestBodyIntNullable: Int?,
        @Schema(description = "입력한 Double Body 파라미터", required = true, example = "1.1")
        @JsonProperty("requestBodyDouble")
        val requestBodyDouble: Double,
        @Schema(description = "입력한 Double Nullable Body 파라미터", required = false, example = "1.1")
        @JsonProperty("requestBodyDoubleNullable")
        val requestBodyDoubleNullable: Double?,
        @Schema(description = "입력한 Boolean Body 파라미터", required = true, example = "true")
        @JsonProperty("requestBodyBoolean")
        val requestBodyBoolean: Boolean,
        @Schema(description = "입력한 Boolean Nullable Body 파라미터", required = false, example = "true")
        @JsonProperty("requestBodyBooleanNullable")
        val requestBodyBooleanNullable: Boolean?,
        @Schema(
            description = "입력한 StringList Body 파라미터",
            required = true,
            example = "[\"testString1\", \"testString2\"]"
        )
        @JsonProperty("requestBodyStringList")
        val requestBodyStringList: List<String>,
        @Schema(
            description = "입력한 StringList Nullable Body 파라미터",
            required = false,
            example = "[\"testString1\", \"testString2\"]"
        )
        @JsonProperty("requestBodyStringListNullable")
        val requestBodyStringListNullable: List<String>?
    )


    ////
    @Operation(
        summary = "N6.1 : Post 요청 테스트 (application-json, 객체 파라미터 포함)",
        description = "application-json 형태의 Request Body(객체 파라미터 포함) 를 받는 Post 메소드 요청 테스트\n\n"
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "정상 동작"
            )
        ]
    )
    @PostMapping(
        path = ["/post-request-application-json-with-object-param"],
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    @ResponseBody
    fun api6Dot1PostRequestTestWithApplicationJsonTypeRequestBody2(
        @Parameter(hidden = true)
        httpServletResponse: HttpServletResponse,
        @RequestBody
        inputVo: Api6Dot1PostRequestTestWithApplicationJsonTypeRequestBody2InputVo
    ): Api6Dot1PostRequestTestWithApplicationJsonTypeRequestBody2OutputVo? {
        return service.api6Dot1PostRequestTestWithApplicationJsonTypeRequestBody2(
            httpServletResponse,
            inputVo
        )
    }

    data class Api6Dot1PostRequestTestWithApplicationJsonTypeRequestBody2InputVo(
        @Schema(description = "객체 타입 파라미터", required = true)
        @JsonProperty("objectVo")
        val objectVo: ObjectVo,
        @Schema(description = "객체 타입 리스트 파라미터", required = true)
        @JsonProperty("objectVoList")
        val objectVoList: List<ObjectVo>
    ) {
        data class ObjectVo(
            @Schema(description = "String Body 파라미터", required = true, example = "testString")
            @JsonProperty("requestBodyString")
            val requestBodyString: String,
            @Schema(
                description = "StringList Body 파라미터",
                required = true,
                example = "[\"testString1\", \"testString2\"]"
            )
            @JsonProperty("requestBodyStringList")
            val requestBodyStringList: List<String>,
            @Schema(description = "서브 객체 타입 파라미터", required = true)
            @JsonProperty("subObjectVo")
            val subObjectVo: SubObjectVo,
            @Schema(description = "서브 객체 타입 리스트 파라미터", required = true)
            @JsonProperty("subObjectVoList")
            val subObjectVoList: List<SubObjectVo>
        ) {
            data class SubObjectVo(
                @Schema(description = "String Body 파라미터", required = true, example = "testString")
                @JsonProperty("requestBodyString")
                val requestBodyString: String,
                @Schema(
                    description = "StringList Body 파라미터",
                    required = true,
                    example = "[\"testString1\", \"testString2\"]"
                )
                @JsonProperty("requestBodyStringList")
                val requestBodyStringList: List<String>
            )
        }
    }

    data class Api6Dot1PostRequestTestWithApplicationJsonTypeRequestBody2OutputVo(
        @Schema(description = "객체 타입 파라미터", required = true)
        @JsonProperty("objectVo")
        val objectVo: ObjectVo,
        @Schema(description = "객체 타입 리스트 파라미터", required = true)
        @JsonProperty("objectVoList")
        val objectVoList: List<ObjectVo>
    ) {
        @Schema(description = "객체 타입 파라미터 VO", required = true)
        data class ObjectVo(
            @Schema(description = "String Body 파라미터", required = true, example = "testString")
            @JsonProperty("requestBodyString")
            val requestBodyString: String,
            @Schema(
                description = "StringList Body 파라미터",
                required = true,
                example = "[\"testString1\", \"testString2\"]"
            )
            @JsonProperty("requestBodyStringList")
            val requestBodyStringList: List<String>,
            @Schema(description = "서브 객체 타입 파라미터", required = true)
            @JsonProperty("subObjectVo")
            val subObjectVo: SubObjectVo,
            @Schema(description = "서브 객체 타입 리스트 파라미터", required = true)
            @JsonProperty("subObjectVoList")
            val subObjectVoList: List<SubObjectVo>
        ) {
            @Schema(description = "서브 객체 타입 파라미터 VO", required = true)
            data class SubObjectVo(
                @Schema(description = "String Body 파라미터", required = true, example = "testString")
                @JsonProperty("requestBodyString")
                val requestBodyString: String,
                @Schema(
                    description = "StringList Body 파라미터",
                    required = true,
                    example = "[\"testString1\", \"testString2\"]"
                )
                @JsonProperty("requestBodyStringList")
                val requestBodyStringList: List<String>
            )
        }
    }


    ////
    @Operation(
        summary = "N6.2 : Post 요청 테스트 (입출력값 없음)",
        description = "입출력값이 없는 Post 메소드 요청 테스트\n\n"
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "정상 동작"
            )
        ]
    )
    @PostMapping(
        path = ["/post-request-application-json-with-no-param"],
        consumes = [MediaType.ALL_VALUE],
        produces = [MediaType.ALL_VALUE]
    )
    @ResponseBody
    fun api6Dot2PostRequestTestWithNoInputAndOutput(
        @Parameter(hidden = true)
        httpServletResponse: HttpServletResponse
    ) {
        return service.api6Dot2PostRequestTestWithNoInputAndOutput(
            httpServletResponse
        )
    }


    ////
    @Operation(
        summary = "N7 : Post 요청 테스트 (x-www-form-urlencoded)",
        description = "x-www-form-urlencoded 형태의 Request Body 를 받는 Post 메소드 요청 테스트\n\n"
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "정상 동작"
            )
        ]
    )
    @PostMapping(
        path = ["/post-request-x-www-form-urlencoded"],
        consumes = [MediaType.APPLICATION_FORM_URLENCODED_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    @ResponseBody
    fun api7PostRequestTestWithFormTypeRequestBody(
        @Parameter(hidden = true)
        httpServletResponse: HttpServletResponse,
        @ModelAttribute
        @RequestBody
        inputVo: Api7PostRequestTestWithFormTypeRequestBodyInputVo
    ): Api7PostRequestTestWithFormTypeRequestBodyOutputVo? {
        return service.api7PostRequestTestWithFormTypeRequestBody(httpServletResponse, inputVo)
    }

    data class Api7PostRequestTestWithFormTypeRequestBodyInputVo(
        @Schema(description = "String Form 파라미터", required = true, example = "testString")
        @JsonProperty("requestFormString")
        val requestFormString: String,
        @Schema(description = "String Nullable Form 파라미터", required = false, example = "testString")
        @JsonProperty("requestFormStringNullable")
        val requestFormStringNullable: String?,
        @Schema(description = "Int Form 파라미터", required = true, example = "1")
        @JsonProperty("requestFormInt")
        val requestFormInt: Int,
        @Schema(description = "Int Nullable Form 파라미터", required = false, example = "1")
        @JsonProperty("requestFormIntNullable")
        val requestFormIntNullable: Int?,
        @Schema(description = "Double Form 파라미터", required = true, example = "1.1")
        @JsonProperty("requestFormDouble")
        val requestFormDouble: Double,
        @Schema(description = "Double Nullable Form 파라미터", required = false, example = "1.1")
        @JsonProperty("requestFormDoubleNullable")
        val requestFormDoubleNullable: Double?,
        @Schema(description = "Boolean Form 파라미터", required = true, example = "true")
        @JsonProperty("requestFormBoolean")
        val requestFormBoolean: Boolean,
        @Schema(description = "Boolean Nullable Form 파라미터", required = false, example = "true")
        @JsonProperty("requestFormBooleanNullable")
        val requestFormBooleanNullable: Boolean?,
        @Schema(description = "StringList Form 파라미터", required = true, example = "[\"testString1\", \"testString2\"]")
        @JsonProperty("requestFormStringList")
        val requestFormStringList: List<String>,
        @Schema(
            description = "StringList Nullable Form 파라미터",
            required = false,
            example = "[\"testString1\", \"testString2\"]"
        )
        @JsonProperty("requestFormStringListNullable")
        val requestFormStringListNullable: List<String>?
    )

    data class Api7PostRequestTestWithFormTypeRequestBodyOutputVo(
        @Schema(description = "입력한 String Form 파라미터", required = true, example = "testString")
        @JsonProperty("requestFormString")
        val requestFormString: String,
        @Schema(description = "입력한 String Nullable Form 파라미터", required = false, example = "testString")
        @JsonProperty("requestFormStringNullable")
        val requestFormStringNullable: String?,
        @Schema(description = "입력한 Int Form 파라미터", required = true, example = "1")
        @JsonProperty("requestFormInt")
        val requestFormInt: Int,
        @Schema(description = "입력한 Int Nullable Form 파라미터", required = false, example = "1")
        @JsonProperty("requestFormIntNullable")
        val requestFormIntNullable: Int?,
        @Schema(description = "입력한 Double Form 파라미터", required = true, example = "1.1")
        @JsonProperty("requestFormDouble")
        val requestFormDouble: Double,
        @Schema(description = "입력한 Double Nullable Form 파라미터", required = false, example = "1.1")
        @JsonProperty("requestFormDoubleNullable")
        val requestFormDoubleNullable: Double?,
        @Schema(description = "입력한 Boolean Form 파라미터", required = true, example = "true")
        @JsonProperty("requestFormBoolean")
        val requestFormBoolean: Boolean,
        @Schema(description = "입력한 Boolean Nullable Form 파라미터", required = false, example = "true")
        @JsonProperty("requestFormBooleanNullable")
        val requestFormBooleanNullable: Boolean?,
        @Schema(
            description = "입력한 StringList Form 파라미터",
            required = true,
            example = "[\"testString1\", \"testString2\"]"
        )
        @JsonProperty("requestFormStringList")
        val requestFormStringList: List<String>,
        @Schema(
            description = "입력한 StringList Nullable Form 파라미터",
            required = false,
            example = "[\"testString1\", \"testString2\"]"
        )
        @JsonProperty("requestFormStringListNullable")
        val requestFormStringListNullable: List<String>?
    )


    ////
    @Operation(
        summary = "N8 : Post 요청 테스트 (multipart/form-data)",
        description = "multipart/form-data 형태의 Request Body 를 받는 Post 메소드 요청 테스트\n\n" +
                "MultipartFile 파라미터가 null 이 아니라면 저장\n\n"
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "정상 동작"
            )
        ]
    )
    @PostMapping(
        path = ["/post-request-multipart-form-data"],
        consumes = [MediaType.MULTIPART_FORM_DATA_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    @ResponseBody
    fun api8PostRequestTestWithMultipartFormTypeRequestBody(
        @Parameter(hidden = true)
        httpServletResponse: HttpServletResponse,
        @ModelAttribute
        @RequestBody
        inputVo: Api8PostRequestTestWithMultipartFormTypeRequestBodyInputVo
    ): Api8PostRequestTestWithMultipartFormTypeRequestBodyOutputVo? {
        return service.api8PostRequestTestWithMultipartFormTypeRequestBody(httpServletResponse, inputVo)
    }

    data class Api8PostRequestTestWithMultipartFormTypeRequestBodyInputVo(
        @Schema(description = "String Form 파라미터", required = true, example = "testString")
        @JsonProperty("requestFormString")
        val requestFormString: String,
        @Schema(description = "String Nullable Form 파라미터", required = false, example = "testString")
        @JsonProperty("requestFormStringNullable")
        val requestFormStringNullable: String?,
        @Schema(description = "Int Form 파라미터", required = true, example = "1")
        @JsonProperty("requestFormInt")
        val requestFormInt: Int,
        @Schema(description = "Int Nullable Form 파라미터", required = false, example = "1")
        @JsonProperty("requestFormIntNullable")
        val requestFormIntNullable: Int?,
        @Schema(description = "Double Form 파라미터", required = true, example = "1.1")
        @JsonProperty("requestFormDouble")
        val requestFormDouble: Double,
        @Schema(description = "Double Nullable Form 파라미터", required = false, example = "1.1")
        @JsonProperty("requestFormDoubleNullable")
        val requestFormDoubleNullable: Double?,
        @Schema(description = "Boolean Form 파라미터", required = true, example = "true")
        @JsonProperty("requestFormBoolean")
        val requestFormBoolean: Boolean,
        @Schema(description = "Boolean Nullable Form 파라미터", required = false, example = "true")
        @JsonProperty("requestFormBooleanNullable")
        val requestFormBooleanNullable: Boolean?,
        @Schema(description = "StringList Form 파라미터", required = true, example = "[\"testString1\", \"testString2\"]")
        @JsonProperty("requestFormStringList")
        val requestFormStringList: List<String>,
        @Schema(
            description = "StringList Nullable Form 파라미터",
            required = false,
            example = "[\"testString1\", \"testString2\"]"
        )
        @JsonProperty("requestFormStringListNullable")
        val requestFormStringListNullable: List<String>?,
        @Schema(description = "멀티 파트 파일", required = true)
        @JsonProperty("multipartFile")
        val multipartFile: MultipartFile,
        @Schema(description = "멀티 파트 파일 Nullable", required = false)
        @JsonProperty("multipartFileNullable")
        val multipartFileNullable: MultipartFile?
    )

    data class Api8PostRequestTestWithMultipartFormTypeRequestBodyOutputVo(
        @Schema(description = "입력한 String Form 파라미터", required = true, example = "testString")
        @JsonProperty("requestFormString")
        val requestFormString: String,
        @Schema(description = "입력한 String Nullable Form 파라미터", required = false, example = "testString")
        @JsonProperty("requestFormStringNullable")
        val requestFormStringNullable: String?,
        @Schema(description = "입력한 Int Form 파라미터", required = true, example = "1")
        @JsonProperty("requestFormInt")
        val requestFormInt: Int,
        @Schema(description = "입력한 Int Nullable Form 파라미터", required = false, example = "1")
        @JsonProperty("requestFormIntNullable")
        val requestFormIntNullable: Int?,
        @Schema(description = "입력한 Double Form 파라미터", required = true, example = "1.1")
        @JsonProperty("requestFormDouble")
        val requestFormDouble: Double,
        @Schema(description = "입력한 Double Nullable Form 파라미터", required = false, example = "1.1")
        @JsonProperty("requestFormDoubleNullable")
        val requestFormDoubleNullable: Double?,
        @Schema(description = "입력한 Boolean Form 파라미터", required = true, example = "true")
        @JsonProperty("requestFormBoolean")
        val requestFormBoolean: Boolean,
        @Schema(description = "입력한 Boolean Nullable Form 파라미터", required = false, example = "true")
        @JsonProperty("requestFormBooleanNullable")
        val requestFormBooleanNullable: Boolean?,
        @Schema(
            description = "입력한 StringList Form 파라미터",
            required = true,
            example = "[\"testString1\", \"testString2\"]"
        )
        @JsonProperty("requestFormStringList")
        val requestFormStringList: List<String>,
        @Schema(
            description = "입력한 StringList Nullable Form 파라미터",
            required = false,
            example = "[\"testString1\", \"testString2\"]"
        )
        @JsonProperty("requestFormStringListNullable")
        val requestFormStringListNullable: List<String>?
    )


    ////
    @Operation(
        summary = "N9 : Post 요청 테스트2 (multipart/form-data)",
        description = "multipart/form-data 형태의 Request Body 를 받는 Post 메소드 요청 테스트(Multipart File List)\n\n" +
                "파일 리스트가 null 이 아니라면 저장\n\n"
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "정상 동작"
            )
        ]
    )
    @PostMapping(
        path = ["/post-request-multipart-form-data2"],
        consumes = [MediaType.MULTIPART_FORM_DATA_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    @ResponseBody
    fun api9PostRequestTestWithMultipartFormTypeRequestBody2(
        @Parameter(hidden = true)
        httpServletResponse: HttpServletResponse,
        @ModelAttribute
        @RequestBody
        inputVo: Api9PostRequestTestWithMultipartFormTypeRequestBody2InputVo
    ): Api9PostRequestTestWithMultipartFormTypeRequestBody2OutputVo? {
        return service.api9PostRequestTestWithMultipartFormTypeRequestBody2(httpServletResponse, inputVo)
    }

    data class Api9PostRequestTestWithMultipartFormTypeRequestBody2InputVo(
        @Schema(description = "String Form 파라미터", required = true, example = "testString")
        @JsonProperty("requestFormString")
        val requestFormString: String,
        @Schema(description = "String Nullable Form 파라미터", required = false, example = "testString")
        @JsonProperty("requestFormStringNullable")
        val requestFormStringNullable: String?,
        @Schema(description = "Int Form 파라미터", required = true, example = "1")
        @JsonProperty("requestFormInt")
        val requestFormInt: Int,
        @Schema(description = "Int Nullable Form 파라미터", required = false, example = "1")
        @JsonProperty("requestFormIntNullable")
        val requestFormIntNullable: Int?,
        @Schema(description = "Double Form 파라미터", required = true, example = "1.1")
        @JsonProperty("requestFormDouble")
        val requestFormDouble: Double,
        @Schema(description = "Double Nullable Form 파라미터", required = false, example = "1.1")
        @JsonProperty("requestFormDoubleNullable")
        val requestFormDoubleNullable: Double?,
        @Schema(description = "Boolean Form 파라미터", required = true, example = "true")
        @JsonProperty("requestFormBoolean")
        val requestFormBoolean: Boolean,
        @Schema(description = "Boolean Nullable Form 파라미터", required = false, example = "true")
        @JsonProperty("requestFormBooleanNullable")
        val requestFormBooleanNullable: Boolean?,
        @Schema(description = "StringList Form 파라미터", required = true, example = "[\"testString1\", \"testString2\"]")
        @JsonProperty("requestFormStringList")
        val requestFormStringList: List<String>,
        @Schema(
            description = "StringList Nullable Form 파라미터",
            required = false,
            example = "[\"testString1\", \"testString2\"]"
        )
        @JsonProperty("requestFormStringListNullable")
        val requestFormStringListNullable: List<String>?,
        @Schema(description = "멀티 파트 파일", required = true)
        @JsonProperty("multipartFileList")
        val multipartFileList: List<MultipartFile>,
        @Schema(description = "멀티 파트 파일 Nullable", required = false)
        @JsonProperty("multipartFileNullableList")
        val multipartFileNullableList: List<MultipartFile>?
    )

    data class Api9PostRequestTestWithMultipartFormTypeRequestBody2OutputVo(
        @Schema(description = "입력한 String Form 파라미터", required = true, example = "testString")
        @JsonProperty("requestFormString")
        val requestFormString: String,
        @Schema(description = "입력한 String Nullable Form 파라미터", required = false, example = "testString")
        @JsonProperty("requestFormStringNullable")
        val requestFormStringNullable: String?,
        @Schema(description = "입력한 Int Form 파라미터", required = true, example = "1")
        @JsonProperty("requestFormInt")
        val requestFormInt: Int,
        @Schema(description = "입력한 Int Nullable Form 파라미터", required = false, example = "1")
        @JsonProperty("requestFormIntNullable")
        val requestFormIntNullable: Int?,
        @Schema(description = "입력한 Double Form 파라미터", required = true, example = "1.1")
        @JsonProperty("requestFormDouble")
        val requestFormDouble: Double,
        @Schema(description = "입력한 Double Nullable Form 파라미터", required = false, example = "1.1")
        @JsonProperty("requestFormDoubleNullable")
        val requestFormDoubleNullable: Double?,
        @Schema(description = "입력한 Boolean Form 파라미터", required = true, example = "true")
        @JsonProperty("requestFormBoolean")
        val requestFormBoolean: Boolean,
        @Schema(description = "입력한 Boolean Nullable Form 파라미터", required = false, example = "true")
        @JsonProperty("requestFormBooleanNullable")
        val requestFormBooleanNullable: Boolean?,
        @Schema(
            description = "입력한 StringList Form 파라미터",
            required = true,
            example = "[\"testString1\", \"testString2\"]"
        )
        @JsonProperty("requestFormStringList")
        val requestFormStringList: List<String>,
        @Schema(
            description = "입력한 StringList Nullable Form 파라미터",
            required = false,
            example = "[\"testString1\", \"testString2\"]"
        )
        @JsonProperty("requestFormStringListNullable")
        val requestFormStringListNullable: List<String>?
    )


    ////
    @Operation(
        summary = "N10 : Post 요청 테스트 (multipart/form-data - JsonString)",
        description = "multipart/form-data 형태의 Request Body 를 받는 Post 메소드 요청 테스트\n\n" +
                "Form Data 의 Input Body 에는 Object 리스트 타입은 사용 불가능입니다.\n\n" +
                "Object 리스트 타입을 사용한다면, Json String 타입으로 객체를 받아서 파싱하여 사용하는 방식을 사용합니다.\n\n" +
                "아래 예시에서는 모두 JsonString 형식으로 만들었지만, ObjectList 타입만 이런식으로 처리하세요.\n\n"
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "정상 동작"
            )
        ]
    )
    @PostMapping(
        path = ["/post-request-multipart-form-data-json"],
        consumes = [MediaType.MULTIPART_FORM_DATA_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    @ResponseBody
    fun api10PostRequestTestWithMultipartFormTypeRequestBody3(
        @Parameter(hidden = true)
        httpServletResponse: HttpServletResponse,
        @ModelAttribute
        @RequestBody
        inputVo: Api10PostRequestTestWithMultipartFormTypeRequestBody3InputVo
    ): Api10PostRequestTestWithMultipartFormTypeRequestBody3OutputVo? {
        return service.api10PostRequestTestWithMultipartFormTypeRequestBody3(httpServletResponse, inputVo)
    }

    data class Api10PostRequestTestWithMultipartFormTypeRequestBody3InputVo(
        @Schema(
            description = "json 형식의 문자열\n\n" +
                    "        data class InputJsonObject(\n" +
                    "            @Schema(description = \"String Form 파라미터\", required = true, example = \"testString\")\n" +
                    "            @JsonProperty(\"requestFormString\")\n" +
                    "            val requestFormString: String,\n" +
                    "            @Schema(description = \"String Nullable Form 파라미터\", required = false, example = \"testString\")\n" +
                    "            @JsonProperty(\"requestFormStringNullable\")\n" +
                    "            val requestFormStringNullable: String?,\n" +
                    "            @Schema(description = \"Int Form 파라미터\", required = true, example = \"1\")\n" +
                    "            @JsonProperty(\"requestFormInt\")\n" +
                    "            val requestFormInt: Int,\n" +
                    "            @Schema(description = \"Int Nullable Form 파라미터\", required = false, example = \"1\")\n" +
                    "            @JsonProperty(\"requestFormIntNullable\")\n" +
                    "            val requestFormIntNullable: Int?,\n" +
                    "            @Schema(description = \"Double Form 파라미터\", required = true, example = \"1.1\")\n" +
                    "            @JsonProperty(\"requestFormDouble\")\n" +
                    "            val requestFormDouble: Double,\n" +
                    "            @Schema(description = \"Double Nullable Form 파라미터\", required = false, example = \"1.1\")\n" +
                    "            @JsonProperty(\"requestFormDoubleNullable\")\n" +
                    "            val requestFormDoubleNullable: Double?,\n" +
                    "            @Schema(description = \"Boolean Form 파라미터\", required = true, example = \"true\")\n" +
                    "            @JsonProperty(\"requestFormBoolean\")\n" +
                    "            val requestFormBoolean: Boolean,\n" +
                    "            @Schema(description = \"Boolean Nullable Form 파라미터\", required = false, example = \"true\")\n" +
                    "            @JsonProperty(\"requestFormBooleanNullable\")\n" +
                    "            val requestFormBooleanNullable: Boolean?,\n" +
                    "            @Schema(description = \"StringList Form 파라미터\", required = true, example = \"[\\\"testString1\\\", \\\"testString2\\\"]\")\n" +
                    "            @JsonProperty(\"requestFormStringList\")\n" +
                    "            val requestFormStringList: List<String>,\n" +
                    "            @Schema(\n" +
                    "                description = \"StringList Nullable Form 파라미터\",\n" +
                    "                required = false,\n" +
                    "                example = \"[\\\"testString1\\\", \\\"testString2\\\"]\"\n" +
                    "            )\n" +
                    "            @JsonProperty(\"requestFormStringListNullable\")\n" +
                    "            val requestFormStringListNullable: List<String>?\n" +
                    "        )",
            required = true,
            example = "{\n" +
                    "  \"requestFormString\": \"testString\",\n" +
                    "  \"requestFormStringNullable\": null,\n" +
                    "  \"requestFormInt\": 1,\n" +
                    "  \"requestFormIntNullable\": null,\n" +
                    "  \"requestFormDouble\": 1.1,\n" +
                    "  \"requestFormDoubleNullable\": null,\n" +
                    "  \"requestFormBoolean\": true,\n" +
                    "  \"requestFormBooleanNullable\": null,\n" +
                    "  \"requestFormStringList\": [\n" +
                    "    \"testString1\",\n" +
                    "    \"testString2\"\n" +
                    "  ],\n" +
                    "  \"requestFormStringListNullable\": null\n" +
                    "}"
        )
        @JsonProperty("jsonString")
        val jsonString: String,
        @Schema(description = "멀티 파트 파일", required = true)
        @JsonProperty("multipartFile")
        val multipartFile: MultipartFile,
        @Schema(description = "멀티 파트 파일 Nullable", required = false)
        @JsonProperty("multipartFileNullable")
        val multipartFileNullable: MultipartFile?
    ) {
        @Schema(description = "Json String Object")
        data class InputJsonObject(
            @Schema(description = "String Form 파라미터", required = true, example = "testString")
            @JsonProperty("requestFormString")
            val requestFormString: String,
            @Schema(description = "String Nullable Form 파라미터", required = false, example = "testString")
            @JsonProperty("requestFormStringNullable")
            val requestFormStringNullable: String?,
            @Schema(description = "Int Form 파라미터", required = true, example = "1")
            @JsonProperty("requestFormInt")
            val requestFormInt: Int,
            @Schema(description = "Int Nullable Form 파라미터", required = false, example = "1")
            @JsonProperty("requestFormIntNullable")
            val requestFormIntNullable: Int?,
            @Schema(description = "Double Form 파라미터", required = true, example = "1.1")
            @JsonProperty("requestFormDouble")
            val requestFormDouble: Double,
            @Schema(description = "Double Nullable Form 파라미터", required = false, example = "1.1")
            @JsonProperty("requestFormDoubleNullable")
            val requestFormDoubleNullable: Double?,
            @Schema(description = "Boolean Form 파라미터", required = true, example = "true")
            @JsonProperty("requestFormBoolean")
            val requestFormBoolean: Boolean,
            @Schema(description = "Boolean Nullable Form 파라미터", required = false, example = "true")
            @JsonProperty("requestFormBooleanNullable")
            val requestFormBooleanNullable: Boolean?,
            @Schema(
                description = "StringList Form 파라미터",
                required = true,
                example = "[\"testString1\", \"testString2\"]"
            )
            @JsonProperty("requestFormStringList")
            val requestFormStringList: List<String>,
            @Schema(
                description = "StringList Nullable Form 파라미터",
                required = false,
                example = "[\"testString1\", \"testString2\"]"
            )
            @JsonProperty("requestFormStringListNullable")
            val requestFormStringListNullable: List<String>?
        )
    }

    data class Api10PostRequestTestWithMultipartFormTypeRequestBody3OutputVo(
        @Schema(description = "입력한 String Form 파라미터", required = true, example = "testString")
        @JsonProperty("requestFormString")
        val requestFormString: String,
        @Schema(description = "입력한 String Nullable Form 파라미터", required = false, example = "testString")
        @JsonProperty("requestFormStringNullable")
        val requestFormStringNullable: String?,
        @Schema(description = "입력한 Int Form 파라미터", required = true, example = "1")
        @JsonProperty("requestFormInt")
        val requestFormInt: Int,
        @Schema(description = "입력한 Int Nullable Form 파라미터", required = false, example = "1")
        @JsonProperty("requestFormIntNullable")
        val requestFormIntNullable: Int?,
        @Schema(description = "입력한 Double Form 파라미터", required = true, example = "1.1")
        @JsonProperty("requestFormDouble")
        val requestFormDouble: Double,
        @Schema(description = "입력한 Double Nullable Form 파라미터", required = false, example = "1.1")
        @JsonProperty("requestFormDoubleNullable")
        val requestFormDoubleNullable: Double?,
        @Schema(description = "입력한 Boolean Form 파라미터", required = true, example = "true")
        @JsonProperty("requestFormBoolean")
        val requestFormBoolean: Boolean,
        @Schema(description = "입력한 Boolean Nullable Form 파라미터", required = false, example = "true")
        @JsonProperty("requestFormBooleanNullable")
        val requestFormBooleanNullable: Boolean?,
        @Schema(
            description = "입력한 StringList Form 파라미터",
            required = true,
            example = "[\"testString1\", \"testString2\"]"
        )
        @JsonProperty("requestFormStringList")
        val requestFormStringList: List<String>,
        @Schema(
            description = "입력한 StringList Nullable Form 파라미터",
            required = false,
            example = "[\"testString1\", \"testString2\"]"
        )
        @JsonProperty("requestFormStringListNullable")
        val requestFormStringListNullable: List<String>?
    )


    ////
    @Operation(
        summary = "N11 : 인위적 에러 발생 테스트",
        description = "요청 받으면 인위적인 서버 에러를 발생시킵니다.(Http Response Status 500)\n\n"
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "정상 동작"
            )
        ]
    )
    @PostMapping(
        path = ["/generate-error"],
        consumes = [MediaType.ALL_VALUE],
        produces = [MediaType.ALL_VALUE]
    )
    @ResponseBody
    fun api11GenerateErrorTest(
        @Parameter(hidden = true)
        httpServletResponse: HttpServletResponse
    ) {
        service.api11GenerateErrorTest(httpServletResponse)
    }


    ////
    @Operation(
        summary = "N12 : 결과 코드 발생 테스트",
        description = "Response Header 에 api-result-code 를 반환하는 테스트 API\n\n"
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "정상 동작"
            ),
            ApiResponse(
                responseCode = "204",
                content = [Content()],
                description = "Response Body 가 없습니다.\n\n" +
                        "Response Headers 를 확인하세요.",
                headers = [
                    Header(
                        name = "api-result-code",
                        description = "(Response Code 반환 원인) - Required\n\n" +
                                "1 : errorType 을 A 로 보냈습니다.\n\n" +
                                "2 : errorType 을 B 로 보냈습니다.\n\n" +
                                "3 : errorType 을 C 로 보냈습니다.\n\n",
                        schema = Schema(type = "string")
                    )
                ]
            )
        ]
    )
    @PostMapping(
        path = ["/api-result-code-test"],
        consumes = [MediaType.ALL_VALUE],
        produces = [MediaType.ALL_VALUE]
    )
    @ResponseBody
    fun api12ReturnResultCodeThroughHeaders(
        @Parameter(hidden = true)
        httpServletResponse: HttpServletResponse,
        @Parameter(name = "errorType", description = "정상적이지 않은 상황을 만들도록 가정된 변수입니다.", example = "A")
        @RequestParam("errorType")
        errorType: Api12ReturnResultCodeThroughHeadersErrorTypeEnum?
    ) {
        service.api12ReturnResultCodeThroughHeaders(httpServletResponse, errorType)
    }

    enum class Api12ReturnResultCodeThroughHeadersErrorTypeEnum {
        A,
        B,
        C
    }


    ////
    @Operation(
        summary = "N13 : 인위적 응답 지연 테스트",
        description = "임의로 응답 시간을 지연시킵니다.\n\n"
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "정상 동작"
            )
        ]
    )
    @PostMapping(
        path = ["/time-delay-test"],
        consumes = [MediaType.ALL_VALUE],
        produces = [MediaType.ALL_VALUE]
    )
    @ResponseBody
    fun api13ResponseDelayTest(
        @Parameter(hidden = true)
        httpServletResponse: HttpServletResponse,
        @Parameter(name = "delayTimeSec", description = "지연 시간(초)", example = "1")
        @RequestParam("delayTimeSec")
        delayTimeSec: Long
    ) {
        service.api13ResponseDelayTest(httpServletResponse, delayTimeSec)
    }


    ////
    // UTF-8 설정을 적용하려면,
    // produces = ["text/plain;charset=utf-8"]
    // produces = ["text/html;charset=utf-8"]
    @Operation(
        summary = "N14 : text/string 반환 샘플",
        description = "text/string 형식의 Response Body 를 반환합니다.\n\n"
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
        path = ["/return-text-string"],
        consumes = [MediaType.ALL_VALUE],
        produces = [MediaType.TEXT_PLAIN_VALUE]
    )
    @ResponseBody
    fun api14ReturnTextStringTest(
        @Parameter(hidden = true)
        httpServletResponse: HttpServletResponse
    ): String? {
        return service.api14ReturnTextStringTest(
            httpServletResponse
        )
    }


    ////
    @Operation(
        summary = "N15 : text/html 반환 샘플",
        description = "text/html 형식의 Response Body 를 반환합니다.\n\n"
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
        path = ["/return-text-html"],
        consumes = [MediaType.ALL_VALUE],
        produces = [MediaType.TEXT_HTML_VALUE]
    )
    @ResponseBody
    fun api15ReturnTextHtmlTest(
        @Parameter(hidden = true)
        httpServletResponse: HttpServletResponse
    ): ModelAndView? {
        return service.api15ReturnTextHtmlTest(
            httpServletResponse
        )
    }


    ////
    @Operation(
        summary = "N16 : byte 반환 샘플",
        description = " byte array('a', .. , 'f') 에서 아래와 같은 요청으로 원하는 바이트를 요청 가능\n\n" +
                "    >> curl http://localhost:8080/service1/tk/v1/request-test/byte -i -H \"Range: bytes=2-4\"\n\n"
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
        path = ["/byte"],
        consumes = [MediaType.ALL_VALUE],
        produces = [MediaType.TEXT_PLAIN_VALUE]
    )
    @ResponseBody
    fun api16ReturnByteDataTest(
        @Parameter(hidden = true)
        httpServletResponse: HttpServletResponse,
        @Parameter(
            name = "Range",
            description = "byte array('a', 'b', 'c', 'd', 'e', 'f') 중 가져올 범위(0 부터 시작되는 인덱스)",
            example = "Bytes=2-4"
        )
        @RequestHeader("Range")
        range: String
    ): Resource? {
        return service.api16ReturnByteDataTest(httpServletResponse)
    }


    ////
    @Operation(
        summary = "N17 : 비디오 스트리밍 샘플",
        description = "비디오 스트리밍 샘플\n\n" +
                "테스트는 프로젝트 파일 경로의 external_files/files_for_api_test/html_file_sample 안의 video-streaming.html 파일을 사용하세요.\n\n"
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
        path = ["/video-streaming"],
        consumes = [MediaType.ALL_VALUE],
        produces = [MediaType.APPLICATION_OCTET_STREAM_VALUE]
    )
    @ResponseBody
    fun api17VideoStreamingTest(
        @Parameter(hidden = true)
        httpServletResponse: HttpServletResponse,
        @RequestParam(value = "videoHeight")
        videoHeight: Api17VideoStreamingTestVideoHeight // 비디오 해상도 높이
    ): Resource? {
        return service.api17VideoStreamingTest(videoHeight, httpServletResponse)
    }

    enum class Api17VideoStreamingTestVideoHeight {
        H240,
        H360,
        H480,
        H720
    }


    ////
    @Operation(
        summary = "N18 : 오디오 스트리밍 샘플",
        description = "오디오 스트리밍 샘플\n\n" +
                "테스트는 프로젝트 파일 경로의 external_files/files_for_api_test/html_file_sample 안의 audio-streaming.html 파일을 사용하세요.\n\n"
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
        path = ["/audio-streaming"],
        consumes = [MediaType.ALL_VALUE],
        produces = [MediaType.APPLICATION_OCTET_STREAM_VALUE]
    )
    @ResponseBody
    fun api18AudioStreamingTest(
        @Parameter(hidden = true)
        httpServletResponse: HttpServletResponse
    ): Resource? {
        return service.api18AudioStreamingTest(httpServletResponse)
    }


    ////
    @Operation(
        summary = "N19 : 비동기 처리 결과 반환 샘플",
        description = "API 호출시 함수 내에서 별도 스레드로 작업을 수행하고,\n\n" +
                "비동기 작업 완료 후 그 처리 결과가 반환됨\n\n"
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
        path = ["/async-result"],
        consumes = [MediaType.ALL_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    @ResponseBody
    fun api19AsynchronousResponseTest(
        httpServletResponse: HttpServletResponse
    ): DeferredResult<Api19AsynchronousResponseTestOutputVo>? {
        return service.api19AsynchronousResponseTest(httpServletResponse)
    }

    data class Api19AsynchronousResponseTestOutputVo(
        @Schema(description = "결과 메세지", required = true, example = "n 초 경과 후 반환했습니다.")
        @JsonProperty("resultMessage")
        val resultMessage: String
    )


    ////
    @Operation(
        summary = "N20 : 클라이언트가 특정 SSE 이벤트를 구독",
        description = "구독 수신 중 연결이 끊어질 경우, 클라이언트가 헤더에 Last-Event-ID 라는 값을 넣어서 다시 요청함\n\n" +
                "!주의점! : 로깅 필터와 충돌되므로, 꼭 요청 헤더에는 Accept:text/event-stream 를 넣어서 요청을 해야함 (이것으로 SSE 요청임을 필터가 확인함)\n\n" +
                "테스트는, CMD 를 열고, \n\n" +
                "    >>> curl -N --http2 -H \"Accept:text/event-stream\" http://127.0.0.1:8080/service1/tk/v1/request-test/sse-test/subscribe\n\n" +
                "혹은, 프로젝트 파일 경로의 external_files/files_for_api_test/html_file_sample 안의 sse-test.html 파일을 사용하세요. (cors 설정 필요)\n\n"
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
        path = ["/sse-test/subscribe"],
        consumes = [MediaType.ALL_VALUE],
        produces = [MediaType.TEXT_EVENT_STREAM_VALUE]
    )
    @ResponseBody
    fun api20SseTestSubscribe(
        httpServletResponse: HttpServletResponse,
        @Parameter(name = "Last-Event-ID", description = "멤버가 수신한 마지막 event id")
        @RequestHeader(value = "Last-Event-ID")
        lastSseEventId: String?
    ): SseEmitter? {
        return service.api20SseTestSubscribe(httpServletResponse, lastSseEventId)
    }


    ////
    @Operation(
        summary = "N21 : SSE 이벤트 전송 트리거 테스트",
        description = "어떠한 사건이 일어나면 알림을 위하여 SSE 이벤트 전송을 한다고 가정\n\n"
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "정상 동작"
            )
        ]
    )
    @PostMapping(
        path = ["/sse-test/event-trigger"],
        consumes = [MediaType.ALL_VALUE],
        produces = [MediaType.ALL_VALUE]
    )
    @ResponseBody
    fun api21SseTestEventTrigger(
        httpServletResponse: HttpServletResponse
    ) {
        service.api21SseTestEventTrigger(httpServletResponse)
    }


    ////
    /*
         결론적으로 아래 파라미터는, Query Param 의 경우는 리스트 입력이 ?stringList=string&stringList=string 이런식이므로,
         리스트 파라미터가 Not NULL 이라면 빈 리스트를 보낼 수 없으며,
         Body Param 의 경우는 JSON 으로 "requestBodyStringList" : [] 이렇게 보내면 빈 리스트를 보낼 수 있습니다.
     */
    @Operation(
        summary = "N22 : 빈 리스트 받기 테스트",
        description = "Query 파라미터에 NotNull List 와 Body 파라미터의 NotNull List 에 빈 리스트를 넣었을 때의 현상을 관측하기 위한 테스트\n\n"
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "정상 동작"
            )
        ]
    )
    @PostMapping(
        path = ["/empty-list-param-test"],
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    @ResponseBody
    fun api22EmptyListRequestTest(
        @Parameter(hidden = true)
        httpServletResponse: HttpServletResponse,
        @Parameter(
            name = "stringList",
            description = "String List Query 파라미터",
            example = "[\"testString1\", \"testString2\"]"
        )
        @RequestParam("stringList")
        stringList: List<String>,
        @RequestBody
        inputVo: Api22EmptyListRequestTestInputVo
    ): Api22EmptyListRequestTestOutputVo? {
        return service.api22EmptyListRequestTest(
            httpServletResponse,
            stringList,
            inputVo
        )
    }

    data class Api22EmptyListRequestTestInputVo(
        @Schema(description = "StringList Body 파라미터", required = true, example = "[\"testString1\", \"testString2\"]")
        @JsonProperty("requestBodyStringList")
        val requestBodyStringList: List<String>
    )

    data class Api22EmptyListRequestTestOutputVo(
        @Schema(description = "StringList Query 파라미터", required = true, example = "[\"testString1\", \"testString2\"]")
        @JsonProperty("requestQueryStringList")
        val requestQueryStringList: List<String>,
        @Schema(description = "StringList Body 파라미터", required = true, example = "[\"testString1\", \"testString2\"]")
        @JsonProperty("requestBodyStringList")
        val requestBodyStringList: List<String>
    )
}