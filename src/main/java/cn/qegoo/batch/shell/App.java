package cn.qegoo.batch.shell;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

public class App {
    public static void main(String[] args) {
        System.out.println(new Date());
        final String[] springConfig = {"jobs.xml"};
        final ApplicationContext context = new ClassPathXmlApplicationContext(springConfig);
        final JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
        final Job job = (Job) context.getBean("helloWorldJob");
        try {
            final JobExecution execution = jobLauncher.run(job, new JobParameters());
            System.out.println("Exit Status : " + execution.getStatus());
            System.exit(0);
        } catch (final Exception e) {
            e.printStackTrace();
        }

        System.out.println("Done" + new Date());

    }

}
