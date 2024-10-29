package com.raillylinker.springboot_mvc_template_private.controllers

import com.fasterxml.jackson.annotation.JsonProperty
import com.raillylinker.springboot_mvc_template_private.services.C1Service
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.ModelAndView

@Tag(name = "root APIs", description = "C1 : Root 경로에 대한 API 컨트롤러")
@Controller
class C1Controller(
    private val service: C1Service
) {
    // <멤버 변수 공간>


    // ---------------------------------------------------------------------------------------------
    // <매핑 함수 공간>
    @Operation(
        summary = "N1 : 홈페이지",
        description = "루트 홈페이지를 반환합니다.\n\n"
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "정상 동작"
            )
        ]
    )
    @GetMapping(
        path = ["", "/"],
        consumes = [MediaType.ALL_VALUE],
        produces = [MediaType.TEXT_HTML_VALUE]
    )
    fun api1GetRoot(
        @Parameter(hidden = true)
        httpServletResponse: HttpServletResponse
    ): ModelAndView? {
        return service.api1GetRoot(httpServletResponse)
    }


    ////
    @Operation(
        summary = "N2 : Project Runtime Config Redis Key-Value 모두 조회 테스트",
        description = "Project 의 런타임 설정 저장용 Redis Table 에 저장된 모든 Key-Value 를 조회합니다.\n\n"
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "정상 동작"
            )
        ]
    )
    @GetMapping(
        path = ["/project-runtime-configs"],
        consumes = [MediaType.ALL_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    @ResponseBody
    fun api2SelectAllProjectRuntimeConfigsRedisKeyValue(
        @Parameter(hidden = true)
        httpServletResponse: HttpServletResponse
    ): Api2SelectAllProjectRuntimeConfigsRedisKeyValueOutputVo? {
        return service.api2SelectAllProjectRuntimeConfigsRedisKeyValue(
            httpServletResponse
        )
    }

    data class Api2SelectAllProjectRuntimeConfigsRedisKeyValueOutputVo(
        @Schema(description = "Key-Value 리스트", required = true)
        @JsonProperty("keyValueList")
        val keyValueList: List<KeyValueVo>,
    ) {
        @Schema(description = "Key-Value 객체")
        data class KeyValueVo(
            @Schema(description = "Key", required = true, example = "testing")
            @JsonProperty("key")
            val key: String,
            @Schema(description = "설정 IP 정보 리스트", required = true)
            @JsonProperty("ipInfoList")
            var ipInfoList: List<IpDescVo>
        ) {
            data class IpDescVo(
                // 설정 ip
                val ip: String,
                // ip 설명
                val desc: String
            )
        }
    }


    ////
    @Operation(
        summary = "N3 : Redis Project Runtime Config actuatorAllowIpList 입력 테스트",
        description = "Redis 의 Project Runtime Config actuatorAllowIpList 를 입력합니다.\n\n" +
                "이 정보는 본 프로젝트 actuator 접근 허용 IP 를 뜻합니다.\n\n"
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "정상 동작"
            )
        ]
    )
    @PostMapping(
        path = ["/project-runtime-config-actuator-allow-ip-list"],
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.ALL_VALUE]
    )
    @ResponseBody
    fun api3InsertProjectRuntimeConfigActuatorAllowIpList(
        @Parameter(hidden = true)
        httpServletResponse: HttpServletResponse,
        @RequestBody inputVo: Api3InsertProjectRuntimeConfigActuatorAllowIpListInputVo
    ) {
        service.api3InsertProjectRuntimeConfigActuatorAllowIpList(httpServletResponse, inputVo)
    }

    data class Api3InsertProjectRuntimeConfigActuatorAllowIpListInputVo(
        @Schema(
            description = "설정 IP 정보 리스트",
            required = true,
            example = "[{\"ip\":\"127.0.0.1\",\"desc\":\"localHost\"}]"
        )
        @JsonProperty("ipInfoList")
        var ipInfoList: List<IpDescVo>
    ) {
        data class IpDescVo(
            // 설정 ip
            val ip: String,
            // ip 설명
            val desc: String
        )
    }


    ////
    @Operation(
        summary = "N4 : Redis Project Runtime Config loggingDenyIpList 입력 테스트",
        description = "Redis 의 Project Runtime Config loggingDenyIpList 를 입력합니다.\n\n" +
                "이 정보는 본 프로젝트에서 로깅하지 않을 IP 를 뜻합니다.\n\n"
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "정상 동작"
            )
        ]
    )
    @PostMapping(
        path = ["/project-runtime-config-logging-deny-ip-list"],
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.ALL_VALUE]
    )
    @ResponseBody
    fun api4InsertProjectRuntimeConfigLoggingDenyIpList(
        @Parameter(hidden = true)
        httpServletResponse: HttpServletResponse,
        @RequestBody inputVo: Api4InsertProjectRuntimeConfigLoggingDenyIpListInputVo
    ) {
        service.api4InsertProjectRuntimeConfigLoggingDenyIpList(httpServletResponse, inputVo)
    }

    data class Api4InsertProjectRuntimeConfigLoggingDenyIpListInputVo(
        @Schema(
            description = "설정 IP 정보 리스트",
            required = true,
            example = "[{\"ip\":\"127.0.0.1\",\"desc\":\"localHost\"}]"
        )
        @JsonProperty("ipInfoList")
        var ipInfoList: List<IpDescVo>
    ) {
        data class IpDescVo(
            // 설정 ip
            val ip: String,
            // ip 설명
            val desc: String
        )
    }
}