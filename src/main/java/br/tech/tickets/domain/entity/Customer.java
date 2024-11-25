package br.tech.tickets.domain.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;
    private String name;
    private String email;

    @OneToMany(mappedBy = "customer")
    private List<Sales> sales;
}
