package com.example.asynchronoustask;

import com.example.asynchronoustask.entity.User;
import com.example.asynchronoustask.service.GitHubLookupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.CompletableFuture;

@SpringBootApplication
public class AsynchronousTaskApplication implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(AsynchronousTaskApplication.class);

	@Autowired
	private GitHubLookupService gitHubLookupService;

	public static void main(String[] args) {
		SpringApplication.run(AsynchronousTaskApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Start the clock
		long start = System.currentTimeMillis();

		// Kick of multiple, asynchronous lookups
		CompletableFuture<User> page1 = gitHubLookupService.findUser("PivotalSoftware");
		CompletableFuture<User> page2 = gitHubLookupService.findUser("CloudFoundry");
		CompletableFuture<User> page3 = gitHubLookupService.findUser("Spring-Projects");
		CompletableFuture<User> page4 = gitHubLookupService.findUser("RameshMF");
		// Wait until they are all done
		CompletableFuture.allOf(page1, page2, page3, page4).join();

		// Print results, including elapsed time
		logger.info("Elapsed time: " + ((System.currentTimeMillis() - start)/1000)+" second");
		logger.info("--> " + page1.get());
		logger.info("--> " + page2.get());
		logger.info("--> " + page3.get());
		logger.info("--> " + page4.get());
	}

	// overridden SimpleAsyncTaskExecutor at the application level
	// as an executor of method Async CompletableFuture<User> findUser(){..}
	@Bean("threadPoolTaskExecutor") //he beans name must be same Async method
	public TaskExecutor getAsyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(20);
		executor.setMaxPoolSize(1000);
		executor.setWaitForTasksToCompleteOnShutdown(true);
		executor.setThreadNamePrefix("Async-");
		return executor;
	}
}
