package com.grgr.dao;

import com.grgr.dto.Payment;

public interface PaymentDAO {
    void insertPayment(Payment payment);
    void updatePayment(Payment payment);
    Payment getPayment(String impUid);
    String cancelPayment(Payment payment);
}