package com.mehdi.rentcar.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PageResponse<T> implements Serializable {


    private static final long serialVersionUID = 7161934598739148744L;

    private List<T> content;
    private int currentPage;
    private long totalItems;
    private int totalPages;

}

