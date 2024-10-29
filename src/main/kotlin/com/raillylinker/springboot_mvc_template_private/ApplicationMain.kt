package com.raillylinker.springboot_mvc_template_private

import com.raillylinker.springboot_mvc_template_private.data_sources.const_objects.ProjectConst
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.mongodb.config.EnableMongoAuditing
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.annotation.EnableScheduling
import java.util.*

@EnableScheduling // 스케쥴러 사용 설정
@EnableAsync // 스케쥴러의 Async 사용 설정
@EnableMongoAuditing // MongoDB 에서 @CreatedDate, @LastModifiedDate 사용 설정
@ComponentScan(
	// Bean 스캔할 모듈 패키지 리스트
	basePackages =
	[
		"com.raillylinker.springboot_mvc_template_private"
	]
)
@SpringBootApplication(
	// Using generated security password 워닝을 피하기 위해 SpringSecurity 비밀번호 자동 생성 비활성화
	exclude = [UserDetailsServiceAutoConfiguration::class]
)
class ApplicationMain {
	@Bean
	fun init() = CommandLineRunner {
		// 서버 타임존 설정
		TimeZone.setDefault(TimeZone.getTimeZone(ProjectConst.SYSTEM_TIME_ZONE))
		// println("Current TimeZone: ${TimeZone.getDefault().id}")
	}
}

fun main(args: Array<String>) {
	runApplication<ApplicationMain>(*args)
}