package io.example.kafkademo.producer

import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Bean
import org.springframework.scheduling.quartz.SchedulerFactoryBean
import org.springframework.context.ApplicationContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.quartz.CronTriggerFactoryBean
import org.springframework.scheduling.quartz.JobDetailFactoryBean


@Configuration
class QuartzConfiguration {

    @Autowired
    private lateinit var applicationContext: ApplicationContext

    @Bean
    fun quartzScheduler(): SchedulerFactoryBean {

        val quartzScheduler = SchedulerFactoryBean()

        quartzScheduler.setOverwriteExistingJobs(true)
        quartzScheduler.setSchedulerName("quartzScheduler")

        // custom job factory of spring with DI support for @Autowired!
        val jobFactory = AutowiringSpringBeanJobFactory()
        jobFactory.setApplicationContext(applicationContext)
        quartzScheduler.setJobFactory(jobFactory)

        quartzScheduler.setTriggers(processTickJobTrigger().`object`)

        return quartzScheduler
    }

//    @Bean
//    fun tickJobDetail(): JobDetail {
//
//        return JobBuilder.newJob(TickJob::class.java)
//                .withIdentity("processTickJob")
//                .storeDurably()
//                .build()
//    }

    @Bean
    fun processTickJob(): JobDetailFactoryBean {
        val jobDetailFactory = JobDetailFactoryBean()
        jobDetailFactory.setJobClass(TickJob::class.java)
        jobDetailFactory.setGroup("default")
        return jobDetailFactory
    }

//    @Bean
//    fun processTickJobTrigger(): Trigger {
//
//        val scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
//                .withIntervalInSeconds(2)
//                .repeatForever()
//
//        return TriggerBuilder.newTrigger()
//                .forJob(tickJobDetail())
//                .withIdentity("tickTrigger")
//                .withSchedule(scheduleBuilder)
//                .build()
//    }

    fun processTickJobTrigger(): CronTriggerFactoryBean {

        val cronTriggerFactoryBean = CronTriggerFactoryBean()
        cronTriggerFactoryBean.setJobDetail(processTickJob().`object`!!)
        cronTriggerFactoryBean.setCronExpression("*/2 * * * * ?");
        cronTriggerFactoryBean.setGroup("group");

        return cronTriggerFactoryBean;
    }
}