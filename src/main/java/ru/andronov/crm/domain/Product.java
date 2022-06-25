package ru.andronov.crm.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "products")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    @Id
    private int id;

    @Column(name = "product_name")
    private String name;

    @Column(name = "price")
    private double price;

    @Column(name = "count")
    private int count;

    @Column(name = "product_comment")
    private String comment;

    @Column(name = "lead_id")
    private int leadId;

    @Column(name = "is_service")
    private boolean service;

}
