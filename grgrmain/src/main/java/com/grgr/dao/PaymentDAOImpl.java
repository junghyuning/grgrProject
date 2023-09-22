package com.grgr.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.grgr.dto.Payment;
import com.grgr.mapper.PaymentMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class PaymentDAOImpl implements PaymentDAO {
	private final SqlSession session;

	@Override
	public void insertPayment(Payment payment) {
		session.getMapper(PaymentMapper.class).insertPayment(payment);
	}

	@Override
	public void updatePayment(Payment payment) {
		session.getMapper(PaymentMapper.class).updatePayment(payment);
		
	}

	@Override
	public Payment getPayment(String impUid) {
		return session.getMapper(PaymentMapper.class).getPayment(impUid);
	}

	@Override
	public String cancelPayment(Payment payment) {
		return session.getMapper(PaymentMapper.class).cancelPayment(payment);
	}


}
