package com.raillylinker.springboot_mvc_template_private.services.impls

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.raillylinker.springboot_mvc_template_private.classes.SseEmitterWrapper
import com.raillylinker.springboot_mvc_template_private.controllers.C2Service1TkV1RequestTestController
import com.raillylinker.springboot_mvc_template_private.services.C2Service1TkV1RequestTestService
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.ByteArrayResource
import org.springframework.core.io.Resource
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.util.FileCopyUtils
import org.springframework.util.StringUtils
import org.springframework.web.context.request.async.DeferredResult
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter
import java.io.File
import java.io.FileInputStream
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@Service
class C2Service1TkV1RequestTestServiceImpl(
    // (프로젝트 실행시 사용 설정한 프로필명 (ex : dev8080, prod80, local8080, 설정 안하면 default 반환))
    @Value("\${spring.profiles.active:default}") private var activeProfile: String
) : C2Service1TkV1RequestTestService {
    // <멤버 변수 공간>
    private val classLogger: Logger = LoggerFactory.getLogger(this::class.java)

    // (스레드 풀)
    private val executorService: ExecutorService = Executors.newCachedThreadPool()


    // ---------------------------------------------------------------------------------------------
    // <공개 메소드 공간>
    override fun api1BasicRequestTest(httpServletResponse: HttpServletResponse): String? {
        httpServletResponse.status = HttpStatus.OK.value()
        return activeProfile
    }


    ////
    override fun api2RedirectTest(httpServletResponse: HttpServletResponse): ModelAndView? {
        val mv = ModelAndView()
        mv.viewName = "redirect:/service1/tk/v1/request-test"

        return mv
    }


    ////
    override fun api3ForwardTest(httpServletResponse: HttpServletResponse): ModelAndView? {
        val mv = ModelAndView()
        mv.viewName = "forward:/service1/tk/v1/request-test"

        return mv
    }


    ////
    override fun api4GetRequestTest(
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
    ): C2Service1TkV1RequestTestController.Api4GetRequestTestOutputVo? {
        httpServletResponse.status = HttpStatus.OK.value()
        return C2Service1TkV1RequestTestController.Api4GetRequestTestOutputVo(
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


    ////
    override fun api5GetRequestTestWithPathParam(
        httpServletResponse: HttpServletResponse,
        pathParamInt: Int
    ): C2Service1TkV1RequestTestController.Api5GetRequestTestWithPathParamOutputVo? {
        httpServletResponse.status = HttpStatus.OK.value()
        return C2Service1TkV1RequestTestController.Api5GetRequestTestWithPathParamOutputVo(pathParamInt)
    }


    ////
    override fun api6PostRequestTestWithApplicationJsonTypeRequestBody(
        httpServletResponse: HttpServletResponse,
        inputVo: C2Service1TkV1RequestTestController.Api6PostRequestTestWithApplicationJsonTypeRequestBodyInputVo
    ): C2Service1TkV1RequestTestController.Api6PostRequestTestWithApplicationJsonTypeRequestBodyOutputVo? {
        httpServletResponse.status = HttpStatus.OK.value()
        return C2Service1TkV1RequestTestController.Api6PostRequestTestWithApplicationJsonTypeRequestBodyOutputVo(
            inputVo.requestBodyString,
            inputVo.requestBodyStringNullable,
            inputVo.requestBodyInt,
            inputVo.requestBodyIntNullable,
            inputVo.requestBodyDouble,
            inputVo.requestBodyDoubleNullable,
            inputVo.requestBodyBoolean,
            inputVo.requestBodyBooleanNullable,
            inputVo.requestBodyStringList,
            inputVo.requestBodyStringListNullable
        )
    }


    ////
    override fun api6Dot1PostRequestTestWithApplicationJsonTypeRequestBody2(
        httpServletResponse: HttpServletResponse,
        inputVo: C2Service1TkV1RequestTestController.Api6Dot1PostRequestTestWithApplicationJsonTypeRequestBody2InputVo
    ): C2Service1TkV1RequestTestController.Api6Dot1PostRequestTestWithApplicationJsonTypeRequestBody2OutputVo? {
        val objectList: MutableList<C2Service1TkV1RequestTestController.Api6Dot1PostRequestTestWithApplicationJsonTypeRequestBody2OutputVo.ObjectVo> =
            mutableListOf()

        for (objectVo in inputVo.objectVoList) {
            val subObjectVoList: MutableList<C2Service1TkV1RequestTestController.Api6Dot1PostRequestTestWithApplicationJsonTypeRequestBody2OutputVo.ObjectVo.SubObjectVo> =
                mutableListOf()
            for (subObject in objectVo.subObjectVoList) {
                subObjectVoList.add(
                    C2Service1TkV1RequestTestController.Api6Dot1PostRequestTestWithApplicationJsonTypeRequestBody2OutputVo.ObjectVo.SubObjectVo(
                        subObject.requestBodyString,
                        subObject.requestBodyStringList
                    )
                )
            }

            objectList.add(
                C2Service1TkV1RequestTestController.Api6Dot1PostRequestTestWithApplicationJsonTypeRequestBody2OutputVo.ObjectVo(
                    objectVo.requestBodyString,
                    objectVo.requestBodyStringList,
                    C2Service1TkV1RequestTestController.Api6Dot1PostRequestTestWithApplicationJsonTypeRequestBody2OutputVo.ObjectVo.SubObjectVo(
                        objectVo.subObjectVo.requestBodyString,
                        objectVo.subObjectVo.requestBodyStringList
                    ),
                    subObjectVoList
                )
            )
        }

        val subObjectVoList: MutableList<C2Service1TkV1RequestTestController.Api6Dot1PostRequestTestWithApplicationJsonTypeRequestBody2OutputVo.ObjectVo.SubObjectVo> =
            mutableListOf()
        for (subObject in inputVo.objectVo.subObjectVoList) {
            subObjectVoList.add(
                C2Service1TkV1RequestTestController.Api6Dot1PostRequestTestWithApplicationJsonTypeRequestBody2OutputVo.ObjectVo.SubObjectVo(
                    subObject.requestBodyString,
                    subObject.requestBodyStringList
                )
            )
        }

        val outputVo =
            C2Service1TkV1RequestTestController.Api6Dot1PostRequestTestWithApplicationJsonTypeRequestBody2OutputVo(
                C2Service1TkV1RequestTestController.Api6Dot1PostRequestTestWithApplicationJsonTypeRequestBody2OutputVo.ObjectVo(
                    inputVo.objectVo.requestBodyString,
                    inputVo.objectVo.requestBodyStringList,
                    C2Service1TkV1RequestTestController.Api6Dot1PostRequestTestWithApplicationJsonTypeRequestBody2OutputVo.ObjectVo.SubObjectVo(
                        inputVo.objectVo.subObjectVo.requestBodyString,
                        inputVo.objectVo.subObjectVo.requestBodyStringList
                    ),
                    subObjectVoList
                ),
                objectList
            )

        httpServletResponse.status = HttpStatus.OK.value()
        return outputVo
    }


    ////
    override fun api6Dot2PostRequestTestWithNoInputAndOutput(
        httpServletResponse: HttpServletResponse
    ) {
        httpServletResponse.status = HttpStatus.OK.value()
    }


    ////
    override fun api7PostRequestTestWithFormTypeRequestBody(
        httpServletResponse: HttpServletResponse,
        inputVo: C2Service1TkV1RequestTestController.Api7PostRequestTestWithFormTypeRequestBodyInputVo
    ): C2Service1TkV1RequestTestController.Api7PostRequestTestWithFormTypeRequestBodyOutputVo? {
        httpServletResponse.status = HttpStatus.OK.value()
        return C2Service1TkV1RequestTestController.Api7PostRequestTestWithFormTypeRequestBodyOutputVo(
            inputVo.requestFormString,
            inputVo.requestFormStringNullable,
            inputVo.requestFormInt,
            inputVo.requestFormIntNullable,
            inputVo.requestFormDouble,
            inputVo.requestFormDoubleNullable,
            inputVo.requestFormBoolean,
            inputVo.requestFormBooleanNullable,
            inputVo.requestFormStringList,
            inputVo.requestFormStringListNullable
        )
    }


    ////
    override fun api8PostRequestTestWithMultipartFormTypeRequestBody(
        httpServletResponse: HttpServletResponse,
        inputVo: C2Service1TkV1RequestTestController.Api8PostRequestTestWithMultipartFormTypeRequestBodyInputVo
    ): C2Service1TkV1RequestTestController.Api8PostRequestTestWithMultipartFormTypeRequestBodyOutputVo? {
        // 파일 저장 기본 디렉토리 경로
        val saveDirectoryPath: Path = Paths.get("./by_product_files/test").toAbsolutePath().normalize()

        // 파일 저장 기본 디렉토리 생성
        Files.createDirectories(saveDirectoryPath)

        // 원본 파일명(with suffix)
        val multiPartFileNameString = StringUtils.cleanPath(inputVo.multipartFile.originalFilename!!)

        // 파일 확장자 구분 위치
        val fileExtensionSplitIdx = multiPartFileNameString.lastIndexOf('.')

        // 확장자가 없는 파일명
        val fileNameWithOutExtension: String
        // 확장자
        val fileExtension: String

        if (fileExtensionSplitIdx == -1) {
            fileNameWithOutExtension = multiPartFileNameString
            fileExtension = ""
        } else {
            fileNameWithOutExtension = multiPartFileNameString.substring(0, fileExtensionSplitIdx)
            fileExtension =
                multiPartFileNameString.substring(fileExtensionSplitIdx + 1, multiPartFileNameString.length)
        }

        // multipartFile 을 targetPath 에 저장
        inputVo.multipartFile.transferTo(
            // 파일 저장 경로와 파일명(with index) 을 합친 path 객체
            saveDirectoryPath.resolve(
                "${fileNameWithOutExtension}(${
                    LocalDateTime.now().atZone(ZoneId.systemDefault())
                        .format(DateTimeFormatter.ofPattern("yyyy_MM_dd_'T'_HH_mm_ss_SSS_z"))
                }).$fileExtension"
            ).normalize()
        )

        if (inputVo.multipartFileNullable != null) {
            // 원본 파일명(with suffix)
            val multiPartFileNullableNameString =
                StringUtils.cleanPath(inputVo.multipartFileNullable.originalFilename!!)

            // 파일 확장자 구분 위치
            val nullableFileExtensionSplitIdx = multiPartFileNullableNameString.lastIndexOf('.')

            // 확장자가 없는 파일명
            val nullableFileNameWithOutExtension: String
            // 확장자
            val nullableFileExtension: String

            if (nullableFileExtensionSplitIdx == -1) {
                nullableFileNameWithOutExtension = multiPartFileNullableNameString
                nullableFileExtension = ""
            } else {
                nullableFileNameWithOutExtension =
                    multiPartFileNullableNameString.substring(0, nullableFileExtensionSplitIdx)
                nullableFileExtension =
                    multiPartFileNullableNameString.substring(
                        nullableFileExtensionSplitIdx + 1,
                        multiPartFileNullableNameString.length
                    )
            }

            // multipartFile 을 targetPath 에 저장
            inputVo.multipartFileNullable.transferTo(
                // 파일 저장 경로와 파일명(with index) 을 합친 path 객체
                saveDirectoryPath.resolve(
                    "${nullableFileNameWithOutExtension}(${
                        LocalDateTime.now().atZone(ZoneId.systemDefault())
                            .format(DateTimeFormatter.ofPattern("yyyy_MM_dd_'T'_HH_mm_ss_SSS_z"))
                    }).$nullableFileExtension"
                ).normalize()
            )
        }

        httpServletResponse.status = HttpStatus.OK.value()
        return C2Service1TkV1RequestTestController.Api8PostRequestTestWithMultipartFormTypeRequestBodyOutputVo(
            inputVo.requestFormString,
            inputVo.requestFormStringNullable,
            inputVo.requestFormInt,
            inputVo.requestFormIntNullable,
            inputVo.requestFormDouble,
            inputVo.requestFormDoubleNullable,
            inputVo.requestFormBoolean,
            inputVo.requestFormBooleanNullable,
            inputVo.requestFormStringList,
            inputVo.requestFormStringListNullable
        )
    }


    ////
    override fun api9PostRequestTestWithMultipartFormTypeRequestBody2(
        httpServletResponse: HttpServletResponse,
        inputVo: C2Service1TkV1RequestTestController.Api9PostRequestTestWithMultipartFormTypeRequestBody2InputVo
    ): C2Service1TkV1RequestTestController.Api9PostRequestTestWithMultipartFormTypeRequestBody2OutputVo? {
        // 파일 저장 기본 디렉토리 경로
        val saveDirectoryPath: Path = Paths.get("./by_product_files/test").toAbsolutePath().normalize()

        // 파일 저장 기본 디렉토리 생성
        Files.createDirectories(saveDirectoryPath)

        for (multipartFile in inputVo.multipartFileList) {
            // 원본 파일명(with suffix)
            val multiPartFileNameString = StringUtils.cleanPath(multipartFile.originalFilename!!)

            // 파일 확장자 구분 위치
            val fileExtensionSplitIdx = multiPartFileNameString.lastIndexOf('.')

            // 확장자가 없는 파일명
            val fileNameWithOutExtension: String
            // 확장자
            val fileExtension: String

            if (fileExtensionSplitIdx == -1) {
                fileNameWithOutExtension = multiPartFileNameString
                fileExtension = ""
            } else {
                fileNameWithOutExtension = multiPartFileNameString.substring(0, fileExtensionSplitIdx)
                fileExtension =
                    multiPartFileNameString.substring(fileExtensionSplitIdx + 1, multiPartFileNameString.length)
            }

            // multipartFile 을 targetPath 에 저장
            multipartFile.transferTo(
                // 파일 저장 경로와 파일명(with index) 을 합친 path 객체
                saveDirectoryPath.resolve(
                    "${fileNameWithOutExtension}(${
                        LocalDateTime.now().atZone(ZoneId.systemDefault())
                            .format(DateTimeFormatter.ofPattern("yyyy_MM_dd_'T'_HH_mm_ss_SSS_z"))
                    }).$fileExtension"
                ).normalize()
            )
        }

        if (inputVo.multipartFileNullableList != null) {
            for (multipartFileNullable in inputVo.multipartFileNullableList) {
                // 원본 파일명(with suffix)
                val multiPartFileNullableNameString =
                    StringUtils.cleanPath(multipartFileNullable.originalFilename!!)

                // 파일 확장자 구분 위치
                val nullableFileExtensionSplitIdx = multiPartFileNullableNameString.lastIndexOf('.')

                // 확장자가 없는 파일명
                val nullableFileNameWithOutExtension: String
                // 확장자
                val nullableFileExtension: String

                if (nullableFileExtensionSplitIdx == -1) {
                    nullableFileNameWithOutExtension = multiPartFileNullableNameString
                    nullableFileExtension = ""
                } else {
                    nullableFileNameWithOutExtension =
                        multiPartFileNullableNameString.substring(0, nullableFileExtensionSplitIdx)
                    nullableFileExtension =
                        multiPartFileNullableNameString.substring(
                            nullableFileExtensionSplitIdx + 1,
                            multiPartFileNullableNameString.length
                        )
                }

                // multipartFile 을 targetPath 에 저장
                multipartFileNullable.transferTo(
                    // 파일 저장 경로와 파일명(with index) 을 합친 path 객체
                    saveDirectoryPath.resolve(
                        "${nullableFileNameWithOutExtension}(${
                            LocalDateTime.now().atZone(ZoneId.systemDefault())
                                .format(DateTimeFormatter.ofPattern("yyyy_MM_dd_'T'_HH_mm_ss_SSS_z"))
                        }).$nullableFileExtension"
                    ).normalize()
                )
            }
        }

        httpServletResponse.status = HttpStatus.OK.value()
        return C2Service1TkV1RequestTestController.Api9PostRequestTestWithMultipartFormTypeRequestBody2OutputVo(
            inputVo.requestFormString,
            inputVo.requestFormStringNullable,
            inputVo.requestFormInt,
            inputVo.requestFormIntNullable,
            inputVo.requestFormDouble,
            inputVo.requestFormDoubleNullable,
            inputVo.requestFormBoolean,
            inputVo.requestFormBooleanNullable,
            inputVo.requestFormStringList,
            inputVo.requestFormStringListNullable
        )
    }


    ////
    override fun api10PostRequestTestWithMultipartFormTypeRequestBody3(
        httpServletResponse: HttpServletResponse,
        inputVo: C2Service1TkV1RequestTestController.Api10PostRequestTestWithMultipartFormTypeRequestBody3InputVo
    ): C2Service1TkV1RequestTestController.Api10PostRequestTestWithMultipartFormTypeRequestBody3OutputVo? {
        // input Json String to Object
        val inputJsonObject =
            Gson().fromJson<C2Service1TkV1RequestTestController.Api10PostRequestTestWithMultipartFormTypeRequestBody3InputVo.InputJsonObject>(
                inputVo.jsonString, // 해석하려는 json 형식의 String
                object :
                    TypeToken<C2Service1TkV1RequestTestController.Api10PostRequestTestWithMultipartFormTypeRequestBody3InputVo.InputJsonObject>() {}.type // 파싱할 데이터 객체 타입
            )

        // 파일 저장 기본 디렉토리 경로
        val saveDirectoryPath: Path = Paths.get("./by_product_files/test").toAbsolutePath().normalize()

        // 파일 저장 기본 디렉토리 생성
        Files.createDirectories(saveDirectoryPath)

        // 원본 파일명(with suffix)
        val multiPartFileNameString = StringUtils.cleanPath(inputVo.multipartFile.originalFilename!!)

        // 파일 확장자 구분 위치
        val fileExtensionSplitIdx = multiPartFileNameString.lastIndexOf('.')

        // 확장자가 없는 파일명
        val fileNameWithOutExtension: String
        // 확장자
        val fileExtension: String

        if (fileExtensionSplitIdx == -1) {
            fileNameWithOutExtension = multiPartFileNameString
            fileExtension = ""
        } else {
            fileNameWithOutExtension = multiPartFileNameString.substring(0, fileExtensionSplitIdx)
            fileExtension =
                multiPartFileNameString.substring(fileExtensionSplitIdx + 1, multiPartFileNameString.length)
        }

        // multipartFile 을 targetPath 에 저장
        inputVo.multipartFile.transferTo(
            // 파일 저장 경로와 파일명(with index) 을 합친 path 객체
            saveDirectoryPath.resolve(
                "${fileNameWithOutExtension}(${
                    LocalDateTime.now().atZone(ZoneId.systemDefault())
                        .format(DateTimeFormatter.ofPattern("yyyy_MM_dd_'T'_HH_mm_ss_SSS_z"))
                }).$fileExtension"
            ).normalize()
        )

        if (inputVo.multipartFileNullable != null) {
            // 원본 파일명(with suffix)
            val multiPartFileNullableNameString =
                StringUtils.cleanPath(inputVo.multipartFileNullable.originalFilename!!)

            // 파일 확장자 구분 위치
            val nullableFileExtensionSplitIdx = multiPartFileNullableNameString.lastIndexOf('.')

            // 확장자가 없는 파일명
            val nullableFileNameWithOutExtension: String
            // 확장자
            val nullableFileExtension: String

            if (nullableFileExtensionSplitIdx == -1) {
                nullableFileNameWithOutExtension = multiPartFileNullableNameString
                nullableFileExtension = ""
            } else {
                nullableFileNameWithOutExtension =
                    multiPartFileNullableNameString.substring(0, nullableFileExtensionSplitIdx)
                nullableFileExtension =
                    multiPartFileNullableNameString.substring(
                        nullableFileExtensionSplitIdx + 1,
                        multiPartFileNullableNameString.length
                    )
            }

            // multipartFile 을 targetPath 에 저장
            inputVo.multipartFileNullable.transferTo(
                // 파일 저장 경로와 파일명(with index) 을 합친 path 객체
                saveDirectoryPath.resolve(
                    "${nullableFileNameWithOutExtension}(${
                        LocalDateTime.now().format(
                            DateTimeFormatter.ofPattern("yyyy-MM-dd-HH_mm-ss-SSS")
                        )
                    }).$nullableFileExtension"
                ).normalize()
            )
        }

        httpServletResponse.status = HttpStatus.OK.value()
        return C2Service1TkV1RequestTestController.Api10PostRequestTestWithMultipartFormTypeRequestBody3OutputVo(
            inputJsonObject.requestFormString,
            inputJsonObject.requestFormStringNullable,
            inputJsonObject.requestFormInt,
            inputJsonObject.requestFormIntNullable,
            inputJsonObject.requestFormDouble,
            inputJsonObject.requestFormDoubleNullable,
            inputJsonObject.requestFormBoolean,
            inputJsonObject.requestFormBooleanNullable,
            inputJsonObject.requestFormStringList,
            inputJsonObject.requestFormStringListNullable
        )
    }


    ////
    override fun api11GenerateErrorTest(httpServletResponse: HttpServletResponse) {
        throw RuntimeException("Test Error")
    }

    ////
    override fun api12ReturnResultCodeThroughHeaders(
        httpServletResponse: HttpServletResponse,
        errorType: C2Service1TkV1RequestTestController.Api12ReturnResultCodeThroughHeadersErrorTypeEnum?
    ) {
        if (errorType == null) {
            httpServletResponse.status = HttpStatus.OK.value()
        } else {
            when (errorType) {
                C2Service1TkV1RequestTestController.Api12ReturnResultCodeThroughHeadersErrorTypeEnum.A -> {
                    httpServletResponse.status = HttpStatus.NO_CONTENT.value()
                    httpServletResponse.setHeader("api-result-code", "1")
                }

                C2Service1TkV1RequestTestController.Api12ReturnResultCodeThroughHeadersErrorTypeEnum.B -> {
                    httpServletResponse.status = HttpStatus.NO_CONTENT.value()
                    httpServletResponse.setHeader("api-result-code", "2")
                }

                C2Service1TkV1RequestTestController.Api12ReturnResultCodeThroughHeadersErrorTypeEnum.C -> {
                    httpServletResponse.status = HttpStatus.NO_CONTENT.value()
                    httpServletResponse.setHeader("api-result-code", "3")
                }
            }
        }
    }


    ////
    override fun api13ResponseDelayTest(httpServletResponse: HttpServletResponse, delayTimeSec: Long) {
        val endTime = System.currentTimeMillis() + (delayTimeSec * 1000)

        while (System.currentTimeMillis() < endTime) {
            // 아무 것도 하지 않고 대기
            Thread.sleep(100)  // 100ms마다 스레드를 잠들게 하여 CPU 사용률을 줄임
        }

        httpServletResponse.status = HttpStatus.OK.value()
    }


    ////
    override fun api14ReturnTextStringTest(httpServletResponse: HttpServletResponse): String? {
        httpServletResponse.status = HttpStatus.OK.value()
        return "test Complete!"
    }


    ////
    override fun api15ReturnTextHtmlTest(httpServletResponse: HttpServletResponse): ModelAndView? {
        val modelAndView = ModelAndView()
        modelAndView.viewName = "for_c2_n15_return_text_html_test/html_response_example"

        httpServletResponse.status = HttpStatus.OK.value()
        return modelAndView
    }


    ////
    override fun api16ReturnByteDataTest(httpServletResponse: HttpServletResponse): Resource? {
        httpServletResponse.status = HttpStatus.OK.value()
        return ByteArrayResource(
            byteArrayOf(
                'a'.code.toByte(),
                'b'.code.toByte(),
                'c'.code.toByte(),
                'd'.code.toByte(),
                'e'.code.toByte(),
                'f'.code.toByte()
            )
        )
    }


    ////
    override fun api17VideoStreamingTest(
        videoHeight: C2Service1TkV1RequestTestController.Api17VideoStreamingTestVideoHeight,
        httpServletResponse: HttpServletResponse
    ): Resource? {
        // 프로젝트 루트 경로 (프로젝트 settings.gradle 이 있는 경로)
        val projectRootAbsolutePathString: String = File("").absolutePath

        // 파일 절대 경로 및 파일명
        val serverFileAbsolutePathString =
            "$projectRootAbsolutePathString/module-api-sample/src/main/resources/static/for_c2_n17_video_streaming_test"

        // 멤버십 등의 정보로 해상도 제한을 걸 수도 있음
        val serverFileNameString =
            when (videoHeight) {
                C2Service1TkV1RequestTestController.Api17VideoStreamingTestVideoHeight.H240 -> {
                    "test_240p.mp4"
                }

                C2Service1TkV1RequestTestController.Api17VideoStreamingTestVideoHeight.H360 -> {
                    "test_360p.mp4"
                }

                C2Service1TkV1RequestTestController.Api17VideoStreamingTestVideoHeight.H480 -> {
                    "test_480p.mp4"
                }

                C2Service1TkV1RequestTestController.Api17VideoStreamingTestVideoHeight.H720 -> {
                    "test_720p.mp4"
                }
            }

        // 반환값에 전해줄 FIS
        val fileByteArray: ByteArray
        FileInputStream("$serverFileAbsolutePathString/$serverFileNameString").use { fileInputStream ->
            fileByteArray = FileCopyUtils.copyToByteArray(fileInputStream)
        }

        httpServletResponse.status = HttpStatus.OK.value()
        return ByteArrayResource(fileByteArray)
    }


    ////
    override fun api18AudioStreamingTest(httpServletResponse: HttpServletResponse): Resource? {
        // 프로젝트 루트 경로 (프로젝트 settings.gradle 이 있는 경로)
        val projectRootAbsolutePathString: String = File("").absolutePath

        // 파일 절대 경로 및 파일명
        val serverFileAbsolutePathString =
            "$projectRootAbsolutePathString/module-api-sample/src/main/resources/static/for_c2_n18_audio_streaming_test"
        val serverFileNameString = "test.mp3"

        // 반환값에 전해줄 FIS
        val fileByteArray: ByteArray
        FileInputStream("$serverFileAbsolutePathString/$serverFileNameString").use { fileInputStream ->
            fileByteArray = FileCopyUtils.copyToByteArray(fileInputStream)
        }

        httpServletResponse.status = HttpStatus.OK.value()
        return ByteArrayResource(fileByteArray)
    }


    ////
    override fun api19AsynchronousResponseTest(httpServletResponse: HttpServletResponse): DeferredResult<C2Service1TkV1RequestTestController.Api19AsynchronousResponseTestOutputVo>? {
        // 연결 타임아웃 밀리초
        val deferredResultTimeoutMs = 1000L * 60
        val deferredResult = DeferredResult<C2Service1TkV1RequestTestController.Api19AsynchronousResponseTestOutputVo>(
            deferredResultTimeoutMs
        )

        // 비동기 처리
        executorService.execute {
            // 지연시간 대기
            val delayMs = 5000L
            Thread.sleep(delayMs)

            // 결과 반환
            deferredResult.setResult(C2Service1TkV1RequestTestController.Api19AsynchronousResponseTestOutputVo("${delayMs / 1000} 초 경과 후 반환했습니다."))
        }

        // 결과 대기 객체를 먼저 반환
        httpServletResponse.status = HttpStatus.OK.value()
        return deferredResult
    }


    ////
    // api20 에서 발급한 Emitter 객체
    private val api20SseEmitterWrapperMbr = SseEmitterWrapper()
    override fun api20SseTestSubscribe(httpServletResponse: HttpServletResponse, lastSseEventId: String?): SseEmitter? {
        // 수신 객체
        val sseEmitter = api20SseEmitterWrapperMbr.getSseEmitter(null, lastSseEventId)

        httpServletResponse.status = HttpStatus.OK.value()
        return sseEmitter
    }


    ////
    private var api21TriggerTestCountMbr = 0
    override fun api21SseTestEventTrigger(httpServletResponse: HttpServletResponse) {
        // emitter 이벤트 전송
        val nowTriggerTestCount = ++api21TriggerTestCountMbr

        api20SseEmitterWrapperMbr.broadcastEvent(
            "triggerTest",
            "trigger $nowTriggerTestCount"
        )

        httpServletResponse.status = HttpStatus.OK.value()
    }


    ////
    override fun api22EmptyListRequestTest(
        httpServletResponse: HttpServletResponse,
        stringList: List<String>,
        inputVo: C2Service1TkV1RequestTestController.Api22EmptyListRequestTestInputVo
    ): C2Service1TkV1RequestTestController.Api22EmptyListRequestTestOutputVo? {
        httpServletResponse.status = HttpStatus.OK.value()
        return C2Service1TkV1RequestTestController.Api22EmptyListRequestTestOutputVo(
            stringList,
            inputVo.requestBodyStringList
        )
    }
}