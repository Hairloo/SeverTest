package com.example.severstaltest.dto;

import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ReportDtoResponse {
    private int totalQuantity;
    private double totalWeight;

    private double totalPrice;

    private List<DeliveryDtoResponse> deliveryDtoResponseList;
}
