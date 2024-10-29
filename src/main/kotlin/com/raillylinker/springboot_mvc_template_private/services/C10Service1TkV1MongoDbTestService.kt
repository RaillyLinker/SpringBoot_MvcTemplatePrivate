package com.raillylinker.springboot_mvc_template_private.services

import com.raillylinker.springboot_mvc_template_private.controllers.C10Service1TkV1MongoDbTestController
import jakarta.servlet.http.HttpServletResponse

interface C10Service1TkV1MongoDbTestService {
    fun api1InsertDocumentTest(
        httpServletResponse: HttpServletResponse,
        inputVo: C10Service1TkV1MongoDbTestController.Api1InsertDocumentTestInputVo
    ): C10Service1TkV1MongoDbTestController.Api1InsertDocumentTestOutputVo?

    ////
    fun api2DeleteAllDocumentTest(httpServletResponse: HttpServletResponse)

    ////
    fun api3DeleteDocumentTest(httpServletResponse: HttpServletResponse, id: String)

    ////
    fun api4SelectAllDocumentsTest(httpServletResponse: HttpServletResponse): C10Service1TkV1MongoDbTestController.Api4SelectAllDocumentsTestOutputVo?


    fun api12TransactionRollbackTest(
        httpServletResponse: HttpServletResponse
    )


    fun api13NoTransactionRollbackTest(
        httpServletResponse: HttpServletResponse
    )
}