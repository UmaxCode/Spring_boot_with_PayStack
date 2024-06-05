package com.umaxcode.spring_paystack.repositories;

import com.umaxcode.spring_paystack.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long>{
}
