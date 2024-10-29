package com.raillylinker.springboot_mvc_template_private.services.impls

import com.raillylinker.springboot_mvc_template_private.util_components.ActuatorWhiteList
import com.raillylinker.springboot_mvc_template_private.controllers.C1Controller
import com.raillylinker.springboot_mvc_template_private.data_sources.redis_map_components.redis1_main.Redis1_Map_RuntimeConfigIpList
import com.raillylinker.springboot_mvc_template_private.services.C1Service
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.servlet.ModelAndView

@Service
class C1ServiceImpl(
    // (프로젝트 실행시 사용 설정한 프로필명 (ex : dev8080, prod80, local8080, 설정 안하면 default 반환))
    @Value("\${spring.profiles.active:default}") private var activeProfile: String,

    private val redis1RuntimeConfigIpList: Redis1_Map_RuntimeConfigIpList,

    private val actuatorWhiteList: ActuatorWhiteList
) : C1Service {
    // <멤버 변수 공간>
    private val classLogger: Logger = LoggerFactory.getLogger(this::class.java)


    // ---------------------------------------------------------------------------------------------
    // <공개 메소드 공간>
    override fun api1GetRoot(
        httpServletResponse: HttpServletResponse
    ): ModelAndView? {
        val mv = ModelAndView()
        mv.viewName = "forward:/main/sc/v1/home"

        return mv
    }


    ////
    override fun api2SelectAllProjectRuntimeConfigsRedisKeyValue(httpServletResponse: HttpServletResponse): C1Controller.Api2SelectAllProjectRuntimeConfigsRedisKeyValueOutputVo? {
        val testEntityListVoList =
            ArrayList<C1Controller.Api2SelectAllProjectRuntimeConfigsRedisKeyValueOutputVo.KeyValueVo>()

        // actuator 저장 정보 가져오기
        val actuatorWhiteList = actuatorWhiteList.getActuatorWhiteList()

        val actuatorIpDescVoList =
            ArrayList<C1Controller.Api2SelectAllProjectRuntimeConfigsRedisKeyValueOutputVo.KeyValueVo.IpDescVo>()
        for (actuatorWhite in actuatorWhiteList) {
            actuatorIpDescVoList.add(
                C1Controller.Api2SelectAllProjectRuntimeConfigsRedisKeyValueOutputVo.KeyValueVo.IpDescVo(
                    actuatorWhite.ip,
                    actuatorWhite.desc
                )
            )
        }

        testEntityListVoList.add(
            C1Controller.Api2SelectAllProjectRuntimeConfigsRedisKeyValueOutputVo.KeyValueVo(
                Redis1_Map_RuntimeConfigIpList.KeyEnum.ACTUATOR_ALLOW_IP_LIST.name,
                actuatorIpDescVoList
            )
        )

        // 전체 조회 테스트
        val loggingDenyInfo =
            redis1RuntimeConfigIpList.findKeyValue(Redis1_Map_RuntimeConfigIpList.KeyEnum.LOGGING_DENY_IP_LIST.name)

        if (loggingDenyInfo != null) {
            val ipDescVoList =
                ArrayList<C1Controller.Api2SelectAllProjectRuntimeConfigsRedisKeyValueOutputVo.KeyValueVo.IpDescVo>()
            for (ipInfo in loggingDenyInfo.value.ipInfoList) {
                ipDescVoList.add(
                    C1Controller.Api2SelectAllProjectRuntimeConfigsRedisKeyValueOutputVo.KeyValueVo.IpDescVo(
                        ipInfo.ip,
                        ipInfo.desc
                    )
                )
            }

            testEntityListVoList.add(
                C1Controller.Api2SelectAllProjectRuntimeConfigsRedisKeyValueOutputVo.KeyValueVo(
                    Redis1_Map_RuntimeConfigIpList.KeyEnum.LOGGING_DENY_IP_LIST.name,
                    ipDescVoList
                )
            )
        }

        httpServletResponse.status = HttpStatus.OK.value()
        return C1Controller.Api2SelectAllProjectRuntimeConfigsRedisKeyValueOutputVo(
            testEntityListVoList
        )
    }


    ////
    override fun api3InsertProjectRuntimeConfigActuatorAllowIpList(
        httpServletResponse: HttpServletResponse,
        inputVo: C1Controller.Api3InsertProjectRuntimeConfigActuatorAllowIpListInputVo
    ) {
        val actuatorAllowIpVoList: MutableList<ActuatorWhiteList.ActuatorAllowIpVo> = mutableListOf()

        for (ipDescInfo in inputVo.ipInfoList) {
            actuatorAllowIpVoList.add(
                ActuatorWhiteList.ActuatorAllowIpVo(
                    ipDescInfo.ip,
                    ipDescInfo.desc
                )
            )
        }

        actuatorWhiteList.setActuatorWhiteList(actuatorAllowIpVoList)

        httpServletResponse.status = HttpStatus.OK.value()
    }


    ////
    override fun api4InsertProjectRuntimeConfigLoggingDenyIpList(
        httpServletResponse: HttpServletResponse,
        inputVo: C1Controller.Api4InsertProjectRuntimeConfigLoggingDenyIpListInputVo
    ) {
        val ipDescVoList: MutableList<Redis1_Map_RuntimeConfigIpList.ValueVo.IpDescVo> = mutableListOf()

        for (ipDescInfo in inputVo.ipInfoList) {
            ipDescVoList.add(
                Redis1_Map_RuntimeConfigIpList.ValueVo.IpDescVo(
                    ipDescInfo.ip,
                    ipDescInfo.desc
                )
            )
        }

        redis1RuntimeConfigIpList.saveKeyValue(
            Redis1_Map_RuntimeConfigIpList.KeyEnum.LOGGING_DENY_IP_LIST.name,
            Redis1_Map_RuntimeConfigIpList.ValueVo(
                ipDescVoList
            ),
            null
        )

        httpServletResponse.status = HttpStatus.OK.value()
    }
}