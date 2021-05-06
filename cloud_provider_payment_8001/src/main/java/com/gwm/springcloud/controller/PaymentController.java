package com.gwm.springcloud.controller;

import com.gwm.springcloud.entities.CommonResult;
import com.gwm.springcloud.entities.Payment;
import com.gwm.springcloud.service.PaymenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymenService paymenService;

    @Value("${server.port}")
    private String severPort;

    @Resource
    private DiscoveryClient discoveryClient;

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

    @GetMapping(value = "/payment/discovery")
    public Object discovery(){
        final List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("****element:"+service);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info(instance.getInstanceId()+"\t"+instance.getHost()+"\t"+instance.getPort()+"\t"+instance.getUri());
        }
        return this.discoveryClient;
    }
}
