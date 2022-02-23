package com.mehdi.rentcar.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerData implements Serializable {


    private static final long serialVersionUID = -6184515725392149495L;

    private long id;
    private String fullName;
    private String phone;
    private String address;
    private String email;
    private String idNumber;
    private String note;

}
