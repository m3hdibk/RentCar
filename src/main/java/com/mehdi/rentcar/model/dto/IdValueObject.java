package com.mehdi.rentcar.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class IdValueObject implements Serializable {

    private static final long serialVersionUID = 684194365238388591L;

    private long id;
    private String value;
}
