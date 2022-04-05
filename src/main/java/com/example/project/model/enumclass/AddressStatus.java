package com.example.project.model.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AddressStatus {
    NORMAL(0, "일반배송지", "일반상태"),
    PRIME(1, "기본배송지", "기본배송지상태");

    private Integer id;
    private String title;
    private String description;
}
