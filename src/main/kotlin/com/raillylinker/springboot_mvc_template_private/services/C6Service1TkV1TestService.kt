package com.raillylinker.springboot_mvc_template_private.services

import com.raillylinker.springboot_mvc_template_private.controllers.C6Service1TkV1TestController
import jakarta.servlet.http.HttpServletResponse
import org.springframework.core.io.Resource
import org.springframework.http.ResponseEntity

interface C6Service1TkV1TestService {
    // (이메일 발송 테스트)
    fun api1SendEmailTest(
        httpServletResponse: HttpServletResponse,
        inputVo: C6Service1TkV1TestController.Api1SendEmailTestInputVo
    )


    ////
    // (HTML 이메일 발송 테스트)
    fun api2SendHtmlEmailTest(
        httpServletResponse: HttpServletResponse,
        inputVo: C6Service1TkV1TestController.Api2SendHtmlEmailTestInputVo
    )


    ////
    // (Naver API SMS 발송 샘플)
    fun api3NaverSmsSample(
        httpServletResponse: HttpServletResponse,
        inputVo: C6Service1TkV1TestController.Api3NaverSmsSampleInputVo
    )


    ////
    // (Naver API AlimTalk 발송 샘플)
    fun api3Dot1NaverAlimTalkSample(
        httpServletResponse: HttpServletResponse,
        inputVo: C6Service1TkV1TestController.Api3Dot1NaverAlimTalkSampleInputVo
    )


    ////
    // (액셀 파일을 받아서 해석 후 데이터 반환)
    fun api4ReadExcelFileSample(
        httpServletResponse: HttpServletResponse,
        inputVo: C6Service1TkV1TestController.Api4ReadExcelFileSampleInputVo
    ): C6Service1TkV1TestController.Api4ReadExcelFileSampleOutputVo?


    ////
    // (액셀 파일 쓰기)
    fun api5WriteExcelFileSample(httpServletResponse: HttpServletResponse)


    ////
    // (HTML 을 기반으로 PDF 를 생성)
    fun api6HtmlToPdfSample(
        httpServletResponse: HttpServletResponse
    ): ResponseEntity<Resource>?


    ////
    // (입력받은 HTML 을 기반으로 PDF 를 생성 후 반환)
    fun api6Dot1MultipartHtmlToPdfSample(
        httpServletResponse: HttpServletResponse,
        inputVo: C6Service1TkV1TestController.Api6Dot1MultipartHtmlToPdfSampleInputVo,
        controllerBasicMapping: String?
    ): ResponseEntity<Resource>?


    ////
    // (by_product_files/uploads/fonts 폴더에서 파일 다운받기)
    fun api6Dot2DownloadFontFile(
        httpServletResponse: HttpServletResponse,
        fileName: String
    ): ResponseEntity<Resource>?


    ////
    // (Kafka 토픽 메세지 발행 테스트)
    fun api7SendKafkaTopicMessageTest(
        httpServletResponse: HttpServletResponse,
        inputVo: C6Service1TkV1TestController.Api7SendKafkaTopicMessageTestInputVo
    )


    ////
    // (ProcessBuilder 샘플)
    fun api8ProcessBuilderTest(
        httpServletResponse: HttpServletResponse,
        javaEnvironmentPath: String?
    ): C6Service1TkV1TestController.Api8ProcessBuilderTestOutputVo?


    ////
    // (입력받은 폰트 파일의 내부 이름을 반환)
    fun api9CheckFontFileInnerName(
        httpServletResponse: HttpServletResponse,
        inputVo: C6Service1TkV1TestController.Api9CheckFontFileInnerNameInputVo
    ): C6Service1TkV1TestController.Api9CheckFontFileInnerNameOutputVo?


    ////
    // (AES256 암호화 테스트)
    fun api10Aes256EncryptTest(
        httpServletResponse: HttpServletResponse,
        plainText: String,
        alg: C6Service1TkV1TestController.Api10Aes256EncryptTestCryptoAlgEnum,
        initializationVector: String,
        encryptionKey: String
    ): C6Service1TkV1TestController.Api10Aes256EncryptTestOutputVo?


    ////
    // (AES256 복호화 테스트)
    fun api11Aes256DecryptTest(
        httpServletResponse: HttpServletResponse,
        encryptedText: String,
        alg: C6Service1TkV1TestController.Api11Aes256DecryptTestCryptoAlgEnum,
        initializationVector: String,
        encryptionKey: String
    ): C6Service1TkV1TestController.Api11Aes256DecryptTestOutputVo?


    ////
    // (Jsoup 태그 조작 테스트)
    fun api12JsoupTest(httpServletResponse: HttpServletResponse, fix: Boolean): String?
}