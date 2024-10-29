package com.raillylinker.springboot_mvc_template_private.kafka_components.producers

import com.raillylinker.springboot_mvc_template_private.configurations.kafka_configs.Kafka1MainConfig
import com.raillylinker.springboot_mvc_template_private.kafka_components.consumers.Kafka1MainConsumer
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class Kafka1MainProducer(
    @Qualifier(Kafka1MainConfig.PRODUCER_BEAN_NAME) private val kafka1MainProducerTemplate: KafkaTemplate<String, Any>,
) {
    // <멤버 변수 공간>
    private val classLogger: Logger = LoggerFactory.getLogger(this::class.java)

    // ---------------------------------------------------------------------------------------------
    // <공개 메소드 공간>
    // (testTopic1 에 메시지 발송)
    fun sendMessageToTestTopic1(message: Kafka1MainConsumer.TestTopic1Group0ListenerInputVo) {
        // kafkaProducer1 에 토픽 메세지 발행
        kafka1MainProducerTemplate.send("testTopic1", message)
    }
}