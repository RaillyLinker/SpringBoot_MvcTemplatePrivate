package com.raillylinker.springboot_mvc_template_private.util_components

interface ActuatorWhiteList {
    // (Actuator 화이트 리스트 반환)
    fun getActuatorWhiteList(): List<ActuatorAllowIpVo>

    // (Actuator 화이트 리스트 설정)
    fun setActuatorWhiteList(actuatorAllowIpVoList: List<ActuatorAllowIpVo>)

    data class ActuatorAllowIpVo(
        // 설정 ip
        val ip: String,
        // ip 설명
        val desc: String
    )
}