package guru.springframework.sfgjms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAsync                                // Enable Spring Boot to perform tasks from a task pool
@EnableScheduling                           // Activate scheduling
@Configuration                              // Scan this class for methods annotated with @Bean
public class TaskConfig {

    // Setup TaskExecutor bean, which enables us to run asynchronous tasks
    @Bean
    TaskExecutor taskExecutor() {
        return new SimpleAsyncTaskExecutor();
    }
}
