package com.mscSaveFIle.mscSaveFIle.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private String phoneNumber;
    private String country;
    private String customerName;
}