package com.gwm.springcloud.controller;

import com.gwm.springcloud.entities.CommonResult;
import com.gwm.springcloud.entities.Payment;
import com.gwm.springcloud.service.PaymenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymenService paymenService;

    @Value("${server.port}")
    private String severPort;

    @PostMapping(value="/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int result = paymenService.create(payment);
        log.info("***插入结果："+result);
        if(result>0){
            return new CommonResult(200,"插入成功"+severPort,result);
        }else {
            return new CommonResult(444,"失败",null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
         Payment payment = paymenService.getPaymentById(id);
         if(payment !=null){
             return new CommonResult(200,"sucesses!"+severPort,payment);
         }else {
             return new CommonResult(444,"faild!",null);
         }
    }
}
