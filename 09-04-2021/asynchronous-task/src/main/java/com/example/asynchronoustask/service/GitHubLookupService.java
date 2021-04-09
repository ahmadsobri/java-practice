package com.example.asynchronoustask.service;

import com.example.asynchronoustask.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@Service
public class GitHubLookupService {
    //    By default, Spring uses a SimpleAsyncTaskExecutor to actually run these methods asynchronously.
    //    The defaults can be overridden at two levels – at the application level or at the individual method level.
    // can custom error handle with implement AsyncUncaughtExceptionHandler


    private static final Logger logger = LoggerFactory.getLogger(GitHubLookupService.class);

    private final RestTemplate restTemplate;

    public GitHubLookupService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    //override SimpleAsyncTaskExecutor in method level,
    @Async("threadPoolTaskExecutor")
    public CompletableFuture<User> findUser(String user) throws InterruptedException {
        logger.info("Looking up " + user);
        String url = String.format("https://api.github.com/users/%s", user);
        User results = restTemplate.getForObject(url, User.class);
        // Artificial delay of 1s for demonstration purposes
        Thread.sleep(1000);
        return CompletableFuture.completedFuture(results);
    }

    //    needs to be declared in a configuration class :

    //    @Configuration
    //    @EnableAsync
    //    public class SpringAsyncConfig {
    //      @Bean(name = "threadPoolTaskExecutor")
    //      public Executor threadPoolTaskExecutor(){..}
    //    }

    //or directly create beans (look at main class AsynchronousTaskApplication) => the beans name must be same Async method
}