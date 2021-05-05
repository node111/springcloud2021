package com.gwm.springcloud.dao;

import com.gwm.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface PaymentDao {
    //插入操作
    public int create(Payment payment);

    //查询操作
    public Payment getPaymentById(@Param("id") Long id);
}
