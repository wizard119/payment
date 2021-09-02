package com.example.payment;

import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface PaymentRepository extends CrudRepository<Payment, Long> {

    List<Payment> findByOrderId(Long orderId);
    
}