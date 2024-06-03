package com.example.severstaltest.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;


@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "deliveries")
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    private Supplier supplier;

    @OneToMany(cascade = CascadeType.ALL)
    private List<DeliveryProduct> deliveryProducts;

    private LocalDate date;
}
