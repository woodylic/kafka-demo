package io.example.kafkademo.producer

import org.quartz.JobExecutionContext
import org.springframework.scheduling.quartz.QuartzJobBean
import org.quartz.JobExecutionException
import java.util.Date

class TickJob : QuartzJobBean() {

    @Throws(JobExecutionException::class)
    override fun executeInternal(context: JobExecutionContext) {
        println("Tick! The time is ${Date()}!")
    }

}