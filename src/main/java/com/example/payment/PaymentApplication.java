package com.example.payment;

import com.example.payment.config.kafka.KafkaProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableBinding(KafkaProcessor.class)
@EnableFeignClients
public class PaymentApplication {

	public static ApplicationContext applicationContext;

	public static void main(String[] args) {
		//SpringApplication.run(PaymentApplication.class, args);
		applicationContext = SpringApplication.run(PaymentApplication.class, args);
	}

}

