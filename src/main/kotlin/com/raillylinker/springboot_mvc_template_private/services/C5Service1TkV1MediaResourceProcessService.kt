package com.raillylinker.springboot_mvc_template_private.services

import com.raillylinker.springboot_mvc_template_private.controllers.C5Service1TkV1MediaResourceProcessController
import jakarta.servlet.http.HttpServletResponse
import org.springframework.core.io.Resource
import org.springframework.http.ResponseEntity

interface C5Service1TkV1MediaResourceProcessService {
    // (정적 이미지 파일(지원 타입은 description 에 후술)을 업로드 하여 리사이징 후 다운)
    fun api1ResizeImage(
        inputVo: C5Service1TkV1MediaResourceProcessController.Api1ResizeImageInputVo,
        httpServletResponse: HttpServletResponse
    ): ResponseEntity<Resource>?


    ////
    // (서버에 저장된 움직이는 Gif 이미지 파일에서 프레임을 PNG 이미지 파일로 분리한 후 by_product_files/test 폴더 안에 저장)
    fun api2SplitAnimatedGif(
        httpServletResponse: HttpServletResponse
    )


    ////
    // (서버에 저장된 움직이는 PNG 이미지 프레임들을 움직이는 Gif 파일로 병합 후 by_product_files/test 폴더 안에 저장)
    fun api3MergeImagesToAnimatedGif(httpServletResponse: HttpServletResponse)


    ////
    // (동적 GIF 이미지 파일을 업로드 하여 리사이징 후 다운)
    fun api4ResizeGifImage(
        inputVo: C5Service1TkV1MediaResourceProcessController.Api4ResizeGifImageInputVo,
        httpServletResponse: HttpServletResponse
    ): ResponseEntity<Resource>?


    ////
    // (서명 생성 테스트)
    fun api5CreateSignature(
        httpServletResponse: HttpServletResponse,
        inputVo: C5Service1TkV1MediaResourceProcessController.Api5CreateSignatureInputVo
    )
}