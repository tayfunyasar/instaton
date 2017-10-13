package com.instaton.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.instaton.constant.CacheConstants;
import com.instaton.entity.generic.parameter.ParameterListInputData;

@Service
public class BatchService {

	private static final Log logger = LogFactory.getLog(BatchService.class);

	@Autowired
	private CacheManagementService cacheManagementService;

	private final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");

	@Autowired
	private GenericService genericService;

	/**
	 * This method executing generic services for caching
	 * at 1:00, 9:00, 12:00, 18:00 every day.
	 *
	 * @throws InterruptedException
	 */
	@Scheduled(cron = "0 0 1,9,12,18 * * ?")
	private void executeBatchCaching() throws InterruptedException {
		logger.debug("GenericService::executeBatchCaching started => time is now " + dateFormat.format(new Date()));

		cacheManagementService.clearCacheByName(CacheConstants.PARAMETER_LIST);
		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(16, 16, 5, TimeUnit.MINUTES, new LinkedBlockingQueue<Runnable>());
		threadPoolExecutor.submit(() -> {
			try {
				ParameterListInputData inputData = new ParameterListInputData();

				inputData.setParameterType(0);

				genericService.getParameterList(inputData);
			}
			catch (Exception e) {
				logger.error("BatchService::executeBatchCaching groupNameText: 01", e);
			}
		});
		threadPoolExecutor.shutdown();
		threadPoolExecutor.awaitTermination(5, TimeUnit.MINUTES);
	}

	/**
	 * This method triggered after spring boot application start.
	 * For initial batch caching.
	 *
	 * @throws InterruptedException
	 */
	@EventListener(ContextRefreshedEvent.class)
	private void onApplicationStartBatch() throws InterruptedException {
		logger.debug("BatchService::onApplicationStartBatch started");
		executeBatchCaching();
		logger.debug("BatchService::onApplicationStartBatch ended");
	}
}
