package com.example.severstaltest.service;

import com.example.severstaltest.dto.ReportDtoResponse;
import com.example.severstaltest.model.Delivery;
import com.example.severstaltest.model.Product;
import com.example.severstaltest.model.Supplier;

import java.time.LocalDate;
import java.util.List;


public interface DeliveryService {
    Delivery save(Delivery delivery);
    List<Product> getAllProducts();
    Supplier getSupplierByName(String name);

    Delivery getDeliveryByDate(LocalDate date);

    List<Delivery> getDeliveriesBetweenDates(LocalDate firstDate, LocalDate secondDate);

    ReportDtoResponse getReportBetweenDates(LocalDate firstDate, LocalDate secondDate);

    List<Supplier> getAllSuppliers();
}
