package com.example.severstaltest.dto;

import com.example.severstaltest.model.DeliveryProduct;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryDtoRequest {
    List<DeliveryProduct> deliveryProducts;
    private String supplierName;
    private LocalDate date;
}
