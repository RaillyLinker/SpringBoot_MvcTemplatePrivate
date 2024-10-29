package com.raillylinker.springboot_mvc_template_private.data_sources.jpa_beans.db1_main.repositories

import com.raillylinker.springboot_mvc_template_private.data_sources.jpa_beans.db1_main.entities.Db1_RaillyLinkerCompany_Service1LogInTokenHistory
import com.raillylinker.springboot_mvc_template_private.data_sources.jpa_beans.db1_main.entities.Db1_RaillyLinkerCompany_Service1MemberData
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

// (JPA 레포지토리)
// : 함수 작성 명명법에 따라 데이터베이스 SQL 동작을 자동지원
@Repository
interface Db1_RaillyLinkerCompany_Service1LogInTokenHistory_Repository :
    JpaRepository<Db1_RaillyLinkerCompany_Service1LogInTokenHistory, Long> {
    fun findByTokenTypeAndAccessTokenAndLogoutDate(
        tokenType: String,
        accessToken: String,
        logoutDate: LocalDateTime?
    ): Db1_RaillyLinkerCompany_Service1LogInTokenHistory?

    fun findAllByService1MemberDataAndLogoutDate(
        service1MemberData: Db1_RaillyLinkerCompany_Service1MemberData,
        logoutDate: LocalDateTime?
    ): List<Db1_RaillyLinkerCompany_Service1LogInTokenHistory>

    fun findAllByService1MemberDataAndAccessTokenExpireWhenAfter(
        service1MemberData: Db1_RaillyLinkerCompany_Service1MemberData,
        accessTokenExpireWhenAfter: LocalDateTime
    ): List<Db1_RaillyLinkerCompany_Service1LogInTokenHistory>
}