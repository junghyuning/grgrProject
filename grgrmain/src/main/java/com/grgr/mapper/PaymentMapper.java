package com.grgr.mapper;

import com.grgr.dto.Payment;

public interface PaymentMapper {
	void insertPayment(Payment payment);
    void updatePayment(Payment payment);
    Payment getPayment(String impUid);
    String cancelPayment(Payment payment);
}
