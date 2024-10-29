package com.raillylinker.springboot_mvc_template_private.controllers

import com.fasterxml.jackson.annotation.JsonProperty
import com.raillylinker.springboot_mvc_template_private.services.C6Service1TkV1TestService
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
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@Tag(name = "/service1/tk/v1/test APIs", description = "C6 : 테스트 API 컨트롤러")
@Controller
@RequestMapping("/service1/tk/v1/test")
class C6Service1TkV1TestController(
    private val service: C6Service1TkV1TestService
) {
    // <멤버 변수 공간>


    // ---------------------------------------------------------------------------------------------
    // <매핑 함수 공간>
    @Operation(
        summary = "N1 : 이메일 발송 테스트",
        description = "이메일 발송 테스트\n\n"
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
        path = ["/send-email"],
        consumes = [MediaType.MULTIPART_FORM_DATA_VALUE],
        produces = [MediaType.ALL_VALUE]
    )
    @ResponseBody
    fun api1SendEmailTest(
        @Parameter(hidden = true)
        httpServletResponse: HttpServletResponse,
        @ModelAttribute
        @RequestBody
        inputVo: Api1SendEmailTestInputVo
    ) {
        service.api1SendEmailTest(httpServletResponse, inputVo)
    }

    data class Api1SendEmailTestInputVo(
        @Schema(description = "수신자 이메일 배열", required = true, example = "[\"test1@gmail.com\"]")
        @JsonProperty("receiverEmailAddressList")
        val receiverEmailAddressList: List<String>,
        @Schema(description = "참조자 이메일 배열", required = false, example = "[\"test2@gmail.com\"]")
        @JsonProperty("carbonCopyEmailAddressList")
        val carbonCopyEmailAddressList: List<String>?,
        @Schema(description = "발신자명", required = true, example = "Railly Linker")
        @JsonProperty("senderName")
        val senderName: String,
        @Schema(description = "제목", required = true, example = "테스트 이메일")
        @JsonProperty("subject")
        val subject: String,
        @Schema(description = "메세지", required = true, example = "테스트 이메일을 송신했습니다.")
        @JsonProperty("message")
        val message: String,
        @Schema(description = "첨부 파일 리스트", required = false)
        @JsonProperty("multipartFileList")
        val multipartFileList: List<MultipartFile>?
    )


    ////
    @Operation(
        summary = "N2 : HTML 이메일 발송 테스트",
        description = "HTML 로 이루어진 이메일 발송 테스트\n\n"
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
        path = ["/send-html-email"],
        consumes = [MediaType.MULTIPART_FORM_DATA_VALUE],
        produces = [MediaType.ALL_VALUE]
    )
    @ResponseBody
    fun api2SendHtmlEmailTest(
        @Parameter(hidden = true)
        httpServletResponse: HttpServletResponse,
        @ModelAttribute
        @RequestBody
        inputVo: Api2SendHtmlEmailTestInputVo
    ) {
        service.api2SendHtmlEmailTest(httpServletResponse, inputVo)
    }

    data class Api2SendHtmlEmailTestInputVo(
        @Schema(description = "수신자 이메일 배열", required = true, example = "[\"test1@gmail.com\"]")
        @JsonProperty("receiverEmailAddressList")
        val receiverEmailAddressList: List<String>,
        @Schema(description = "참조자 이메일 배열", required = false, example = "[\"test2@gmail.com\"]")
        @JsonProperty("carbonCopyEmailAddressList")
        val carbonCopyEmailAddressList: List<String>?,
        @Schema(description = "발신자명", required = true, example = "Railly Linker")
        @JsonProperty("senderName")
        val senderName: String,
        @Schema(description = "제목", required = true, example = "테스트 이메일")
        @JsonProperty("subject")
        val subject: String,
        @Schema(description = "메세지", required = true, example = "테스트 이메일을 송신했습니다.")
        @JsonProperty("message")
        val message: String,
        @Schema(description = "첨부 파일 리스트", required = false)
        @JsonProperty("multipartFileList")
        val multipartFileList: List<MultipartFile>?
    )


    ////
    @Operation(
        summary = "N3 : Naver API SMS 발송 샘플",
        description = "Naver API 를 사용한 SMS 발송 샘플\n\n" +
                "Service 에서 사용하는 Naver SMS 발송 유틸 내의 개인정보를 변경해야 사용 가능\n\n"
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
        path = ["/naver-sms-sample"],
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.ALL_VALUE]
    )
    @ResponseBody
    fun api3NaverSmsSample(
        @Parameter(hidden = true)
        httpServletResponse: HttpServletResponse,
        @RequestBody
        inputVo: Api3NaverSmsSampleInputVo
    ) {
        return service.api3NaverSmsSample(httpServletResponse, inputVo)
    }

    data class Api3NaverSmsSampleInputVo(
        @Schema(description = "SMS 수신측 휴대전화 번호", required = true, example = "82)010-1111-1111")
        @JsonProperty("phoneNumber")
        val phoneNumber: String,
        @Schema(description = "SMS 메세지", required = true, example = "테스트 메세지 발송입니다.")
        @JsonProperty("smsMessage")
        val smsMessage: String
    )


    ////
    @Operation(
        summary = "N3.1 : Naver API AlimTalk 발송 샘플",
        description = "Naver API 를 사용한 AlimTalk 발송 샘플\n\n" +
                "Service 에서 사용하는 Naver AlimTalk 발송 유틸 내의 개인정보를 변경해야 사용 가능\n\n"
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
        path = ["/naver-alim-talk-sample"],
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.ALL_VALUE]
    )
    @ResponseBody
    fun api3Dot1NaverAlimTalkSample(
        @Parameter(hidden = true)
        httpServletResponse: HttpServletResponse,
        @RequestBody
        inputVo: Api3Dot1NaverAlimTalkSampleInputVo
    ) {
        return service.api3Dot1NaverAlimTalkSample(httpServletResponse, inputVo)
    }

    data class Api3Dot1NaverAlimTalkSampleInputVo(
        @Schema(description = "카카오톡 채널명 ((구)플러스친구 아이디)", required = true, example = "@test")
        @JsonProperty("plusFriendId")
        val plusFriendId: String,
        @Schema(description = "템플릿 코드", required = true, example = "AAA1111")
        @JsonProperty("templateCode")
        val templateCode: String,
        @Schema(description = "SMS 수신측 휴대전화 번호", required = true, example = "82)010-1111-1111")
        @JsonProperty("phoneNumber")
        val phoneNumber: String,
        @Schema(description = "메세지(템플릿에 등록한 문장과 동일해야 됩니다.)", required = true, example = "테스트 메세지 발송입니다.")
        @JsonProperty("message")
        val message: String
    )


    ////
    @Operation(
        summary = "N4 : 액셀 파일을 받아서 해석 후 데이터 반환",
        description = "액셀 파일을 받아서 해석 후 데이터 반환\n\n"
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
        path = ["/read-excel"],
        consumes = [MediaType.MULTIPART_FORM_DATA_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    @ResponseBody
    fun api4ReadExcelFileSample(
        @Parameter(hidden = true)
        httpServletResponse: HttpServletResponse,
        @ModelAttribute
        @RequestBody
        inputVo: Api4ReadExcelFileSampleInputVo
    ): Api4ReadExcelFileSampleOutputVo? {
        return service.api4ReadExcelFileSample(httpServletResponse, inputVo)
    }

    data class Api4ReadExcelFileSampleInputVo(
        @Schema(description = "가져오려는 시트 인덱스 (0부터 시작)", required = true, example = "0")
        @JsonProperty("sheetIdx")
        val sheetIdx: Int,
        @Schema(description = "가져올 행 범위 시작 인덱스 (0부터 시작)", required = true, example = "0")
        @JsonProperty("rowRangeStartIdx")
        val rowRangeStartIdx: Int,
        @Schema(description = "가져올 행 범위 끝 인덱스 null 이라면 전부 (0부터 시작)", required = false, example = "10")
        @JsonProperty("rowRangeEndIdx")
        val rowRangeEndIdx: Int?,
        @Schema(description = "가져올 열 범위 인덱스 리스트 null 이라면 전부 (0부터 시작)", required = false, example = "[0, 1, 2]")
        @JsonProperty("columnRangeIdxList")
        val columnRangeIdxList: List<Int>?,
        @Schema(description = "결과 컬럼의 최소 길이 (길이를 넘으면 그대로, 미만이라면 \"\" 로 채움)", required = false, example = "5")
        @JsonProperty("minColumnLength")
        val minColumnLength: Int?,
        @Schema(description = "액셀 파일", required = true)
        @JsonProperty("excelFile")
        val excelFile: MultipartFile
    )

    data class Api4ReadExcelFileSampleOutputVo(
        @Schema(description = "행 카운트", required = true, example = "1")
        @JsonProperty("rowCount")
        val rowCount: Int,
        @Schema(description = "분석한 객체를 toString 으로 표현한 데이터 String", required = true, example = "[[\"데이터1\", \"데이터2\"]]")
        @JsonProperty("dataString")
        val dataString: String
    )


    ////
    @Operation(
        summary = "N5 : 액셀 파일 쓰기",
        description = "받은 데이터를 기반으로 액셀 파일을 만들어 by_product_files/test 폴더에 저장\n\n"
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
        path = ["/write-excel"],
        consumes = [MediaType.ALL_VALUE],
        produces = [MediaType.ALL_VALUE]
    )
    @ResponseBody
    fun api5WriteExcelFileSample(
        @Parameter(hidden = true)
        httpServletResponse: HttpServletResponse
    ) {
        service.api5WriteExcelFileSample(httpServletResponse)
    }


    ////
    @Operation(
        summary = "N6 : HTML 을 기반으로 PDF 를 생성",
        description = "준비된 HTML 1.0(strict), CSS 2.1 을 기반으로 PDF 를 생성\n\n"
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
        path = ["/html-to-pdf"],
        consumes = [MediaType.ALL_VALUE],
        produces = [MediaType.APPLICATION_OCTET_STREAM_VALUE]
    )
    @ResponseBody
    fun api6HtmlToPdfSample(
        @Parameter(hidden = true)
        httpServletResponse: HttpServletResponse
    ): ResponseEntity<Resource>? {
        return service.api6HtmlToPdfSample(httpServletResponse)
    }


    ////
    @Operation(
        summary = "N6.1 : 입력받은 HTML 을 기반으로 PDF 를 생성 후 반환",
        description = "입력받은 HTML 1.0(strict), CSS 2.1 을 기반으로 PDF 를 생성 후 반환\n\n" +
                "HTML 이 엄격한 규격을 요구받으므로 그것을 확인하며 변환하는 과정에 사용하라고 제공되는 api 입니다.\n\n"
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
                                "1 : fontFiles 에 ttf 가 아닌 폰트 파일이 존재합니다.\n\n",
                        schema = Schema(type = "string")
                    )
                ]
            )
        ]
    )
    @PostMapping(
        path = ["/multipart-html-to-pdf"],
        consumes = [MediaType.MULTIPART_FORM_DATA_VALUE],
        produces = [MediaType.APPLICATION_OCTET_STREAM_VALUE]
    )
    @ResponseBody
    fun api6Dot1MultipartHtmlToPdfSample(
        @Parameter(hidden = true)
        httpServletResponse: HttpServletResponse,
        @ModelAttribute
        @RequestBody
        inputVo: Api6Dot1MultipartHtmlToPdfSampleInputVo
    ): ResponseEntity<Resource>? {
        var controllerBasicMapping: String? = null
        for (requestMappingAnnotation in this.javaClass.getAnnotationsByType(org.springframework.web.bind.annotation.RequestMapping::class.java)) {
            val paths = requestMappingAnnotation.value
            if (paths.isNotEmpty()) {
                controllerBasicMapping = paths[0]
                break
            }
        }

        return service.api6Dot1MultipartHtmlToPdfSample(httpServletResponse, inputVo, controllerBasicMapping)
    }

    data class Api6Dot1MultipartHtmlToPdfSampleInputVo(
        @Schema(description = "업로드 HTML 파일", required = true)
        @JsonProperty("htmlFile")
        val htmlFile: MultipartFile,
        @Schema(
            description = "TTF 폰트 파일 리스트 (위 HTML 에서 사용할 TTF 폰트 파일을 넣어주세요. HTML 내에서는 해당 폰트의 파일명(ex : test.ttf)을 사용하세요.)\n\n" +
                    "ex : \n\n" +
                    "       @font-face {\n\n" +
                    "            font-family: NanumGothic;\n\n" +
                    "            src: \"NanumGothicFile.ttf\";\n\n" +
                    "            -fs-pdf-font-embed: embed;\n\n" +
                    "            -fs-pdf-font-encoding: Identity-H;\n\n" +
                    "        }",
            required = false
        )
        @JsonProperty("fontFiles")
        val fontFiles: List<MultipartFile>?,
        @Schema(
            description = "이미지 파일 리스트 (위 HTML 에서 사용할 이미지 파일을 넣어주세요. HTML 내에서는 해당 이미지의 파일명(ex : test.jpg)을 사용하세요.)\n\n" +
                    "ex : \n\n" +
                    "       img src=\"html_to_pdf_sample.jpg\"/",
            required = false
        )
        @JsonProperty("imgFiles")
        val imgFiles: List<MultipartFile>?
    )


    ////
    @Operation(
        summary = "N6.2 : by_product_files/uploads/fonts 폴더에서 파일 다운받기",
        description = "by_product_files/uploads/fonts 경로의 파일을 다운로드\n\n"
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
                                "1 : fileName 에 해당하는 파일이 존재하지 않습니다.\n\n",
                        schema = Schema(type = "string")
                    )
                ]
            )
        ]
    )
    @GetMapping(
        path = ["/by_product_files/uploads/fonts/{fileName}"],
        consumes = [MediaType.ALL_VALUE],
        produces = [MediaType.APPLICATION_OCTET_STREAM_VALUE]
    )
    @ResponseBody
    fun api6Dot2DownloadFontFile(
        @Parameter(hidden = true)
        httpServletResponse: HttpServletResponse,
        @Parameter(name = "fileName", description = "by_product_files/test 폴더 안의 파일명", example = "sample.txt")
        @PathVariable("fileName")
        fileName: String
    ): ResponseEntity<Resource>? {
        return service.api6Dot2DownloadFontFile(httpServletResponse, fileName)
    }


    ////
    @Operation(
        summary = "N7 : Kafka 토픽 메세지 발행 테스트",
        description = "Kafka 토픽 메세지를 발행합니다.\n\n"
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
        path = ["/kafka-produce-test"],
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.ALL_VALUE]
    )
    @ResponseBody
    fun api7SendKafkaTopicMessageTest(
        @Parameter(hidden = true)
        httpServletResponse: HttpServletResponse,
        @RequestBody
        inputVo: Api7SendKafkaTopicMessageTestInputVo
    ) {
        service.api7SendKafkaTopicMessageTest(
            httpServletResponse,
            inputVo
        )
    }

    data class Api7SendKafkaTopicMessageTestInputVo(
        @Schema(description = "메세지", required = true, example = "testMessage")
        @JsonProperty("message")
        val message: String
    )

    ////
    @Operation(
        summary = "N8 : ProcessBuilder 샘플",
        description = "ProcessBuilder 를 이용하여 준비된 jar 파일을 실행시킵니다.\n\n" +
                "jar 파일은 3초간 while 문으로 int 변수에 ++ 를 한 후 그 결과를 반환합니다.\n\n"
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
        path = ["/process-builder-test"],
        consumes = [MediaType.ALL_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    @ResponseBody
    fun api8ProcessBuilderTest(
        @Parameter(hidden = true)
        httpServletResponse: HttpServletResponse,
        @Parameter(
            name = "java 실행 파일 경로",
            description = "java 명령어 실행 파일의 경로를 넣어줍니다. 환경변수 등록시 null",
            example = "C:\\Users\\raill\\.jdks\\openjdk-21.0.2\\bin"
        )
        @RequestParam("javaEnvironmentPath")
        javaEnvironmentPath: String?
    ): Api8ProcessBuilderTestOutputVo? {
        return service.api8ProcessBuilderTest(
            httpServletResponse,
            javaEnvironmentPath
        )
    }

    data class Api8ProcessBuilderTestOutputVo(
        @Schema(description = "jar 실행 결과", required = true, example = "3333")
        @JsonProperty("jarResult")
        val jarResult: Long
    )


    ////
    @Operation(
        summary = "N9 : 입력받은 폰트 파일의 내부 이름을 반환",
        description = "입력받은 폰트 파일의 내부 이름을 반환\n\n"
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
        path = ["/font-file-inner-name"],
        consumes = [MediaType.MULTIPART_FORM_DATA_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    @ResponseBody
    fun api9CheckFontFileInnerName(
        @Parameter(hidden = true)
        httpServletResponse: HttpServletResponse,
        @ModelAttribute
        @RequestBody
        inputVo: Api9CheckFontFileInnerNameInputVo
    ): Api9CheckFontFileInnerNameOutputVo? {
        return service.api9CheckFontFileInnerName(httpServletResponse, inputVo)
    }

    data class Api9CheckFontFileInnerNameInputVo(
        @Schema(description = "업로드 폰트 파일", required = true)
        @JsonProperty("fontFile")
        val fontFile: MultipartFile
    )

    data class Api9CheckFontFileInnerNameOutputVo(
        @Schema(description = "폰트 파일의 내부 이름", required = true, example = "NanumGothic")
        @JsonProperty("innerName")
        val innerName: String
    )

    ////
    @Operation(
        summary = "N10 : AES256 암호화 테스트",
        description = "입력받은 텍스트를 암호화 하여 반환합니다.\n\n"
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
        path = ["/aes256-encrypt"],
        consumes = [MediaType.ALL_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    @ResponseBody
    fun api10Aes256EncryptTest(
        @Parameter(hidden = true)
        httpServletResponse: HttpServletResponse,
        @Parameter(name = "plainText", description = "암호화 하려는 평문", example = "testString")
        @RequestParam("plainText")
        plainText: String,
        @Parameter(name = "alg", description = "암호화 알고리즘", example = "AES_CBC_PKCS5")
        @RequestParam("alg")
        alg: Api10Aes256EncryptTestCryptoAlgEnum,
        @Parameter(name = "initializationVector", description = "초기화 벡터 16byte = 16char", example = "1q2w3e4r5t6y7u8i")
        @RequestParam("initializationVector")
        initializationVector: String,
        @Parameter(
            name = "encryptionKey",
            description = "암호화 키 32byte = 32char",
            example = "1q2w3e4r5t6y7u8i9o0p1q2w3e4r5t6y"
        )
        @RequestParam("encryptionKey")
        encryptionKey: String
    ): Api10Aes256EncryptTestOutputVo? {
        return service.api10Aes256EncryptTest(
            httpServletResponse,
            plainText,
            alg,
            initializationVector,
            encryptionKey
        )
    }

    enum class Api10Aes256EncryptTestCryptoAlgEnum(val alg: String) {
        AES_CBC_PKCS5("AES/CBC/PKCS5Padding")
    }

    data class Api10Aes256EncryptTestOutputVo(
        @Schema(description = "암호화된 결과물", required = true, example = "testString")
        @JsonProperty("cryptoResult")
        val cryptoResult: String
    )

    ////
    @Operation(
        summary = "N11 : AES256 복호화 테스트",
        description = "입력받은 텍스트를 복호화 하여 반환합니다.\n\n"
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
        path = ["/aes256-decrypt"],
        consumes = [MediaType.ALL_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    @ResponseBody
    fun api11Aes256DecryptTest(
        @Parameter(hidden = true)
        httpServletResponse: HttpServletResponse,
        @Parameter(name = "encryptedText", description = "복호화 하려는 암호문", example = "DwH1WCA3Bzqf6xq+udBI1Q==")
        @RequestParam("encryptedText")
        encryptedText: String,
        @Parameter(name = "alg", description = "암호화 알고리즘", example = "AES_CBC_PKCS5")
        @RequestParam("alg")
        alg: Api11Aes256DecryptTestCryptoAlgEnum,
        @Parameter(name = "initializationVector", description = "초기화 벡터 16byte = 16char", example = "1q2w3e4r5t6y7u8i")
        @RequestParam("initializationVector")
        initializationVector: String,
        @Parameter(
            name = "encryptionKey",
            description = "암호화 키 32byte = 32char",
            example = "1q2w3e4r5t6y7u8i9o0p1q2w3e4r5t6y"
        )
        @RequestParam("encryptionKey")
        encryptionKey: String
    ): Api11Aes256DecryptTestOutputVo? {
        return service.api11Aes256DecryptTest(
            httpServletResponse,
            encryptedText,
            alg,
            initializationVector,
            encryptionKey
        )
    }

    enum class Api11Aes256DecryptTestCryptoAlgEnum(val alg: String) {
        AES_CBC_PKCS5("AES/CBC/PKCS5Padding")
    }

    data class Api11Aes256DecryptTestOutputVo(
        @Schema(description = "암호화된 결과물", required = true, example = "testString")
        @JsonProperty("cryptoResult")
        val cryptoResult: String
    )

    ////
    @Operation(
        summary = "N12 : Jsoup 태그 조작 테스트",
        description = "Jsoup 을 이용하여, HTML 태그를 조작하여 반환합니다.\n\n"
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
        path = ["/jsoup-test"],
        consumes = [MediaType.ALL_VALUE],
        produces = ["text/html;charset=utf-8"]
    )
    @ResponseBody
    fun api12JsoupTest(
        @Parameter(hidden = true)
        httpServletResponse: HttpServletResponse,
        @Parameter(name = "fix", description = "변환 여부", example = "true")
        @RequestParam("fix")
        fix: Boolean
    ): String? {
        return service.api12JsoupTest(
            httpServletResponse,
            fix
        )
    }
}