package com.gwm.springcloud.service;

import com.gwm.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

public interface PaymenService {
    //插入操作
    public int create(Payment payment);

    //查询操作
    public Payment getPaymentById(@Param("id") Long id);
}
