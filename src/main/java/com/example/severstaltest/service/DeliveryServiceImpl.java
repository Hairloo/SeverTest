package com.example.severstaltest.service;

import com.example.severstaltest.dto.DeliveryDtoResponse;
import com.example.severstaltest.dto.ReportDtoResponse;
import com.example.severstaltest.mapper.DeliveryMapper;
import com.example.severstaltest.model.Delivery;
import com.example.severstaltest.model.DeliveryProduct;
import com.example.severstaltest.model.Product;
import com.example.severstaltest.model.Supplier;
import com.example.severstaltest.repository.DeliveryRepository;
import com.example.severstaltest.repository.ProductRepository;
import com.example.severstaltest.repository.SupplierRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeliveryServiceImpl implements DeliveryService{
    private final DeliveryRepository deliveryRepository;
    private final ProductRepository productRepository;
    private final SupplierRepository supplierRepository;
    @Override
    public Delivery save(Delivery delivery) {
        log.info("Конечная попытка сохранения поставки{}", delivery);
        return deliveryRepository.save(delivery);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Supplier getSupplierByName(String name) {
        return supplierRepository.findSupplierByName(name).orElseThrow(() -> new EntityNotFoundException("Поставщик не найден"));
    }

    @Override
    public Delivery getDeliveryByDate(LocalDate date) {
        return deliveryRepository.findDeliveriesByDate(date).orElseThrow(() -> new EntityNotFoundException("Поставка не найдена"));
    }

    @Override
    public List<Delivery> getDeliveriesBetweenDates(LocalDate firstDate, LocalDate secondDate) {
        Optional<List<Delivery>> deliveries = deliveryRepository.getAllDeliveriesBetweenDates(firstDate, secondDate);
        return deliveries.orElse(Collections.emptyList());
    }

    @Override
    public ReportDtoResponse getReportBetweenDates(LocalDate firstDate, LocalDate secondDate) {
        List<DeliveryDtoResponse> deliveryDtoResponseList = getDeliveriesBetweenDates(firstDate, secondDate).stream()
                .map((DeliveryMapper::mapDeliveryToDeliveryDtoResponse)).toList();
        double totalPrice = 0;
        int totalQuantity = 0;
        double totalWeight = 0;
        for (DeliveryDtoResponse deliveryDtoResponse : deliveryDtoResponseList){
            for (DeliveryProduct product :deliveryDtoResponse.getDeliveryProducts()){
                totalPrice += product.getPrice() * product.getQuantity();
                totalQuantity += product.getQuantity();
                totalWeight += product.getProduct().getWeight() * product.getQuantity();
            }
        }
        return ReportDtoResponse.builder()
                .deliveryDtoResponseList(deliveryDtoResponseList)
                .totalPrice(totalPrice)
                .totalQuantity(totalQuantity)
                .totalWeight(totalWeight)
                .build();
    }

    @Override
    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }
}
