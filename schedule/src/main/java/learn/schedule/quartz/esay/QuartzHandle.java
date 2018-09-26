package learn.schedule.quartz.esay;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

import static org.quartz.JobBuilder.*;
import static org.quartz.SimpleScheduleBuilder.*;
import static org.quartz.TriggerBuilder.*;

public class QuartzHandle {

    public static void main(String[] args){
        try {
            //创建 schedule
            SchedulerFactory factory = new StdSchedulerFactory();
            Scheduler scheduler = factory.getScheduler();

            JobDetail jobDetail = newJob(HelloJob.class)
                    .withIdentity("hello_job","group1")
                    .build();


            Trigger trigger = newTrigger()
                    .withIdentity("hello_trigger","group1")
                    .startAt(new Date())
                    .withSchedule(simpleSchedule().withIntervalInSeconds(5).repeatForever())
                    .build();

            scheduler.scheduleJob(jobDetail,trigger);
            scheduler.start();

            Thread.sleep(10*1000L);

            scheduler.shutdown(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
