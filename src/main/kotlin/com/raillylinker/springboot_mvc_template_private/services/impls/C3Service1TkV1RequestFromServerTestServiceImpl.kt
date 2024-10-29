package com.raillylinker.springboot_mvc_template_private.services.impls

import com.google.gson.Gson
import com.raillylinker.springboot_mvc_template_private.controllers.C3Service1TkV1RequestFromServerTestController
import com.raillylinker.springboot_mvc_template_private.data_sources.retrofit2_classes.RepositoryNetworkRetrofit2
import com.raillylinker.springboot_mvc_template_private.data_sources.retrofit2_classes.request_apis.LocalHostRequestApi
import com.raillylinker.springboot_mvc_template_private.services.C3Service1TkV1RequestFromServerTestService
import jakarta.servlet.http.HttpServletResponse
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import java.io.File
import java.net.SocketTimeoutException
import java.nio.file.Paths

@Service
class C3Service1TkV1RequestFromServerTestServiceImpl(
    // (프로젝트 실행시 사용 설정한 프로필명 (ex : dev8080, prod80, local8080, 설정 안하면 default 반환))
    @Value("\${spring.profiles.active:default}") private var activeProfile: String
) : C3Service1TkV1RequestFromServerTestService {
    // <멤버 변수 공간>
    private val classLogger: Logger = LoggerFactory.getLogger(this::class.java)

    // Retrofit2 요청 객체
    val networkRetrofit2: RepositoryNetworkRetrofit2 = RepositoryNetworkRetrofit2.getInstance()


    // ---------------------------------------------------------------------------------------------
    // <공개 메소드 공간>
    override fun api1BasicRequestTest(httpServletResponse: HttpServletResponse): String? {
        try {
            // 네트워크 요청
            val responseObj = networkRetrofit2.localHostRequestApi.getService1TkV1RequestTest().execute()

            return if (responseObj.code() == 200) {
                // 정상 동작
                httpServletResponse.status = HttpStatus.OK.value()
                responseObj.body()!!
            } else {
                // 반환될 일 없는 상태 = 서버측 에러
                httpServletResponse.status = HttpStatus.NO_CONTENT.value()
                httpServletResponse.setHeader("api-result-code", "2")
                return null
            }
        } catch (e: SocketTimeoutException) {
            // 타임아웃 에러 처리 = 런타임에서 처리해야하는 유일한 클라이언트 측 에러
            httpServletResponse.status = HttpStatus.NO_CONTENT.value()
            httpServletResponse.setHeader("api-result-code", "1")
            return null
        }
    }


    ////
    override fun api2RedirectTest(httpServletResponse: HttpServletResponse): String? {
        try {
            // 네트워크 요청
            val responseObj = networkRetrofit2.localHostRequestApi.getService1TkV1RequestTestRedirectToBlank().execute()

            return if (responseObj.code() == 200) {
                // 정상 동작
                httpServletResponse.status = HttpStatus.OK.value()
                responseObj.body()!!
            } else {
                // 반환될 일 없는 상태 = 서버측 에러
                httpServletResponse.status = HttpStatus.NO_CONTENT.value()
                httpServletResponse.setHeader("api-result-code", "2")
                return null
            }
        } catch (e: SocketTimeoutException) {
            // 타임아웃 에러 처리 = 런타임에서 처리해야하는 유일한 클라이언트 측 에러
            httpServletResponse.status = HttpStatus.NO_CONTENT.value()
            httpServletResponse.setHeader("api-result-code", "1")
            return null
        }
    }


    ////
    override fun api3ForwardTest(httpServletResponse: HttpServletResponse): String? {
        try {
            // 네트워크 요청
            val responseObj = networkRetrofit2.localHostRequestApi.getService1TkV1RequestTestForwardToBlank().execute()

            return if (responseObj.code() == 200) {
                // 정상 동작
                httpServletResponse.status = HttpStatus.OK.value()
                responseObj.body()!!
            } else {
                // 반환될 일 없는 상태 = 서버측 에러
                httpServletResponse.status = HttpStatus.NO_CONTENT.value()
                httpServletResponse.setHeader("api-result-code", "2")
                return null
            }
        } catch (e: SocketTimeoutException) {
            // 타임아웃 에러 처리 = 런타임에서 처리해야하는 유일한 클라이언트 측 에러
            httpServletResponse.status = HttpStatus.NO_CONTENT.value()
            httpServletResponse.setHeader("api-result-code", "1")
            return null
        }
    }


    ////
    override fun api4GetRequestTest(httpServletResponse: HttpServletResponse): C3Service1TkV1RequestFromServerTestController.Api4GetRequestTestOutputVo? {
        try {
            // 네트워크 요청
            val responseObj = networkRetrofit2.localHostRequestApi.getService1TkV1RequestTestGetRequest(
                "paramFromServer",
                null,
                1,
                null,
                1.1,
                null,
                true,
                null,
                listOf("paramFromServer"),
                null
            ).execute()

            return if (responseObj.code() == 200) {
                // 정상 동작
                httpServletResponse.status = HttpStatus.OK.value()
                val responseBody = responseObj.body()!!
                C3Service1TkV1RequestFromServerTestController.Api4GetRequestTestOutputVo(
                    responseBody.queryParamString,
                    responseBody.queryParamStringNullable,
                    responseBody.queryParamInt,
                    responseBody.queryParamIntNullable,
                    responseBody.queryParamDouble,
                    responseBody.queryParamDoubleNullable,
                    responseBody.queryParamBoolean,
                    responseBody.queryParamBooleanNullable,
                    responseBody.queryParamStringList,
                    responseBody.queryParamStringListNullable
                )
            } else {
                // 반환될 일 없는 상태 = 서버측 에러
                httpServletResponse.status = HttpStatus.NO_CONTENT.value()
                httpServletResponse.setHeader("api-result-code", "2")
                return null
            }
        } catch (e: SocketTimeoutException) {
            // 타임아웃 에러 처리 = 런타임에서 처리해야하는 유일한 클라이언트 측 에러
            httpServletResponse.status = HttpStatus.NO_CONTENT.value()
            httpServletResponse.setHeader("api-result-code", "1")
            return null
        }
    }


    ////
    override fun api5GetRequestTestWithPathParam(httpServletResponse: HttpServletResponse): C3Service1TkV1RequestFromServerTestController.Api5GetRequestTestWithPathParamOutputVo? {
        try {
            // 네트워크 요청
            val responseObj = networkRetrofit2.localHostRequestApi.getService1TkV1RequestTestGetRequestPathParamInt(
                1234
            ).execute()

            return if (responseObj.code() == 200) {
                // 정상 동작
                httpServletResponse.status = HttpStatus.OK.value()
                val responseBody = responseObj.body()!!
                C3Service1TkV1RequestFromServerTestController.Api5GetRequestTestWithPathParamOutputVo(
                    responseBody.pathParamInt
                )
            } else {
                // 반환될 일 없는 상태 = 서버측 에러
                httpServletResponse.status = HttpStatus.NO_CONTENT.value()
                httpServletResponse.setHeader("api-result-code", "2")
                return null
            }
        } catch (e: SocketTimeoutException) {
            // 타임아웃 에러 처리 = 런타임에서 처리해야하는 유일한 클라이언트 측 에러
            httpServletResponse.status = HttpStatus.NO_CONTENT.value()
            httpServletResponse.setHeader("api-result-code", "1")
            return null
        }
    }


    ////
    override fun api6PostRequestTestWithApplicationJsonTypeRequestBody(httpServletResponse: HttpServletResponse): C3Service1TkV1RequestFromServerTestController.Api6PostRequestTestWithApplicationJsonTypeRequestBodyOutputVo? {
        try {
            // 네트워크 요청
            val responseObj =
                networkRetrofit2.localHostRequestApi.postService1TkV1RequestTestPostRequestApplicationJson(
                    LocalHostRequestApi.PostService1TkV1RequestTestPostRequestApplicationJsonInputVO(
                        "paramFromServer",
                        null,
                        1,
                        null,
                        1.1,
                        null,
                        true,
                        null,
                        listOf("paramFromServer"),
                        null
                    )
                ).execute()

            return if (responseObj.code() == 200) {
                // 정상 동작
                httpServletResponse.status = HttpStatus.OK.value()
                val responseBody = responseObj.body()!!
                C3Service1TkV1RequestFromServerTestController.Api6PostRequestTestWithApplicationJsonTypeRequestBodyOutputVo(
                    responseBody.requestBodyString,
                    responseBody.requestBodyStringNullable,
                    responseBody.requestBodyInt,
                    responseBody.requestBodyIntNullable,
                    responseBody.requestBodyDouble,
                    responseBody.requestBodyDoubleNullable,
                    responseBody.requestBodyBoolean,
                    responseBody.requestBodyBooleanNullable,
                    responseBody.requestBodyStringList,
                    responseBody.requestBodyStringListNullable
                )
            } else {
                // 반환될 일 없는 상태 = 서버측 에러
                httpServletResponse.status = HttpStatus.NO_CONTENT.value()
                httpServletResponse.setHeader("api-result-code", "2")
                return null
            }
        } catch (e: SocketTimeoutException) {
            // 타임아웃 에러 처리 = 런타임에서 처리해야하는 유일한 클라이언트 측 에러
            httpServletResponse.status = HttpStatus.NO_CONTENT.value()
            httpServletResponse.setHeader("api-result-code", "1")
            return null
        }
    }


    ////
    override fun api7PostRequestTestWithFormTypeRequestBody(httpServletResponse: HttpServletResponse): C3Service1TkV1RequestFromServerTestController.Api7PostRequestTestWithFormTypeRequestBodyOutputVo? {
        try {
            // 네트워크 요청
            val responseObj =
                networkRetrofit2.localHostRequestApi.postService1TkV1RequestTestPostRequestXWwwFormUrlencoded(
                    "paramFromServer",
                    null,
                    1,
                    null,
                    1.1,
                    null,
                    true,
                    null,
                    listOf("paramFromServer"),
                    null
                ).execute()

            return if (responseObj.code() == 200) {
                // 정상 동작
                httpServletResponse.status = HttpStatus.OK.value()
                val responseBody = responseObj.body()!!
                C3Service1TkV1RequestFromServerTestController.Api7PostRequestTestWithFormTypeRequestBodyOutputVo(
                    responseBody.requestFormString,
                    responseBody.requestFormStringNullable,
                    responseBody.requestFormInt,
                    responseBody.requestFormIntNullable,
                    responseBody.requestFormDouble,
                    responseBody.requestFormDoubleNullable,
                    responseBody.requestFormBoolean,
                    responseBody.requestFormBooleanNullable,
                    responseBody.requestFormStringList,
                    responseBody.requestFormStringListNullable
                )
            } else {
                // 반환될 일 없는 상태 = 서버측 에러
                httpServletResponse.status = HttpStatus.NO_CONTENT.value()
                httpServletResponse.setHeader("api-result-code", "2")
                return null
            }
        } catch (e: SocketTimeoutException) {
            // 타임아웃 에러 처리 = 런타임에서 처리해야하는 유일한 클라이언트 측 에러
            httpServletResponse.status = HttpStatus.NO_CONTENT.value()
            httpServletResponse.setHeader("api-result-code", "1")
            return null
        }
    }


    ////
    override fun api8PostRequestTestWithMultipartFormTypeRequestBody(httpServletResponse: HttpServletResponse): C3Service1TkV1RequestFromServerTestController.Api8PostRequestTestWithMultipartFormTypeRequestBodyOutputVo? {
        try {
            // 네트워크 요청
            val requestFormString = MultipartBody.Part.createFormData("requestFormString", "paramFromServer")
            val requestFormInt = MultipartBody.Part.createFormData("requestFormInt", 1.toString())
            val requestFormDouble =
                MultipartBody.Part.createFormData("requestFormDouble", 1.1.toString())
            val requestFormBoolean =
                MultipartBody.Part.createFormData("requestFormBoolean", true.toString())

            val requestFormStringList = ArrayList<MultipartBody.Part>()
            for (requestFormString1 in listOf("paramFromServer")) {
                requestFormStringList.add(
                    MultipartBody.Part.createFormData("requestFormStringList", requestFormString1)
                )
            }

            // 전송 하려는 File
            val serverFile =
                Paths.get("${File("").absolutePath}/module-api-sample/src/main/resources/static/for_c3_n8_post_request_test_with_multipart_form_type_request_body/test.txt")
                    .toFile()
            val multipartFileFormData = MultipartBody.Part.createFormData(
                "multipartFile",
                serverFile.name,
                serverFile.asRequestBody(serverFile.toURI().toURL().openConnection().contentType.toMediaTypeOrNull())
            )

            val responseObj =
                networkRetrofit2.localHostRequestApi.postService1TkV1RequestTestPostRequestMultipartFormData(
                    requestFormString,
                    null,
                    requestFormInt,
                    null,
                    requestFormDouble,
                    null,
                    requestFormBoolean,
                    null,
                    requestFormStringList,
                    null,
                    multipartFileFormData,
                    null
                ).execute()

            return if (responseObj.code() == 200) {
                // 정상 동작
                httpServletResponse.status = HttpStatus.OK.value()
                val responseBody = responseObj.body()!!
                C3Service1TkV1RequestFromServerTestController.Api8PostRequestTestWithMultipartFormTypeRequestBodyOutputVo(
                    responseBody.requestFormString,
                    responseBody.requestFormStringNullable,
                    responseBody.requestFormInt,
                    responseBody.requestFormIntNullable,
                    responseBody.requestFormDouble,
                    responseBody.requestFormDoubleNullable,
                    responseBody.requestFormBoolean,
                    responseBody.requestFormBooleanNullable,
                    responseBody.requestFormStringList,
                    responseBody.requestFormStringListNullable
                )
            } else {
                // 반환될 일 없는 상태 = 서버측 에러
                httpServletResponse.status = HttpStatus.NO_CONTENT.value()
                httpServletResponse.setHeader("api-result-code", "2")
                return null
            }
        } catch (e: SocketTimeoutException) {
            // 타임아웃 에러 처리 = 런타임에서 처리해야하는 유일한 클라이언트 측 에러
            httpServletResponse.status = HttpStatus.NO_CONTENT.value()
            httpServletResponse.setHeader("api-result-code", "1")
            return null
        }
    }


    ////
    override fun api9PostRequestTestWithMultipartFormTypeRequestBody2(httpServletResponse: HttpServletResponse): C3Service1TkV1RequestFromServerTestController.Api9PostRequestTestWithMultipartFormTypeRequestBody2OutputVo? {
        try {
            // 네트워크 요청
            val requestFormString = MultipartBody.Part.createFormData("requestFormString", "paramFromServer")
            val requestFormInt = MultipartBody.Part.createFormData("requestFormInt", 1.toString())
            val requestFormDouble =
                MultipartBody.Part.createFormData("requestFormDouble", 1.1.toString())
            val requestFormBoolean =
                MultipartBody.Part.createFormData("requestFormBoolean", true.toString())

            val requestFormStringList = ArrayList<MultipartBody.Part>()
            for (requestFormString1 in listOf("paramFromServer")) {
                requestFormStringList.add(
                    MultipartBody.Part.createFormData("requestFormStringList", requestFormString1)
                )
            }

            // 전송 하려는 File
            val serverFile1 =
                Paths.get("${File("").absolutePath}/module-api-sample/src/main/resources/static/for_c3_n9_post_request_test_with_multipart_form_type_request_body2/test1.txt")
                    .toFile()
            val serverFile2 =
                Paths.get("${File("").absolutePath}/module-api-sample/src/main/resources/static/for_c3_n9_post_request_test_with_multipart_form_type_request_body2/test2.txt")
                    .toFile()

            val multipartFileListFormData = listOf(
                MultipartBody.Part.createFormData(
                    "multipartFileList",
                    serverFile1.name,
                    serverFile1.asRequestBody(
                        serverFile1.toURI().toURL().openConnection().contentType.toMediaTypeOrNull()
                    )
                ),
                MultipartBody.Part.createFormData(
                    "multipartFileList",
                    serverFile2.name,
                    serverFile2.asRequestBody(
                        serverFile2.toURI().toURL().openConnection().contentType.toMediaTypeOrNull()
                    )
                )
            )

            val responseObj =
                networkRetrofit2.localHostRequestApi.postService1TkV1RequestTestPostRequestMultipartFormData2(
                    requestFormString,
                    null,
                    requestFormInt,
                    null,
                    requestFormDouble,
                    null,
                    requestFormBoolean,
                    null,
                    requestFormStringList,
                    null,
                    multipartFileListFormData,
                    null
                ).execute()

            return if (responseObj.code() == 200) {
                // 정상 동작
                httpServletResponse.status = HttpStatus.OK.value()
                val responseBody = responseObj.body()!!
                C3Service1TkV1RequestFromServerTestController.Api9PostRequestTestWithMultipartFormTypeRequestBody2OutputVo(
                    responseBody.requestFormString,
                    responseBody.requestFormStringNullable,
                    responseBody.requestFormInt,
                    responseBody.requestFormIntNullable,
                    responseBody.requestFormDouble,
                    responseBody.requestFormDoubleNullable,
                    responseBody.requestFormBoolean,
                    responseBody.requestFormBooleanNullable,
                    responseBody.requestFormStringList,
                    responseBody.requestFormStringListNullable
                )
            } else {
                // 반환될 일 없는 상태 = 서버측 에러
                httpServletResponse.status = HttpStatus.NO_CONTENT.value()
                httpServletResponse.setHeader("api-result-code", "2")
                return null
            }
        } catch (e: SocketTimeoutException) {
            // 타임아웃 에러 처리 = 런타임에서 처리해야하는 유일한 클라이언트 측 에러
            httpServletResponse.status = HttpStatus.NO_CONTENT.value()
            httpServletResponse.setHeader("api-result-code", "1")
            return null
        }
    }


    ////
    override fun api10PostRequestTestWithMultipartFormTypeRequestBody3(httpServletResponse: HttpServletResponse): C3Service1TkV1RequestFromServerTestController.Api10PostRequestTestWithMultipartFormTypeRequestBody3OutputVo? {
        try {
            // 네트워크 요청
            val jsonStringFormData = MultipartBody.Part.createFormData(
                "jsonString", Gson().toJson(
                    LocalHostRequestApi.PostService1TkV1RequestTestPostRequestMultipartFormDataJsonJsonStringVo(
                        "paramFromServer",
                        null,
                        1,
                        null,
                        1.1,
                        null,
                        true,
                        null,
                        listOf("paramFromServer"),
                        null
                    )
                )
            )

            // 전송 하려는 File
            val serverFile =
                Paths.get("${File("").absolutePath}/module-api-sample/src/main/resources/static/for_c3_n10_post_request_test_with_multipart_form_type_request_body3/test.txt")
                    .toFile()
            val multipartFileFormData = MultipartBody.Part.createFormData(
                "multipartFile",
                serverFile.name,
                serverFile.asRequestBody(serverFile.toURI().toURL().openConnection().contentType.toMediaTypeOrNull())
            )

            val responseObj =
                networkRetrofit2.localHostRequestApi.postService1TkV1RequestTestPostRequestMultipartFormDataJson(
                    jsonStringFormData,
                    multipartFileFormData,
                    null
                ).execute()

            return if (responseObj.code() == 200) {
                // 정상 동작
                httpServletResponse.status = HttpStatus.OK.value()
                val responseBody = responseObj.body()!!
                C3Service1TkV1RequestFromServerTestController.Api10PostRequestTestWithMultipartFormTypeRequestBody3OutputVo(
                    responseBody.requestFormString,
                    responseBody.requestFormStringNullable,
                    responseBody.requestFormInt,
                    responseBody.requestFormIntNullable,
                    responseBody.requestFormDouble,
                    responseBody.requestFormDoubleNullable,
                    responseBody.requestFormBoolean,
                    responseBody.requestFormBooleanNullable,
                    responseBody.requestFormStringList,
                    responseBody.requestFormStringListNullable
                )
            } else {
                // 반환될 일 없는 상태 = 서버측 에러
                httpServletResponse.status = HttpStatus.NO_CONTENT.value()
                httpServletResponse.setHeader("api-result-code", "2")
                return null
            }
        } catch (e: SocketTimeoutException) {
            // 타임아웃 에러 처리 = 런타임에서 처리해야하는 유일한 클라이언트 측 에러
            httpServletResponse.status = HttpStatus.NO_CONTENT.value()
            httpServletResponse.setHeader("api-result-code", "1")
            return null
        }
    }


    ////
    override fun api11GenerateErrorTest(httpServletResponse: HttpServletResponse) {
        try {
            // 네트워크 요청
            val responseObj = networkRetrofit2.localHostRequestApi.postService1TkV1RequestTestGenerateError().execute()

            return if (responseObj.code() == 200) {
                // 정상 동작
                httpServletResponse.status = HttpStatus.OK.value()
            } else {
                // 반환될 일 없는 상태 = 서버측 에러
                httpServletResponse.status = HttpStatus.NO_CONTENT.value()
                httpServletResponse.setHeader("api-result-code", "2")
            }
        } catch (e: SocketTimeoutException) {
            // 타임아웃 에러 처리 = 런타임에서 처리해야하는 유일한 클라이언트 측 에러
            httpServletResponse.status = HttpStatus.NO_CONTENT.value()
            httpServletResponse.setHeader("api-result-code", "1")
        }
    }


    ////
    override fun api12ReturnResultCodeThroughHeaders(httpServletResponse: HttpServletResponse) {
        try {
            // 네트워크 요청
            val responseObj = networkRetrofit2.localHostRequestApi.postService1TkV1RequestTestApiResultCodeTest(
                LocalHostRequestApi.PostService1TkV1RequestTestApiResultCodeTestErrorTypeEnum.A
            ).execute()

            if (responseObj.code() == 200) {
                // 정상 동작
                httpServletResponse.status = HttpStatus.OK.value()
            } else if (responseObj.code() == 204) {
                // api-result-code 확인 필요

                val responseHeaders = responseObj.headers()
                val apiResultCode = responseHeaders["api-result-code"]

                // api-result-code 분기
                when (apiResultCode) {
                    "1" -> {
                        httpServletResponse.status = HttpStatus.NO_CONTENT.value()
                        httpServletResponse.setHeader("api-result-code", "3")
                    }

                    "2" -> {
                        httpServletResponse.status = HttpStatus.NO_CONTENT.value()
                        httpServletResponse.setHeader("api-result-code", "4")
                    }

                    "3" -> {
                        httpServletResponse.status = HttpStatus.NO_CONTENT.value()
                        httpServletResponse.setHeader("api-result-code", "5")
                    }

                    else -> {
                        // 알수없는 api-result-code
                        httpServletResponse.status = HttpStatus.NO_CONTENT.value()
                        httpServletResponse.setHeader("api-result-code", "2")
                    }
                }
            } else {
                // 반환될 일 없는 상태 = 서버측 에러
                httpServletResponse.status = HttpStatus.NO_CONTENT.value()
                httpServletResponse.setHeader("api-result-code", "2")
            }
        } catch (e: SocketTimeoutException) {
            // 타임아웃 에러 처리 = 런타임에서 처리해야하는 유일한 클라이언트 측 에러
            httpServletResponse.status = HttpStatus.NO_CONTENT.value()
            httpServletResponse.setHeader("api-result-code", "1")
        }
    }


    ////
    override fun api13ResponseDelayTest(httpServletResponse: HttpServletResponse, delayTimeSec: Long) {
        try {
            // 네트워크 요청
            val responseObj =
                networkRetrofit2.localHostRequestApi.postService1TkV1RequestTestGenerateTimeOutError(delayTimeSec)
                    .execute()

            return if (responseObj.code() == 200) {
                // 정상 동작
                httpServletResponse.status = HttpStatus.OK.value()
            } else {
                // 반환될 일 없는 상태 = 서버측 에러
                httpServletResponse.status = HttpStatus.NO_CONTENT.value()
                httpServletResponse.setHeader("api-result-code", "2")
            }
        } catch (e: SocketTimeoutException) {
            // 타임아웃 에러 처리 = 런타임에서 처리해야하는 유일한 클라이언트 측 에러
            httpServletResponse.status = HttpStatus.NO_CONTENT.value()
            httpServletResponse.setHeader("api-result-code", "1")
        }
    }


    ////
    override fun api14ReturnTextStringTest(httpServletResponse: HttpServletResponse): String? {
        try {
            // 네트워크 요청
            val responseObj =
                networkRetrofit2.localHostRequestApi.getService1TkV1RequestTestReturnTextString().execute()

            return if (responseObj.code() == 200) {
                // 정상 동작
                httpServletResponse.status = HttpStatus.OK.value()
                responseObj.body()!!
            } else {
                // 반환될 일 없는 상태 = 서버측 에러
                httpServletResponse.status = HttpStatus.NO_CONTENT.value()
                httpServletResponse.setHeader("api-result-code", "2")
                return null
            }
        } catch (e: SocketTimeoutException) {
            // 타임아웃 에러 처리 = 런타임에서 처리해야하는 유일한 클라이언트 측 에러
            httpServletResponse.status = HttpStatus.NO_CONTENT.value()
            httpServletResponse.setHeader("api-result-code", "1")
            return null
        }
    }


    ////
    override fun api15ReturnTextHtmlTest(httpServletResponse: HttpServletResponse): String? {
        try {
            // 네트워크 요청
            val responseObj = networkRetrofit2.localHostRequestApi.getService1TkV1RequestTestReturnTextHtml().execute()

            return if (responseObj.code() == 200) {
                // 정상 동작
                httpServletResponse.status = HttpStatus.OK.value()
                responseObj.body()!!
            } else {
                // 반환될 일 없는 상태 = 서버측 에러
                httpServletResponse.status = HttpStatus.NO_CONTENT.value()
                httpServletResponse.setHeader("api-result-code", "2")
                return null
            }
        } catch (e: SocketTimeoutException) {
            // 타임아웃 에러 처리 = 런타임에서 처리해야하는 유일한 클라이언트 측 에러
            httpServletResponse.status = HttpStatus.NO_CONTENT.value()
            httpServletResponse.setHeader("api-result-code", "1")
            return null
        }
    }


    ////
    override fun api16AsynchronousResponseTest(httpServletResponse: HttpServletResponse): C3Service1TkV1RequestFromServerTestController.Api16AsynchronousResponseTestOutputVo? {
        try {
            // 네트워크 요청
            val responseObj = networkRetrofit2.localHostRequestApi.getService1TkV1RequestTestAsyncResult().execute()

            return if (responseObj.code() == 200) {
                // 정상 동작
                httpServletResponse.status = HttpStatus.OK.value()
                val responseBody = responseObj.body()!!
                C3Service1TkV1RequestFromServerTestController.Api16AsynchronousResponseTestOutputVo(
                    responseBody.resultMessage
                )
            } else {
                // 반환될 일 없는 상태 = 서버측 에러
                httpServletResponse.status = HttpStatus.NO_CONTENT.value()
                httpServletResponse.setHeader("api-result-code", "2")
                return null
            }
        } catch (e: SocketTimeoutException) {
            // 타임아웃 에러 처리 = 런타임에서 처리해야하는 유일한 클라이언트 측 에러
            httpServletResponse.status = HttpStatus.NO_CONTENT.value()
            httpServletResponse.setHeader("api-result-code", "1")
            return null
        }
    }


    ////
//    fun api17SseSubscribeTest(httpServletResponse: HttpServletResponse) {
//        // SSE Subscribe Url 연결 객체 생성
//        val sseClient =
//            SseClient("http://127.0.0.1:8080/service1/tk/v1/request-test/sse-test/subscribe")
//
//        val maxCount = 5
//        var count = 0
//
//        // SSE 구독 연결
//        sseClient.connect(
//            5000,
//            object : SseClient.ListenerCallback {
//                override fun onConnectRequestFirstTime(sse: SseClient, originalRequest: Request) {
//                    classLogger.info("++++ api17 : onConnectRequestFirstTime")
//                }
//
//                override fun onConnect(sse: SseClient, response: Response) {
//                    classLogger.info("++++ api17 : onOpen")
//                }
//
//                override fun onMessageReceive(
//                    sse: SseClient,
//                    eventId: String?,
//                    event: String,
//                    message: String
//                ) {
//                    classLogger.info("++++ api17 : onMessage : $event $message")
//
//                    // maxCount 만큼 반복했다면 연결 끊기
//                    if (maxCount == count++) {
//                        sseClient.disconnect()
//                    }
//                }
//
//                override fun onCommentReceive(sse: SseClient, comment: String) {
//                    classLogger.info("++++ api17 : onComment : $comment")
//                }
//
//                override fun onDisconnected(sse: SseClient) {
//                    classLogger.info("++++ api17 : onClosed")
//                }
//
//                override fun onPreRetry(
//                    sse: SseClient,
//                    originalRequest: Request,
//                    throwable: Throwable,
//                    response: Response?
//                ): Boolean {
//                    classLogger.info("++++ api17 : onPreRetry")
//                    return true
//                }
//            }
//        )
//
//        httpServletResponse.status = HttpStatus.OK.value()
//    }
//
//
//    ////
//    fun api18WebsocketConnectTest(httpServletResponse: HttpServletResponse) {
//        val client = OkHttpClient()
//
//        val maxCount = 5
//        var count = 0
//
//        val webSocket = client.newWebSocket(
//            Request.Builder()
//                .url("ws://localhost:8080/ws/test/websocket")
//                .build(),
//            object : WebSocketListener() {
//                override fun onOpen(webSocket: WebSocket, response: Response) {
//                    classLogger.info("++++ api18 : onOpen")
//                    webSocket.send(
//                        Gson().toJson(
//                            Api18MessagePayloadVo(
//                                "++++ api18 Client",
//                                "i am open!"
//                            )
//                        )
//                    )
//                }
//
//                override fun onMessage(webSocket: WebSocket, text: String) {
//                    // maxCount 만큼 반복했다면 연결 끊기
//                    if (maxCount == count++) {
//                        webSocket.close(1000, null)
//                    }
//
//                    classLogger.info("++++ api18 : onMessage : $text")
//
//                    // 메세지를 받으면 바로 메세지를 보내기
//                    webSocket.send(
//                        Gson().toJson(
//                            Api18MessagePayloadVo(
//                                "++++ api18 Client",
//                                "reply!"
//                            )
//                        )
//                    )
//
//                    // 웹소켓 종료시
////                    webSocket.close(1000, null)
//                }
//
//                override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
//                    classLogger.info("++++ api18 : onMessage : $bytes")
//                }
//
//                override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
//                    classLogger.info("++++ api18 : onClosing")
//                }
//
//                override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
//                    classLogger.info("vapi18 : onFailure")
//                    t.printStackTrace()
//                }
//            }
//        )
//        client.dispatcher.executorService.shutdown()
//
//        classLogger.info(webSocket.toString())
//
//        httpServletResponse.status = HttpStatus.OK.value()
//    }
}