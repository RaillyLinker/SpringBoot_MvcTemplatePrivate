package com.raillylinker.springboot_mvc_template_private.services

import com.raillylinker.springboot_mvc_template_private.controllers.C4Service1TkV1FileTestController
import jakarta.servlet.http.HttpServletResponse
import org.springframework.core.io.Resource
import org.springframework.http.ResponseEntity

interface C4Service1TkV1FileTestService {
    // (by_product_files/test 폴더로 파일 업로드)
    fun api1UploadToServerTest(
        httpServletResponse: HttpServletResponse,
        inputVo: C4Service1TkV1FileTestController.Api1UploadToServerTestInputVo
    ): C4Service1TkV1FileTestController.Api1UploadToServerTestOutputVo?


    ////
    // (by_product_files/test 폴더에서 파일 다운받기)
    fun api2FileDownloadTest(httpServletResponse: HttpServletResponse, fileName: String): ResponseEntity<Resource>?


    ////
    // (파일 리스트 zip 압축 테스트)
    fun api3FilesToZipTest(httpServletResponse: HttpServletResponse)


    ////
    // (폴더 zip 압축 테스트)
    fun api3Dot1FolderToZipTest(httpServletResponse: HttpServletResponse)


    ////
    // (zip 압축 파일 해제 테스트)
    fun api4UnzipTest(httpServletResponse: HttpServletResponse)


    ////
    // (클라이언트 이미지 표시 테스트용 API)
    fun api5ForClientSideImageTest(
        httpServletResponse: HttpServletResponse,
        delayTimeSecond: Int
    ): ResponseEntity<Resource>?


    ////
    // (AWS S3 로 파일 업로드)
    fun api6AwsS3UploadTest(
        httpServletResponse: HttpServletResponse,
        inputVo: C4Service1TkV1FileTestController.Api6AwsS3UploadTestInputVo
    ): C4Service1TkV1FileTestController.Api6AwsS3UploadTestOutputVo?


    ////
    // (AWS S3 파일의 내용을 String 으로 가져오기)
    fun api7GetFileContentToStringTest(
        httpServletResponse: HttpServletResponse,
        uploadFileName: String
    ): C4Service1TkV1FileTestController.Api7GetFileContentToStringTestOutputVo?


    ////
    // (AWS S3 파일을 삭제하기)
    fun api8DeleteAwsS3FileTest(httpServletResponse: HttpServletResponse, deleteFileName: String)
}