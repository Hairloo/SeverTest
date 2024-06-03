package com.example.severstaltest.repository;

import com.example.severstaltest.model.DeliveryProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryProductRepository extends JpaRepository<DeliveryProduct, Long> {
}
