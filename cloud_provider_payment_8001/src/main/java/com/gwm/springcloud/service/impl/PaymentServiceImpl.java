package com.gwm.springcloud.service.impl;

import com.gwm.springcloud.dao.PaymentDao;
import com.gwm.springcloud.entities.Payment;
import com.gwm.springcloud.service.PaymenService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaymentServiceImpl implements PaymenService {
    @Resource
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
