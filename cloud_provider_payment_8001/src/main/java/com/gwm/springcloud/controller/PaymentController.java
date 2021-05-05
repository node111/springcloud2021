package com.gwm.springcloud.controller;

import com.gwm.springcloud.entities.CommonResult;
import com.gwm.springcloud.entities.Payment;
import com.gwm.springcloud.service.PaymenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymenService paymenService;

    @PostMapping(value="/payment/create")
    public CommonResult create(Payment payment){
        int result = paymenService.create(payment);
        log.info("***插入结果："+result);
        if(result>0){
            return new CommonResult(200,"插入成功",result);
        }else {
            return new CommonResult(444,"失败",null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
         Payment payment = paymenService.getPaymentById(id);
         if(payment !=null){
             return new CommonResult(200,"sucess!",payment);
         }else {
             return new CommonResult(444,"faild!",null);
         }
    }
}
