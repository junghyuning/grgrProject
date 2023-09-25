package com.grgr.service;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.grgr.dao.VisitCountDAO;
import com.grgr.dto.VisitCountVO;

import lombok.RequiredArgsConstructor;

@Service("visitCountService")
public class VisitCountServiceImpl implements VisitCountService {

   
	@Resource(name = "visitCountDAO")
    private VisitCountDAO visitCountDAO;

    @Override
    public void insertVisitCount() {
        visitCountDAO.insertVisitCount();
    }

    @Override
    public void updateVisitCount() {
        visitCountDAO.updateVisitCount();
    }

    @Override
    public VisitCountVO selectVisitCount() {
        return visitCountDAO.selectVisitCount();
    }
}
