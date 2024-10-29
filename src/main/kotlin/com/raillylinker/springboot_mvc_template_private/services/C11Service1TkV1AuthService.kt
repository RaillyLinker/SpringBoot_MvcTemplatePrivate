package com.raillylinker.springboot_mvc_template_private.services

import com.raillylinker.springboot_mvc_template_private.controllers.C11Service1TkV1AuthController
import jakarta.servlet.http.HttpServletResponse
import org.springframework.core.io.Resource
import org.springframework.http.ResponseEntity

interface C11Service1TkV1AuthService {
    fun api1NoLoggedInAccessTest(httpServletResponse: HttpServletResponse): String?


    ////
    fun api2LoggedInAccessTest(httpServletResponse: HttpServletResponse, authorization: String): String?


    ////
    fun api3AdminAccessTest(httpServletResponse: HttpServletResponse, authorization: String): String?


    ////
    fun api4DeveloperAccessTest(httpServletResponse: HttpServletResponse, authorization: String): String?


    ////
    fun api4Dot9DoExpireAccessToken(
        httpServletResponse: HttpServletResponse,
        memberUid: Long,
        inputVo: C11Service1TkV1AuthController.Api4Dot9DoExpireAccessTokenInputVo
    )

    ////
    fun api5LoginWithPassword(
        httpServletResponse: HttpServletResponse,
        inputVo: C11Service1TkV1AuthController.Api5LoginWithPasswordInputVo
    ): C11Service1TkV1AuthController.Api5Api7Api7Dot1Api9LoginOutputVo?


    ////
    fun api6GetOAuth2AccessToken(
        httpServletResponse: HttpServletResponse,
        oauth2TypeCode: Int,
        oauth2Code: String
    ): C11Service1TkV1AuthController.Api6GetOAuth2AccessTokenOutputVo?


    ////
    fun api7LoginWithOAuth2AccessToken(
        httpServletResponse: HttpServletResponse,
        inputVo: C11Service1TkV1AuthController.Api7LoginWithOAuth2AccessTokenInputVo
    ): C11Service1TkV1AuthController.Api5Api7Api7Dot1Api9LoginOutputVo?


    ////
    fun api7Dot1LoginWithOAuth2IdToken(
        httpServletResponse: HttpServletResponse,
        inputVo: C11Service1TkV1AuthController.Api7Dot1LoginWithOAuth2IdTokenInputVo
    ): C11Service1TkV1AuthController.Api5Api7Api7Dot1Api9LoginOutputVo?


    ////
    fun api8Logout(authorization: String, httpServletResponse: HttpServletResponse)


    ////
    fun api9ReissueJwt(
        authorization: String?,
        inputVo: C11Service1TkV1AuthController.Api9ReissueJwtInputVo,
        httpServletResponse: HttpServletResponse
    ): C11Service1TkV1AuthController.Api5Api7Api7Dot1Api9LoginOutputVo?


    ////
    fun api10DeleteAllJwtOfAMember(authorization: String, httpServletResponse: HttpServletResponse)


    ////
    fun api10Dot1GetMemberInfo(
        httpServletResponse: HttpServletResponse,
        authorization: String
    ): C11Service1TkV1AuthController.Api10Dot1GetMemberInfoOutputVo?


    ////
    fun api11CheckIdDuplicate(
        httpServletResponse: HttpServletResponse,
        id: String
    ): C11Service1TkV1AuthController.Api11CheckIdDuplicateOutputVo?


    ////
    fun api12UpdateId(httpServletResponse: HttpServletResponse, authorization: String, id: String)


    ////
    fun api12Dot9JoinTheMembershipForTest(
        httpServletResponse: HttpServletResponse,
        inputVo: C11Service1TkV1AuthController.Api12Dot9JoinTheMembershipForTestInputVo
    )


    ////
    fun api13SendEmailVerificationForJoin(
        httpServletResponse: HttpServletResponse,
        inputVo: C11Service1TkV1AuthController.Api13SendEmailVerificationForJoinInputVo
    ): C11Service1TkV1AuthController.Api13SendEmailVerificationForJoinOutputVo?


    ////
    fun api14CheckEmailVerificationForJoin(
        httpServletResponse: HttpServletResponse,
        verificationUid: Long,
        email: String,
        verificationCode: String
    )


    ////
    fun api15JoinTheMembershipWithEmail(
        httpServletResponse: HttpServletResponse,
        inputVo: C11Service1TkV1AuthController.Api15JoinTheMembershipWithEmailInputVo
    )


