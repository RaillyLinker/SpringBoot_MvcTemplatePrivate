package com.raillylinker.springboot_mvc_template_private.data_sources.jpa_beans.db1_main.repositories

import com.raillylinker.springboot_mvc_template_private.data_sources.jpa_beans.db1_main.entities.Db1_RaillyLinkerCompany_Service1MemberData
import com.raillylinker.springboot_mvc_template_private.data_sources.jpa_beans.db1_main.entities.Db1_RaillyLinkerCompany_Service1MemberProfileData
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

// (JPA 레포지토리)
// : 함수 작성 명명법에 따라 데이터베이스 SQL 동작을 자동지원
@Repository
interface Db1_RaillyLinkerCompany_Service1MemberProfileData_Repository :
    JpaRepository<Db1_RaillyLinkerCompany_Service1MemberProfileData, Long> {
    fun findAllByService1MemberData(
        service1MemberData: Db1_RaillyLinkerCompany_Service1MemberData
    ): List<Db1_RaillyLinkerCompany_Service1MemberProfileData>

    fun findByUidAndService1MemberData(
        uid: Long,
        service1MemberData: Db1_RaillyLinkerCompany_Service1MemberData
    ): Db1_RaillyLinkerCompany_Service1MemberProfileData?
}