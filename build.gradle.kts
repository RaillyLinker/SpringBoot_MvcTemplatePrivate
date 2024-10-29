plugins {
	kotlin("jvm") version "1.9.25"
	kotlin("plugin.spring") version "1.9.25"
	id("org.springframework.boot") version "3.3.5"
	id("io.spring.dependency-management") version "1.1.6"

	// 추가
	kotlin("plugin.allopen") version "2.0.21" // allOpen 에 지정한 어노테이션으로 만든 클래스에 open 키워드를 적용
	kotlin("plugin.noarg") version "2.0.21" // noArg 에 지정한 어노테이션으로 만든 클래스에 자동으로 no-arg 생성자를 생성

	// QueryDSL Kapt
	kotlin("kapt") version "2.0.21"
}

group = "com.raillylinker"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	// (기본)
	implementation("org.springframework.boot:spring-boot-starter:3.3.4")
	implementation("org.jetbrains.kotlin:kotlin-reflect:2.0.21")
	testImplementation("org.springframework.boot:spring-boot-starter-test:3.3.4")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5:2.0.21")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher:1.11.2")

	// (Spring Starter Web)
	// : 스프링 부트 웹 개발
	implementation("org.springframework.boot:spring-boot-starter-web:3.3.4")

	// (Swagger)
	// : API 자동 문서화
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.6.0")

	// (ThymeLeaf)
	// : 웹 뷰 라이브러리
	implementation("org.springframework.boot:spring-boot-starter-thymeleaf:3.3.4")

	// (GSON)
	// : Json - Object 라이브러리
	implementation("com.google.code.gson:gson:2.11.0")

	// (OkHttp3)
	implementation("com.squareup.okhttp3:okhttp:4.12.0")
	implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

	// (폰트 파일 내부 이름 가져오기용)
	implementation("org.apache.pdfbox:pdfbox:3.0.3")

	// (JSOUP - HTML 태그 조작)
	implementation("org.jsoup:jsoup:1.18.1")

	// (Spring Security)
	// : 스프링 부트 보안
	implementation("org.springframework.boot:spring-boot-starter-security:3.3.4")
	testImplementation("org.springframework.security:spring-security-test:6.3.3")

	// (JWT)
	// : JWT 인증 토큰 라이브러리
	runtimeOnly("io.jsonwebtoken:jjwt-impl:0.12.6")
	implementation("io.jsonwebtoken:jjwt-api:0.12.6")
	runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.12.6")

	// (Spring Actuator)
	// : 서버 모니터링 정보
	implementation("org.springframework.boot:spring-boot-starter-actuator:3.3.4")
	runtimeOnly("io.micrometer:micrometer-registry-prometheus:1.13.6")

	// (Apache codec)
	implementation("commons-codec:commons-codec:1.17.1")

	// (retrofit2 네트워크 요청)
	implementation("com.squareup.retrofit2:retrofit:2.11.0")
	implementation("com.squareup.retrofit2:converter-gson:2.11.0")
	implementation("com.squareup.retrofit2:converter-scalars:2.11.0")

	// (OkHttp3)
	implementation("com.squareup.okhttp3:okhttp:4.12.0")
	implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

	// (Jackson Core)
	implementation("com.fasterxml.jackson.core:jackson-annotations:2.18.0")

	// (Redis)
	// : 메모리 키 값 데이터 구조 스토어
	implementation("org.springframework.boot:spring-boot-starter-data-redis:3.3.4")

	// (MongoDB)
	implementation("org.springframework.boot:spring-boot-starter-data-mongodb:3.3.4")

	// (AOP)
	implementation("org.springframework.boot:spring-boot-starter-aop:3.3.4")

	// (AWS)
	implementation("io.awspring.cloud:spring-cloud-starter-aws:2.4.4")

	// (Excel File Read Write)
	// : 액셀 파일 입출력 라이브러리
	implementation("org.apache.poi:poi:5.3.0")
	implementation("org.apache.poi:poi-ooxml:5.3.0")
	implementation("sax:sax:2.0.1")

	// (HTML 2 PDF)
	// : HTML -> PDF 변환 라이브러리
	implementation("org.xhtmlrenderer:flying-saucer-pdf:9.9.5")

	// (Spring email)
	// : 스프링 이메일 발송
	implementation("org.springframework.boot:spring-boot-starter-mail:3.3.4")

	// (JPA)
	// : DB ORM
	implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.3.4")
	implementation("com.fasterxml.jackson.datatype:jackson-datatype-hibernate5:2.18.0")
	implementation("org.hibernate:hibernate-validator:8.0.1.Final")
	implementation("com.mysql:mysql-connector-j:9.0.0") // MySQL

	// (QueryDSL)
	implementation("com.querydsl:querydsl-jpa:5.1.0:jakarta")
	kapt("com.querydsl:querydsl-apt:5.1.0:jakarta")
	kapt("jakarta.annotation:jakarta.annotation-api")
	kapt("jakarta.persistence:jakarta.persistence-api")

	// (Kafka)
	implementation("org.springframework.kafka:spring-kafka:3.2.4")

	// (jackson)
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.18.0")

	// (WebSocket)
	// : 웹소켓
	implementation("org.springframework.boot:spring-boot-starter-websocket:3.3.4")

	// (ORM 관련 라이브러리)
	// WebSocket STOMP Controller 에서 입력값 매핑시 사용됨
	implementation("javax.persistence:persistence-api:1.0.2")
}

// (Querydsl 설정부 추가 - start)
val generated = file("src/main/generated")
// querydsl QClass 파일 생성 위치를 지정
tasks.withType<JavaCompile> {
	options.generatedSourceOutputDirectory.set(generated)
}
// kotlin source set 에 querydsl QClass 위치 추가
sourceSets {
	main {
		kotlin.srcDirs += generated
	}
}
// gradle clean 시에 QClass 디렉토리 삭제
tasks.named("clean") {
	doLast {
		generated.deleteRecursively()
	}
}
kapt {
	generateStubs = true
}
// (Querydsl 설정부 추가 - end)

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

// kotlin jpa : 아래의 어노테이션 클래스에 no-arg 생성자를 생성
noArg {
	annotation("jakarta.persistence.Entity")
	annotation("jakarta.persistence.MappedSuperclass")
	annotation("jakarta.persistence.Embeddable")
}

// kotlin jpa : 아래의 어노테이션 클래스를 open class 로 자동 설정
allOpen {
	annotation("jakarta.persistence.Entity")
	annotation("jakarta.persistence.MappedSuperclass")
	annotation("jakarta.persistence.Embeddable")
}