    ////
    fun api16SendPhoneVerificationForJoin(
        httpServletResponse: HttpServletResponse,
        inputVo: C11Service1TkV1AuthController.Api16SendPhoneVerificationForJoinInputVo
    ): C11Service1TkV1AuthController.Api16SendPhoneVerificationForJoinOutputVo?


    ////
    fun api17CheckPhoneVerificationForJoin(
        httpServletResponse: HttpServletResponse,
        verificationUid: Long,
        phoneNumber: String,
        verificationCode: String
    )


    ////
    fun api18JoinTheMembershipWithPhoneNumber(
        httpServletResponse: HttpServletResponse,
        inputVo: C11Service1TkV1AuthController.Api18JoinTheMembershipWithPhoneNumberInputVo
    )


    ////
    fun api19CheckOauth2AccessTokenVerificationForJoin(
        httpServletResponse: HttpServletResponse,
        inputVo: C11Service1TkV1AuthController.Api19CheckOauth2AccessTokenVerificationForJoinInputVo
    ): C11Service1TkV1AuthController.Api19CheckOauth2AccessTokenVerificationForJoinOutputVo?


    ////
    fun api19Dot1CheckOauth2IdTokenVerificationForJoin(
        httpServletResponse: HttpServletResponse,
        inputVo: C11Service1TkV1AuthController.Api19Dot1CheckOauth2IdTokenVerificationForJoinInputVo
    ): C11Service1TkV1AuthController.Api19Dot1CheckOauth2IdTokenVerificationForJoinOutputVo?


    ////
    fun api20JoinTheMembershipWithOauth2(
        httpServletResponse: HttpServletResponse,
        inputVo: C11Service1TkV1AuthController.Api20JoinTheMembershipWithOauth2InputVo
    )


    ////
    fun api21UpdateAccountPassword(
        httpServletResponse: HttpServletResponse,
        authorization: String,
        inputVo: C11Service1TkV1AuthController.Api21UpdateAccountPasswordInputVo
    )


    ////
    fun api22SendEmailVerificationForFindPassword(
        httpServletResponse: HttpServletResponse,
        inputVo: C11Service1TkV1AuthController.Api22SendEmailVerificationForFindPasswordInputVo
    ): C11Service1TkV1AuthController.Api22SendEmailVerificationForFindPasswordOutputVo?


    ////
    fun api23CheckEmailVerificationForFindPassword(
        httpServletResponse: HttpServletResponse,
        verificationUid: Long,
        email: String,
        verificationCode: String
    )


    ////
    fun api24FindPasswordWithEmail(
        httpServletResponse: HttpServletResponse,
        inputVo: C11Service1TkV1AuthController.Api24FindPasswordWithEmailInputVo
    )


    ////
    fun api25SendPhoneVerificationForFindPassword(
        httpServletResponse: HttpServletResponse,
        inputVo: C11Service1TkV1AuthController.Api25SendPhoneVerificationForFindPasswordInputVo
    ): C11Service1TkV1AuthController.Api25SendPhoneVerificationForFindPasswordOutputVo?


    ////
    fun api26CheckPhoneVerificationForFindPassword(
        httpServletResponse: HttpServletResponse,
        verificationUid: Long,
        phoneNumber: String,
        verificationCode: String
    )


    ////
    fun api27FindPasswordWithPhoneNumber(
        httpServletResponse: HttpServletResponse,
        inputVo: C11Service1TkV1AuthController.Api27FindPasswordWithPhoneNumberInputVo
    )


    ////
    fun api29GetMyEmailList(
        httpServletResponse: HttpServletResponse,
        authorization: String
    ): C11Service1TkV1AuthController.Api29GetMyEmailListOutputVo?


    ////
    fun api30GetMyPhoneNumberList(
        httpServletResponse: HttpServletResponse,
        authorization: String
    ): C11Service1TkV1AuthController.Api30GetMyPhoneNumberListOutputVo?


    ////
    fun api31(
        httpServletResponse: HttpServletResponse,
        authorization: String
    ): C11Service1TkV1AuthController.Api31GetMyOauth2ListOutputVo?


    ////
    fun api32SendEmailVerificationForAddNewEmail(
        httpServletResponse: HttpServletResponse,
        inputVo: C11Service1TkV1AuthController.Api32SendEmailVerificationForAddNewEmailInputVo,
        authorization: String
    ): C11Service1TkV1AuthController.Api32SendEmailVerificationForAddNewEmailOutputVo?


    ////
    fun api33CheckEmailVerificationForAddNewEmail(
        httpServletResponse: HttpServletResponse,
        verificationUid: Long,
        email: String,
        verificationCode: String,
        authorization: String
    )


