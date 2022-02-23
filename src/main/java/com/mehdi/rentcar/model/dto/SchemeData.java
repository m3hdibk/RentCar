package com.mehdi.rentcar.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SchemeData implements Serializable {


    private static final long serialVersionUID = 2650915316276742668L;
    private long id;
    private int type;
    private String name;
    private String description;
    private String value;
    private boolean status;
    private boolean defaultItem;
}
