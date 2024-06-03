package com.example.severstaltest.dto;

import com.example.severstaltest.model.DeliveryProduct;
import com.example.severstaltest.model.Supplier;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryDtoResponse {
    private Long id;

    private Supplier supplier;

    private List<DeliveryProduct> deliveryProducts;

    private LocalDate date;
}
