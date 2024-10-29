package com.raillylinker.springboot_mvc_template_private.controllers

import com.fasterxml.jackson.annotation.JsonProperty
import com.raillylinker.springboot_mvc_template_private.services.C4Service1TkV1FileTestService
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

@Tag(name = "/service1/tk/v1/file-test APIs", description = "C4 : 파일을 다루는 테스트 API 컨트롤러")
@Controller
@RequestMapping("/service1/tk/v1/file-test")
class C4Service1TkV1FileTestController(
    private val service: C4Service1TkV1FileTestService
) {
    // <멤버 변수 공간>


    // ---------------------------------------------------------------------------------------------
    // <매핑 함수 공간>
    @Operation(
        summary = "N1 : by_product_files/test 폴더로 파일 업로드",
        description = "multipart File 을 하나 업로드하여 서버의 by_product_files/test 폴더에 저장\n\n"
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
        path = ["/upload-to-server"],
        consumes = [MediaType.MULTIPART_FORM_DATA_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    @ResponseBody
    fun api1UploadToServerTest(
        @Parameter(hidden = true)
        httpServletResponse: HttpServletResponse,
        @ModelAttribute
        @RequestBody
        inputVo: Api1UploadToServerTestInputVo
    ): Api1UploadToServerTestOutputVo? {
        return service.api1UploadToServerTest(httpServletResponse, inputVo)
    }

    data class Api1UploadToServerTestInputVo(
        @Schema(description = "업로드 파일", required = true)
        @JsonProperty("multipartFile")
        val multipartFile: MultipartFile
    )

    data class Api1UploadToServerTestOutputVo(
        @Schema(
            description = "파일 다운로드 경로", required = true,
            example = "http://127.0.0.1:8080/service1/tk/v1/file-test/download-from-server/file.txt"
        )
        @JsonProperty("fileDownloadFullUrl")
        val fileDownloadFullUrl: String
    )


    ////
    @Operation(
        summary = "N2 : by_product_files/test 폴더에서 파일 다운받기",
        description = "업로드 API 를 사용하여 by_product_files/test 로 업로드한 파일을 다운로드\n\n"
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
        path = ["/download-from-server/{fileName}"],
        consumes = [MediaType.ALL_VALUE],
        produces = [MediaType.APPLICATION_OCTET_STREAM_VALUE]
    )
    @ResponseBody
    fun api2FileDownloadTest(
        @Parameter(hidden = true)
        httpServletResponse: HttpServletResponse,
        @Parameter(name = "fileName", description = "by_product_files/test 폴더 안의 파일명", example = "sample.txt")
        @PathVariable("fileName")
        fileName: String
    ): ResponseEntity<Resource>? {
        return service.api2FileDownloadTest(httpServletResponse, fileName)
    }


    ////
    @Operation(
        summary = "N3 : 파일 리스트 zip 압축 테스트",
        description = "파일들을 zip 타입으로 압축하여 by_product_files/test 폴더에 저장\n\n"
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
        path = ["/zip-files"],
        consumes = [MediaType.ALL_VALUE],
        produces = [MediaType.ALL_VALUE]
    )
    @ResponseBody
    fun api3FilesToZipTest(
        @Parameter(hidden = true)
        httpServletResponse: HttpServletResponse
    ) {
        service.api3FilesToZipTest(httpServletResponse)
    }


    ////
    @Operation(
        summary = "N3.1 : 폴더 zip 압축 테스트",
        description = "폴더를 통째로 zip 타입으로 압축하여 by_product_files/test 폴더에 저장\n\n"
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
        path = ["/zip-folder"],
        consumes = [MediaType.ALL_VALUE],
        produces = [MediaType.ALL_VALUE]
    )
    @ResponseBody
    fun api3Dot1FolderToZipTest(
        @Parameter(hidden = true)
        httpServletResponse: HttpServletResponse
    ) {
        service.api3Dot1FolderToZipTest(httpServletResponse)
    }


    ////
    @Operation(
        summary = "N4 : zip 압축 파일 해제 테스트",
        description = "zip 압축 파일을 해제하여 by_product_files/test 폴더에 저장\n\n"
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
        path = ["/unzip-file"],
        consumes = [MediaType.ALL_VALUE],
        produces = [MediaType.ALL_VALUE]
    )
    @ResponseBody
    fun api4UnzipTest(
        @Parameter(hidden = true)
        httpServletResponse: HttpServletResponse
    ) {
        service.api4UnzipTest(httpServletResponse)
    }


    ////
    @Operation(
        summary = "N5 : 클라이언트 이미지 표시 테스트용 API",
        description = "서버에서 이미지를 반환합니다. 클라이언트에서의 이미지 표시 시 PlaceHolder, Error 처리에 대응 할 수 있습니다.\n\n"
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
        path = ["/client-image-test"],
        consumes = [MediaType.ALL_VALUE],
        produces = [MediaType.APPLICATION_OCTET_STREAM_VALUE]
    )
    @ResponseBody
    fun api5ForClientSideImageTest(
        @Parameter(hidden = true)
        httpServletResponse: HttpServletResponse,
        @Parameter(name = "delayTimeSecond", description = "이미지 파일 반환 대기 시간(0 은 바로, 음수는 에러 발생)", example = "0")
        @RequestParam("delayTimeSecond")
        delayTimeSecond: Int
    ): ResponseEntity<Resource>? {
        return service.api5ForClientSideImageTest(httpServletResponse, delayTimeSecond)
    }


    ////
    @Operation(
        summary = "N6 : AWS S3 로 파일 업로드",
        description = "multipart File 을 하나 업로드하여 AWS S3 에 저장\n\n"
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
        path = ["/upload-to-s3"],
        consumes = [MediaType.MULTIPART_FORM_DATA_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    @ResponseBody
    fun api6AwsS3UploadTest(
        @Parameter(hidden = true)
        httpServletResponse: HttpServletResponse,
        @ModelAttribute
        @RequestBody
        inputVo: Api6AwsS3UploadTestInputVo
    ): Api6AwsS3UploadTestOutputVo? {
        return service.api6AwsS3UploadTest(httpServletResponse, inputVo)
    }

    data class Api6AwsS3UploadTestInputVo(
        @Schema(description = "업로드 파일", required = true)
        @JsonProperty("multipartFile")
        val multipartFile: MultipartFile
    )

    data class Api6AwsS3UploadTestOutputVo(
        @Schema(
            description = "파일 다운로드 경로", required = true,
            example = "http://127.0.0.1:8080/service1/tk/v1/file-test/download-from-server/file.txt"
        )
        @JsonProperty("fileDownloadFullUrl")
        val fileDownloadFullUrl: String
    )


    ////
    @Operation(
        summary = "N7 : AWS S3 파일의 내용을 String 으로 가져오기",
        description = "AWS S3 파일의 내용을 String 으로 가져옵니다.\n\n"
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
        path = ["/read-from-s3"],
        consumes = [MediaType.ALL_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    @ResponseBody
    fun api7GetFileContentToStringTest(
        @Parameter(hidden = true)
        httpServletResponse: HttpServletResponse,
        @Parameter(
            name = "uploadFileName",
            description = "업로드한 파일 이름",
            example = "file.txt"
        )
        @RequestParam("uploadFileName")
        uploadFileName: String
    ): Api7GetFileContentToStringTestOutputVo? {
        return service.api7GetFileContentToStringTest(
            httpServletResponse,
            uploadFileName
        )
    }

    data class Api7GetFileContentToStringTestOutputVo(
        @Schema(description = "읽은 파일 내용", required = true, example = "testString")
        @JsonProperty("fileContent")
        val v: String
    )


    ////
    @Operation(
        summary = "N8 : AWS S3 파일을 삭제하기",
        description = "AWS S3 파일을 삭제합니다.\n\n"
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "정상 동작"
            )
        ]
    )
    @DeleteMapping(
        path = ["/delete-from-s3"],
        consumes = [MediaType.ALL_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    @ResponseBody
    fun api8DeleteAwsS3FileTest(
        @Parameter(hidden = true)
        httpServletResponse: HttpServletResponse,
        @Parameter(
            name = "deleteFileName",
            description = "삭제할 파일 이름",
            example = "file.txt"
        )
        @RequestParam("deleteFileName")
        deleteFileName: String
    ) {
        service.api8DeleteAwsS3FileTest(
            httpServletResponse,
            deleteFileName
        )
    }
}