    ////
    fun api34AddNewEmail(
        httpServletResponse: HttpServletResponse,
        inputVo: C11Service1TkV1AuthController.Api34AddNewEmailInputVo,
        authorization: String
    ): C11Service1TkV1AuthController.Api34AddNewEmailOutputVo?


    ////
    fun api35DeleteMyEmail(
        httpServletResponse: HttpServletResponse,
        emailUid: Long,
        authorization: String
    )


    ////
    fun api36SendPhoneVerificationForAddNewPhoneNumber(
        httpServletResponse: HttpServletResponse,
        inputVo: C11Service1TkV1AuthController.Api36SendPhoneVerificationForAddNewPhoneNumberInputVo,
        authorization: String
    ): C11Service1TkV1AuthController.Api36SendPhoneVerificationForAddNewPhoneNumberOutputVo?


    ////
    fun api37CheckPhoneVerificationForAddNewPhoneNumber(
        httpServletResponse: HttpServletResponse,
        verificationUid: Long,
        phoneNumber: String,
        verificationCode: String,
        authorization: String
    )


    ////
    fun api38AddNewPhoneNumber(
        httpServletResponse: HttpServletResponse,
        inputVo: C11Service1TkV1AuthController.Api38AddNewPhoneNumberInputVo,
        authorization: String
    ): C11Service1TkV1AuthController.Api38AddNewPhoneNumberOutputVo?


    ////
    fun api39DeleteMyPhoneNumber(
        httpServletResponse: HttpServletResponse,
        phoneUid: Long,
        authorization: String
    )


    ////
    fun api40AddNewOauth2WithAccessToken(
        httpServletResponse: HttpServletResponse,
        inputVo: C11Service1TkV1AuthController.Api40AddNewOauth2WithAccessTokenInputVo,
        authorization: String
    )


    ////
    fun api40Dot1AddNewOauth2WithIdToken(
        httpServletResponse: HttpServletResponse,
        inputVo: C11Service1TkV1AuthController.Api40Dot1AddNewOauth2WithIdTokenInputVo,
        authorization: String
    )


    ////
    fun api41DeleteMyOauth2(
        httpServletResponse: HttpServletResponse,
        oAuth2Uid: Long,
        authorization: String
    )


    ////
    fun api42WithdrawalMembership(
        httpServletResponse: HttpServletResponse,
        authorization: String
    )


    ////
    fun api43GetMyProfileList(
        httpServletResponse: HttpServletResponse,
        authorization: String
    ): C11Service1TkV1AuthController.Api43GetMyProfileListOutputVo?


    ////
    fun api44GetMyFrontProfile(
        httpServletResponse: HttpServletResponse,
        authorization: String
    ): C11Service1TkV1AuthController.Api44GetMyFrontProfileOutputVo?


    ////
    fun api45SetMyFrontProfile(httpServletResponse: HttpServletResponse, authorization: String, profileUid: Long?)


    ////
    fun api46DeleteMyProfile(authorization: String, httpServletResponse: HttpServletResponse, profileUid: Long)


    ////
    fun api47AddNewProfile(
        httpServletResponse: HttpServletResponse,
        authorization: String,
        inputVo: C11Service1TkV1AuthController.Api47AddNewProfileInputVo
    ): C11Service1TkV1AuthController.Api47AddNewProfileOutputVo?


    ////
    fun api48DownloadProfileFile(
        httpServletResponse: HttpServletResponse,
        fileName: String
    ): ResponseEntity<Resource>?


    ////
    fun api49GetMyFrontEmail(
        httpServletResponse: HttpServletResponse,
        authorization: String
    ): C11Service1TkV1AuthController.Api49GetMyFrontEmailOutputVo?


    ////
    fun api50SetMyFrontEmail(httpServletResponse: HttpServletResponse, authorization: String, emailUid: Long?)


    ////
    fun api51GetMyFrontPhoneNumber(
        httpServletResponse: HttpServletResponse,
        authorization: String
    ): C11Service1TkV1AuthController.Api51GetMyFrontPhoneNumberOutputVo?


    ////
    fun api52SetMyFrontPhoneNumber(
        httpServletResponse: HttpServletResponse,
        authorization: String,
        phoneNumberUid: Long?
    )


    ////
    fun api53SelectAllRedisKeyValueSample(httpServletResponse: HttpServletResponse): C11Service1TkV1AuthController.Api53SelectAllRedisKeyValueSampleOutputVo?
}