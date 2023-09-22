package com.grgr.service;

import com.grgr.dto.Payment;

public interface PaymentService {
	void addPayment(Payment payment);
	void modifyPayment(Payment payment);

	String getAccessToken(Payment payment);
	Payment getPayment(String accessToken, String impUid);
	String cancelPayment(String accessToken, Payment payment);
}
