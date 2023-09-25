package com.grgr.dao;

import com.grgr.dto.VisitCountVO;

import lombok.RequiredArgsConstructor;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository("visitCountDAO")
@RequiredArgsConstructor
public class VisitCountDAO {
    private final SqlSession sqlSession;

    

    public void insertVisitCount() {
        sqlSession.insert("com.grgr.mapper.VisitCountMapper.insertVisitCount");
    }

    public void updateVisitCount() {
        sqlSession.update("com.grgr.mapper.VisitCountMapper.updateVisitCount");
    }

    public VisitCountVO selectVisitCount() {
        return sqlSession.selectOne("com.grgr.mapper.VisitCountMapper.selectVisitCount");
    }
}
