package com.grgr.mapper;

import com.grgr.dto.VisitCountVO;

public interface VisitCountMapper {
    void insertVisitCount();
    void updateVisitCount();
    VisitCountVO selectVisitCount();
}
