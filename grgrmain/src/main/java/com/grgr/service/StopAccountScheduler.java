package com.grgr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.grgr.dao.UserDAO;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class StopAccountScheduler {
	
	@Autowired
	private UserDAO userDAO;
	
	@Scheduled(cron ="0 0 4 * * *")
	@Transactional(rollbackFor = Exception.class)
	public void autoUserDisActive() {

		userDAO.updateUserDisactivate();

		
	}

}
