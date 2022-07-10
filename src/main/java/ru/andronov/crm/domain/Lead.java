package ru.andronov.crm.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Data
@Entity
@Table(name = "leads")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Lead {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lead_id")
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone")
    private String phone;

    @Column(name = "address")
    private String address;

    @Column(name = "email")
    private String email;

    @Column(name = "storage_unit")
    private String storage;

    @Column(name = "trade_price")
    private double tradePrice;

    @Column(name = "discount")
    private int discount;

    @ManyToOne(targetEntity = Status.class,
               fetch = FetchType.EAGER)
    @JoinColumn(name = "status_id")
    private Status status;

    @Column(name = "creation_date")
    private Date creationDate;

    @Column(name = "completed_date")
    private Date completedDate;

    @Column(name = "comment")
    private String comment;

    @Column(name = "storage_unit_address")
    private String storageUnitAddress;

    @Column(name = "prepay")
    private double prepay;

    @Column(name = "prepay_type")
    private String prepayType;

    @Column(name = "surcharge")
    private double surcharge;

    @Column(name = "surcharge_type")
    private String surchargeType;

    @OneToMany(targetEntity = Product.class,
            fetch = FetchType.EAGER)
    @JoinColumn(name = "lead_id")
    private List<Product> products;

    public int creationMonth() {
        return this.creationDate.toLocalDate().getMonth().getValue();
    }

    public int creationYear() {
        return this.creationDate.toLocalDate().getYear();
    }

    public double totalCostWithDiscount() {
        var totalCost = products
                .stream()
                .map(p -> p.getCount() * p.getPrice())
                .reduce(0.0, Double::sum);
        var totalCostWithDiscount = totalCost * (1 - discount / 100.0);
        return Math.round(totalCostWithDiscount * 100) / 100.0;
    }
}
