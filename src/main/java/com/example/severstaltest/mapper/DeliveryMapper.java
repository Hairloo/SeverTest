package com.example.severstaltest.mapper;

import com.example.severstaltest.dto.DeliveryDtoRequest;
import com.example.severstaltest.dto.DeliveryDtoResponse;
import com.example.severstaltest.model.Delivery;
import com.example.severstaltest.model.DeliveryProduct;
import com.example.severstaltest.model.Product;
import com.example.severstaltest.service.DeliveryService;
import java.util.ArrayList;
import java.util.List;

public class DeliveryMapper {
    public static Delivery mapDeliveryDtoRequestToDelivery(DeliveryDtoRequest deliveryDtoRequest, DeliveryService deliveryService){
        List<DeliveryProduct> deliveryProducts = new ArrayList<>();
        List<Product> products = deliveryService.getAllProducts();
        List<DeliveryProduct> deliveryProductsDtoRequest = deliveryDtoRequest.getDeliveryProducts();
        for (int i = 0; i < deliveryProductsDtoRequest.size();i++){
            if(deliveryProductsDtoRequest.get(i).getQuantity() != 0){
                deliveryProductsDtoRequest.get(i).setProduct(products.get(i));
                deliveryProducts.add(deliveryProductsDtoRequest.get(i));
            }
        }
        if (deliveryProducts.isEmpty()){
            return null;
        }
        Delivery delivery = Delivery.builder()
                .date(deliveryDtoRequest.getDate())
                .supplier(deliveryService.getSupplierByName(deliveryDtoRequest.getSupplierName()))
                .deliveryProducts(deliveryProducts)
                .build();
        return delivery;
    }

    public static DeliveryDtoResponse mapDeliveryToDeliveryDtoResponse(Delivery delivery){
        return DeliveryDtoResponse.builder()
                .id(delivery.getId())
                .deliveryProducts(delivery.getDeliveryProducts())
                .date(delivery.getDate())
                .supplier(delivery.getSupplier())
                .build();
    }
}
