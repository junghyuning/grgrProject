package com.grgr.service;


import org.springframework.stereotype.Service;

import com.grgr.dao.VisitCountDAO;
import com.grgr.dto.VisitCountVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VisitCountServiceImpl implements VisitCountService {

   
    private final VisitCountDAO visitCountDAO;

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
