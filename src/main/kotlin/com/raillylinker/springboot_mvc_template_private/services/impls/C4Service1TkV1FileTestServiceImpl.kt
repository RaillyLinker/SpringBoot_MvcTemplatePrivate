package com.raillylinker.springboot_mvc_template_private.services.impls

import com.raillylinker.springboot_mvc_template_private.use_components.AwsS3UtilComponent
import com.raillylinker.springboot_mvc_template_private.use_components.CustomUtil
import com.raillylinker.springboot_mvc_template_private.controllers.C4Service1TkV1FileTestController
import com.raillylinker.springboot_mvc_template_private.services.C4Service1TkV1FileTestService
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.ClassPathResource
import org.springframework.core.io.InputStreamResource
import org.springframework.core.io.Resource
import org.springframework.http.ContentDisposition
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.util.StringUtils
import java.io.File
import java.io.FileOutputStream
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.zip.ZipOutputStream

@Service
class C4Service1TkV1FileTestServiceImpl(
    // (프로젝트 실행시 사용 설정한 프로필명 (ex : dev8080, prod80, local8080, 설정 안하면 default 반환))
    @Value("\${spring.profiles.active:default}") private var activeProfile: String,

    private val customUtil: CustomUtil,

    // (AWS S3 유틸 객체)
    private val awsS3UtilComponent: AwsS3UtilComponent
): C4Service1TkV1FileTestService {
    // <멤버 변수 공간>
    private val classLogger: Logger = LoggerFactory.getLogger(this::class.java)


    // ---------------------------------------------------------------------------------------------
    // <공개 메소드 공간>
    override fun api1UploadToServerTest(
        httpServletResponse: HttpServletResponse,
        inputVo: C4Service1TkV1FileTestController.Api1UploadToServerTestInputVo
    ): C4Service1TkV1FileTestController.Api1UploadToServerTestOutputVo? {
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

        val savedFileName = "${fileNameWithOutExtension}(${
            LocalDateTime.now().atZone(ZoneId.systemDefault())
                .format(DateTimeFormatter.ofPattern("yyyy_MM_dd_'T'_HH_mm_ss_SSS_z"))
        }).$fileExtension"

        // multipartFile 을 targetPath 에 저장
        inputVo.multipartFile.transferTo(
            // 파일 저장 경로와 파일명(with index) 을 합친 path 객체
            saveDirectoryPath.resolve(savedFileName).normalize()
        )

        httpServletResponse.status = HttpStatus.OK.value()

        return C4Service1TkV1FileTestController.Api1UploadToServerTestOutputVo("http://127.0.0.1:8080/service1/tk/v1/file-test/download-from-server/$savedFileName")
    }

    override fun api2FileDownloadTest(httpServletResponse: HttpServletResponse, fileName: String): ResponseEntity<Resource>? {
        // 프로젝트 루트 경로 (프로젝트 settings.gradle 이 있는 경로)
        val projectRootAbsolutePathString: String = File("").absolutePath

        // 파일 절대 경로 및 파일명 (프로젝트 루트 경로에 있는 by_product_files/test 폴더를 기준으로 함)
        val serverFilePathObject =
            Paths.get("$projectRootAbsolutePathString/by_product_files/test/$fileName")

        when {
            Files.isDirectory(serverFilePathObject) -> {
                // 파일이 디렉토리일때
                httpServletResponse.status = HttpStatus.NO_CONTENT.value()
                httpServletResponse.setHeader("api-result-code", "1")
                return null
            }

            Files.notExists(serverFilePathObject) -> {
                // 파일이 없을 때
                httpServletResponse.status = HttpStatus.NO_CONTENT.value()
                httpServletResponse.setHeader("api-result-code", "1")
                return null
            }
        }

        httpServletResponse.status = HttpStatus.OK.value()
        return ResponseEntity<Resource>(
            InputStreamResource(Files.newInputStream(serverFilePathObject)),
            HttpHeaders().apply {
                this.contentDisposition = ContentDisposition.builder("attachment")
                    .filename(fileName, StandardCharsets.UTF_8)
                    .build()
                this.add(HttpHeaders.CONTENT_TYPE, Files.probeContentType(serverFilePathObject))
            },
            HttpStatus.OK
        )
    }


    ////
    override fun api3FilesToZipTest(httpServletResponse: HttpServletResponse) {
        // 프로젝트 루트 경로 (프로젝트 settings.gradle 이 있는 경로)
        val projectRootAbsolutePathString: String = File("").absolutePath

        // 파일 경로 리스트
        val filePathList = listOf(
            "$projectRootAbsolutePathString/module-api-sample/src/main/resources/static/for_c4_n3_files_to_zip_test/1.txt",
            "$projectRootAbsolutePathString/module-api-sample/src/main/resources/static/for_c4_n3_files_to_zip_test/2.xlsx",
            "$projectRootAbsolutePathString/module-api-sample/src/main/resources/static/for_c4_n3_files_to_zip_test/3.png",
            "$projectRootAbsolutePathString/module-api-sample/src/main/resources/static/for_c4_n3_files_to_zip_test/4.mp4"
        )

        // 파일 저장 디렉토리 경로
        val saveDirectoryPathString = "./by_product_files/test"
        val saveDirectoryPath = Paths.get(saveDirectoryPathString).toAbsolutePath().normalize()
        // 파일 저장 디렉토리 생성
        Files.createDirectories(saveDirectoryPath)

        // 확장자 포함 파일명 생성
        val fileTargetPath = saveDirectoryPath.resolve(
            "zipped_${
                LocalDateTime.now().atZone(ZoneId.systemDefault())
                    .format(DateTimeFormatter.ofPattern("yyyy_MM_dd_'T'_HH_mm_ss_SSS_z"))
            }.zip"
        ).normalize()

        // 압축 파일 생성
        FileOutputStream(fileTargetPath.toFile()).use { fileOutputStream ->
            ZipOutputStream(fileOutputStream).use { zipOutputStream ->
                for (filePath in filePathList) {
                    val file = File(filePath)
                    if (file.exists()) {
                        customUtil.addToZip(file, file.name, zipOutputStream)
                    }
                }
            }
        }

        httpServletResponse.status = HttpStatus.OK.value()
    }


    ////
    override fun api3Dot1FolderToZipTest(httpServletResponse: HttpServletResponse) {
        // 프로젝트 루트 경로 (프로젝트 settings.gradle 이 있는 경로)
        val projectRootAbsolutePathString: String = File("").absolutePath

        // 압축 대상 디렉토리
        val sourceDir = File("$projectRootAbsolutePathString/module-api-sample/src/main/resources/static/for_c4_n3_files_to_zip_test")

        // 파일 저장 디렉토리 경로
        val saveDirectoryPathString = "./by_product_files/test"
        val saveDirectoryPath = Paths.get(saveDirectoryPathString).toAbsolutePath().normalize()
        // 파일 저장 디렉토리 생성
        Files.createDirectories(saveDirectoryPath)

        // 확장자 포함 파일명 생성
        val fileTargetPath = saveDirectoryPath.resolve(
            "zipped_${
                LocalDateTime.now().atZone(ZoneId.systemDefault())
                    .format(DateTimeFormatter.ofPattern("yyyy_MM_dd_'T'_HH_mm_ss_SSS_z"))
            }.zip"
        ).normalize()

        // 압축 파일 생성
        FileOutputStream(fileTargetPath.toFile()).use { fileOutputStream ->
            ZipOutputStream(fileOutputStream).use { zipOutputStream ->
                customUtil.compressDirectoryToZip(sourceDir, sourceDir.name, zipOutputStream)
            }
        }

        httpServletResponse.status = HttpStatus.OK.value()
    }


    ////
    override fun api4UnzipTest(httpServletResponse: HttpServletResponse) {
        // 프로젝트 루트 경로 (프로젝트 settings.gradle 이 있는 경로)
        val projectRootAbsolutePathString: String = File("").absolutePath
        val filePathString =
            "$projectRootAbsolutePathString/module-api-sample/src/main/resources/static/for_c4_n4_unzip_test/test.zip"

        // 파일 저장 디렉토리 경로
        val saveDirectoryPathString = "./by_product_files/test"
        val saveDirectoryPath = Paths.get(saveDirectoryPathString).toAbsolutePath().normalize()
        // 파일 저장 디렉토리 생성
        Files.createDirectories(saveDirectoryPath)

        // 요청 시간을 문자열로
        val timeString = LocalDateTime.now().atZone(ZoneId.systemDefault())
            .format(DateTimeFormatter.ofPattern("yyyy_MM_dd_'T'_HH_mm_ss_SSS_z"))

        // 확장자 포함 파일명 생성
        val saveFileName = "unzipped_${timeString}/"

        val fileTargetPath = saveDirectoryPath.resolve(saveFileName).normalize()

        customUtil.unzipFile(filePathString, fileTargetPath)

        httpServletResponse.status = HttpStatus.OK.value()
    }


    ////
    override fun api5ForClientSideImageTest(httpServletResponse: HttpServletResponse, delayTimeSecond: Int): ResponseEntity<Resource>? {
        if (delayTimeSecond < 0) {
            httpServletResponse.status = HttpStatus.BAD_REQUEST.value()
            return null
        }

        Thread.sleep(delayTimeSecond * 1000L)

        val file: Resource = ClassPathResource("static/for_c4_n5_for_client_side_image_test/client_image_test.jpg")

        httpServletResponse.status = HttpStatus.OK.value()
        return ResponseEntity<Resource>(
            file,
            HttpHeaders().apply {
                this.contentDisposition = ContentDisposition.builder("attachment")
                    .filename("client_image_test.jpg", StandardCharsets.UTF_8)
                    .build()
                this.add(HttpHeaders.CONTENT_TYPE, "image/jpeg")
            },
            HttpStatus.OK
        )
    }


    ////
    override fun api6AwsS3UploadTest(
        httpServletResponse: HttpServletResponse,
        inputVo: C4Service1TkV1FileTestController.Api6AwsS3UploadTestInputVo
    ): C4Service1TkV1FileTestController.Api6AwsS3UploadTestOutputVo? {
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

        val savedFileName = "${fileNameWithOutExtension}(${
            LocalDateTime.now().atZone(ZoneId.systemDefault())
                .format(DateTimeFormatter.ofPattern("yyyy_MM_dd_'T'_HH_mm_ss_SSS_z"))
        }).$fileExtension"

        val uploadedFileFullUrl: String = awsS3UtilComponent.upload(
            inputVo.multipartFile,
            savedFileName,
            if (activeProfile == "prod80") {
                "test-prod/test"
            } else {
                "test-dev/test"
            }
        )

        httpServletResponse.status = HttpStatus.OK.value()

        return C4Service1TkV1FileTestController.Api6AwsS3UploadTestOutputVo(uploadedFileFullUrl)
    }


    ////
    override fun api7GetFileContentToStringTest(
        httpServletResponse: HttpServletResponse,
        uploadFileName: String
    ): C4Service1TkV1FileTestController.Api7GetFileContentToStringTestOutputVo? {
        httpServletResponse.status = HttpStatus.OK.value()

        return C4Service1TkV1FileTestController.Api7GetFileContentToStringTestOutputVo(
            awsS3UtilComponent.getTextFileString(
                if (activeProfile == "prod80") {
                    "petlogon-contract-prod/test"
                } else {
                    "petlogon-contract-dev/test"
                },
                uploadFileName
            )
        )
    }


    ////
    override fun api8DeleteAwsS3FileTest(httpServletResponse: HttpServletResponse, deleteFileName: String) {
        // AWS 파일 삭제
        awsS3UtilComponent.delete(
            if (activeProfile == "prod80") {
                "petlogon-contract-prod/test"
            } else {
                "petlogon-contract-dev/test"
            },
            deleteFileName
        )

        httpServletResponse.status = HttpStatus.OK.value()
    }
}