package com.mscSaveFIle.mscSaveFIle.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private String phoneNumber;
    private String country;
    private String customerName;
}