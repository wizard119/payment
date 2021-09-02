package com.example.payment;

import com.example.payment.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PolicyHandler {
    @Autowired PaymentRepository paymentRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverOrderPlaced_Payment(@Payload OrderPlaced orderPlaced){

        if(!orderPlaced.validate()) return;

        System.out.println("\n\n##### listener payment : " + orderPlaced.toJson() + "\n\n");

        // Sample Logic //
        Payment payment = new Payment();
        payment.setOrderId(orderPlaced.getId());
        payment.setProductId(orderPlaced.getProductId());
        payment.setStatus("payment");
        payment.setAmt(orderPlaced.getAmt());
        paymentRepository.save(payment);            
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverOrderCanceled_CancelPayment(@Payload OrderCanceled orderCanceled){

        if(!orderCanceled.validate()) return;

        System.out.println("\n\n##### listener CancelPayment : " + orderCanceled.toJson() + "\n\n");

        // Sample Logic //
        List<Payment> paymentList = paymentRepository.findByOrderId(orderCanceled.getId());
        if ((paymentList != null) && !paymentList.isEmpty()){
            paymentRepository.deleteAll(paymentList);
        }         
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){}
}
