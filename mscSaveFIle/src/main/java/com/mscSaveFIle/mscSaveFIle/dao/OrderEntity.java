package com.mscSaveFIle.mscSaveFIle.dao;

import javax.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.Id;


@Entity
@Table(name = "orders")
@Data
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String customerName;


}
