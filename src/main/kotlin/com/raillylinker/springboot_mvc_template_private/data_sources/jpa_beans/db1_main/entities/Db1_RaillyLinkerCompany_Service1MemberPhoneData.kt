package com.raillylinker.springboot_mvc_template_private.data_sources.jpa_beans.db1_main.entities

import jakarta.persistence.*
import org.hibernate.annotations.Comment
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime

@Entity
@Table(
    name = "service1_member_phone_data",
    catalog = "railly_linker_company",
    uniqueConstraints = [
        UniqueConstraint(columnNames = ["phone_number"])
    ]
)
@Comment("Service1 계정 회원 전화 정보 테이블")
class Db1_RaillyLinkerCompany_Service1MemberPhoneData(
    @ManyToOne
    @JoinColumn(name = "service1_member_uid", nullable = false)
    @Comment("멤버 고유번호(railly_linker_company.service1_member_data.uid)")
    var service1MemberData: Db1_RaillyLinkerCompany_Service1MemberData,

    @Column(name = "phone_number", nullable = false, columnDefinition = "VARCHAR(45)")
    @Comment("전화번호(국가번호 + 전화번호, 중복 비허용)")
    var phoneNumber: String
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uid", columnDefinition = "BIGINT UNSIGNED")
    @Comment("행 고유값")
    var uid: Long? = null

    @Column(name = "row_create_date", nullable = false, columnDefinition = "DATETIME(3)")
    @CreationTimestamp
    @Comment("행 생성일")
    var rowCreateDate: LocalDateTime? = null

    @Column(name = "row_update_date", nullable = false, columnDefinition = "DATETIME(3)")
    @UpdateTimestamp
    @Comment("행 수정일")
    var rowUpdateDate: LocalDateTime? = null

    @OneToMany(mappedBy = "frontService1MemberPhoneData", fetch = FetchType.LAZY)
    var service1MemberDataList: MutableList<Db1_RaillyLinkerCompany_Service1MemberData> = mutableListOf()


    // ---------------------------------------------------------------------------------------------
    // <중첩 클래스 공간>

}