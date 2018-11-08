package io.example.kafkademo.producer

import org.quartz.JobExecutionContext
import org.springframework.scheduling.quartz.QuartzJobBean
import org.quartz.JobExecutionException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component
import java.util.Date

@Component
class TickJob : QuartzJobBean() {

    @Autowired
    private lateinit var kafkaTemplate: KafkaTemplate<String, String>

    @Throws(JobExecutionException::class)
    override fun executeInternal(context: JobExecutionContext) {
        kafkaTemplate.send("my_topic", "${Date()}")
        println("Tick! The time is ${Date()}!")
    }

}