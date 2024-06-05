package com.umaxcode.spring_paystack.repositories;

import com.umaxcode.spring_paystack.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
