package io.example.kafkademo.producer

import org.quartz.*
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Bean


@Configuration
class ProducerConfiguration {

    @Bean
    fun tickJobDetail(): JobDetail {

        return JobBuilder.newJob(TickJob::class.java)
                .withIdentity("tickJob")
                .storeDurably()
                .build()
    }

    @Bean
    fun tickJobTrigger(): Trigger {

        val scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(2)
                .repeatForever()

        return TriggerBuilder.newTrigger()
                .forJob(tickJobDetail())
                .withIdentity("tickTrigger")
                .withSchedule(scheduleBuilder)
                .build()
    }
}