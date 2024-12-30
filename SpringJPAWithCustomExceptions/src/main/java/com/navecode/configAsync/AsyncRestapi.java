package com.navecode.configAsync;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class AsyncRestapi // implements AsyncConfigurer
{

//	@Bean(name = "shashiTask")
//	Executor taskExecutr() {
//		ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(2, 4, 1, TimeUnit.HOURS, new ArrayBlockingQueue<>(3),
//				new customThreadFactory());
//		return poolExecutor;
//	}
//
//	class customThreadFactory implements ThreadFactory {
//		private final AtomicInteger threadNo = new AtomicInteger(1);
//
//		@Override
//		public Thread newThread(Runnable r) {
//			Thread thread = new Thread(r);
//			thread.setName("My thread - " + threadNo.getAndIncrement());
//			return thread;
//		}
//	}

//	@Bean(name = "simpl")
//	public SimpleAsyncTaskExecutor simpl() {
//		return new SimpleAsyncTaskExecutor();
//	}

//	@Autowired
//	public AsyncUncaughtExceptionHandler asyncuncaughtexceptionhandler;
//
//	public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHanlder() {
//
//		return this.asyncuncaughtexceptionhandler;
//	}

//	@Bean(name = "taskExecutor")
//	@Override
//	public synchronized Executor getAsyncExecutor() {
//		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
//		executor.setCorePoolSize(4);
//		executor.setMaxPoolSize(5);
//		executor.setQueueCapacity(150);
//		executor.setThreadNamePrefix("user thread : ");
//		executor.initialize();
//		return executor;
//	}

	@Bean(name = "taskExecutor")
	Executor taskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(2);
		executor.setMaxPoolSize(2);
		executor.setQueueCapacity(100);
		executor.setThreadNamePrefix("userThread-");
		executor.initialize();
		return executor;
	}
}

//@Component
//class DefaultAsyncUncaughtExceptionhandler implements AsyncUncaughtExceptionHandler {
//	@Override
//	public void handleUncaughtException(Throwable th, Method meth, Object... params) {
//		System.out.println("in default exception method");
//	}
//}