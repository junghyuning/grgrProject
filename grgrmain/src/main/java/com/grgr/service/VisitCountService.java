package com.grgr.service;


import com.grgr.dto.VisitCountVO;

public interface VisitCountService {

    void insertVisitCount();

    void updateVisitCount();

    VisitCountVO selectVisitCount();
}