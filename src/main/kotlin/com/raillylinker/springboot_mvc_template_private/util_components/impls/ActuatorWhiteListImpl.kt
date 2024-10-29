package com.raillylinker.springboot_mvc_template_private.util_components.impls

import com.raillylinker.springboot_mvc_template_private.util_components.ActuatorWhiteList
import com.raillylinker.springboot_mvc_template_private.data_sources.redis_map_components.redis1_main.Redis1_Map_RuntimeConfigIpList
import org.springframework.stereotype.Component

@Component
class ActuatorWhiteListImpl(
    private val redis1RuntimeConfigIpList: Redis1_Map_RuntimeConfigIpList
) : ActuatorWhiteList {
    override fun getActuatorWhiteList(): List<ActuatorWhiteList.ActuatorAllowIpVo> {
        val keyValue =
            redis1RuntimeConfigIpList.findKeyValue(Redis1_Map_RuntimeConfigIpList.KeyEnum.ACTUATOR_ALLOW_IP_LIST.name)

        val actuatorAllowIpList: MutableList<ActuatorWhiteList.ActuatorAllowIpVo> = mutableListOf()
        if (keyValue != null) {
            for (vl in keyValue.value.ipInfoList) {
                actuatorAllowIpList.add(
                    ActuatorWhiteList.ActuatorAllowIpVo(
                        vl.ip,
                        vl.desc
                    )
                )
            }
        }

        return actuatorAllowIpList
    }

    override fun setActuatorWhiteList(actuatorAllowIpVoList: List<ActuatorWhiteList.ActuatorAllowIpVo>) {
        val ipDescVoList: MutableList<Redis1_Map_RuntimeConfigIpList.ValueVo.IpDescVo> = mutableListOf()

        for (ipDescInfo in actuatorAllowIpVoList) {
            ipDescVoList.add(
                Redis1_Map_RuntimeConfigIpList.ValueVo.IpDescVo(
                    ipDescInfo.ip,
                    ipDescInfo.desc
                )
            )
        }

        redis1RuntimeConfigIpList.saveKeyValue(
            Redis1_Map_RuntimeConfigIpList.KeyEnum.ACTUATOR_ALLOW_IP_LIST.name,
            Redis1_Map_RuntimeConfigIpList.ValueVo(
                ipDescVoList
            ),
            null
        )
    }
}