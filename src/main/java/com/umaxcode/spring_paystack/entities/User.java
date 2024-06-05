package com.umaxcode.spring_paystack.entities;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "AppUser")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String username;

    private String email;

    @OneToMany
    @JsonManagedReference
    private List<Payment> payments;

}
