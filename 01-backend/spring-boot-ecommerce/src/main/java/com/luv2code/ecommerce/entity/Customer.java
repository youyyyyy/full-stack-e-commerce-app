package com.luv2code.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.aspectj.weaver.ast.Or;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "customer")
@Setter
@Getter
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private Set<Order> orders = new HashSet<>();

    public void add(Order order) {
        if (order != null) {
            if (orders == null) {
                orders = new HashSet<>();
            }
            orders.add(order);
            order.setCustomer(this);
        }
    }
}
