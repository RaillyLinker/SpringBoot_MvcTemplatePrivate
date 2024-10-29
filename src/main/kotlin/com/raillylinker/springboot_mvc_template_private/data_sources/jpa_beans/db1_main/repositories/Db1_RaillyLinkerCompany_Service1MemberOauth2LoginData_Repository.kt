package com.raillylinker.springboot_mvc_template_private.data_sources.jpa_beans.db1_main.repositories

import com.raillylinker.springboot_mvc_template_private.data_sources.jpa_beans.db1_main.entities.Db1_RaillyLinkerCompany_Service1MemberData
import com.raillylinker.springboot_mvc_template_private.data_sources.jpa_beans.db1_main.entities.Db1_RaillyLinkerCompany_Service1MemberOauth2LoginData
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

// (JPA 레포지토리)
// : 함수 작성 명명법에 따라 데이터베이스 SQL 동작을 자동지원
@Repository
interface Db1_RaillyLinkerCompany_Service1MemberOauth2LoginData_Repository :
    JpaRepository<Db1_RaillyLinkerCompany_Service1MemberOauth2LoginData, Long> {
    fun findByOauth2TypeCodeAndOauth2Id(
        oauth2TypeCode: Byte,
        snsId: String
    ): Db1_RaillyLinkerCompany_Service1MemberOauth2LoginData?

    fun existsByOauth2TypeCodeAndOauth2Id(
        oauth2TypeCode: Byte,
        snsId: String
    ): Boolean

    fun findAllByService1MemberData(
        service1MemberData: Db1_RaillyLinkerCompany_Service1MemberData
    ): List<Db1_RaillyLinkerCompany_Service1MemberOauth2LoginData>

    fun existsByService1MemberData(
        service1MemberData: Db1_RaillyLinkerCompany_Service1MemberData
    ): Boolean
}