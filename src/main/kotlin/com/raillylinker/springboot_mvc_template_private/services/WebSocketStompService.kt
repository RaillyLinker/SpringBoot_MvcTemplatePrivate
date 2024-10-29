package com.raillylinker.springboot_mvc_template_private.services

import com.raillylinker.springboot_mvc_template_private.controllers.WebSocketStompController

interface WebSocketStompService {
    // (/test 로 받아서 /topic 토픽을 구독중인 모든 클라이언트에 메시지 전달)
    fun api1SendToTopicTest(inputVo: WebSocketStompController.Api1SendToTopicTestInputVo): WebSocketStompController.TopicVo
}