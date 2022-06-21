package ru.andronov.crm.domain;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@Table(name = "leads")
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

    @OneToOne(targetEntity = Status.class,
            fetch = FetchType.EAGER)
    @JoinColumn(name = "status_id")
    private Status status;

    @Column(name = "creation_date")
    private Date creationDate;

}
