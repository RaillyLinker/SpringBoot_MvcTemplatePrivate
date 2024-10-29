package com.raillylinker.springboot_mvc_template_private.data_sources.jpa_beans.db1_main.repositories

import com.raillylinker.springboot_mvc_template_private.data_sources.jpa_beans.db1_main.entities.Db1_Template_TestMap
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface Db1_Template_TestMap_Repository : JpaRepository<Db1_Template_TestMap, Long> {
}