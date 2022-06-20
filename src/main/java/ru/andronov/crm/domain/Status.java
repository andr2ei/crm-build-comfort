package ru.andronov.crm.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "statuses")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "status_id")
    private int id;

    @Column(name = "status_name")
    private String name;

    public Status(String name) {
        this.name = name;
    }
}
