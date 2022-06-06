package com.nazar.backendspringboot.dto;

import lombok.Data;

import java.util.List;

@Data
public class PageableResponse<T> {
    private List<T> items;
    private long totalItems;
    private long totalPages;
}
