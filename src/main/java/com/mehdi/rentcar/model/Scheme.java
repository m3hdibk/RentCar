package com.mehdi.rentcar.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity(name = "schemes")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Scheme implements Serializable {


    private static final long serialVersionUID = -3408636037702722863L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int type;
    private String name;
    private String description;
    private String value;
    private boolean status;
    private boolean defaultItem;
}
