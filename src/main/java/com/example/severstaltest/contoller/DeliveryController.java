package com.example.severstaltest.contoller;

import com.example.severstaltest.dto.GenerateReportDtoRequest;
import com.example.severstaltest.dto.DeliveryDtoRequest;
import com.example.severstaltest.dto.ReportDtoResponse;
import com.example.severstaltest.mapper.DeliveryMapper;
import com.example.severstaltest.model.Delivery;
import com.example.severstaltest.model.DeliveryProduct;
import com.example.severstaltest.model.Product;
import com.example.severstaltest.model.Supplier;
import com.example.severstaltest.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class DeliveryController {
    private final DeliveryService deliveryService;

    @GetMapping("/home")
    public String getHome(){
        return "home";
    }
    @GetMapping("/delivery/create")
    public String getCreateDelivery(Model model){
        List<Product> products = deliveryService.getAllProducts();
        List<Supplier> suppliers = deliveryService.getAllSuppliers();
        DeliveryDtoRequest deliveryDtoRequest = new DeliveryDtoRequest();
        List<DeliveryProduct> deliveryProducts = new ArrayList<>();
        deliveryProducts.add(new DeliveryProduct());
        deliveryDtoRequest.setDeliveryProducts(deliveryProducts);
        model.addAttribute("deliveryDtoRequest", deliveryDtoRequest);
        model.addAttribute("products", products);
        model.addAttribute("suppliers", suppliers);
        log.info("Заход на страницу поставки");
        return "create-delivery";
    }

    @PostMapping("/delivery/create")
    public String postCreateDelivery(@ModelAttribute("deliveryDtoRequest") DeliveryDtoRequest deliveryDtoRequest,
                                     Model model){
        model.addAttribute("deliveryDtoRequest", deliveryDtoRequest);
        model.addAttribute("products", deliveryService.getAllProducts());
        log.info("DtoRequest: {}", deliveryDtoRequest);
        Delivery saveDelivery = deliveryService.save(DeliveryMapper.mapDeliveryDtoRequestToDelivery(deliveryDtoRequest, deliveryService));
        log.info("Сохранена поставка={}", saveDelivery);
        return "redirect:/home";
    }

    @GetMapping("/report/generate")
    public String getGenerateReport(){
        return "generate-report";
    }

    @PostMapping("/report/generate")
    public String postGenerateReport(@ModelAttribute("dateDto") GenerateReportDtoRequest generateReportDtoRequest, Model model){
        log.info(generateReportDtoRequest.getStartDate().toString());
        log.info(generateReportDtoRequest.getEndDate().toString());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        //String formattedString = localDate.format(formatter)
        return "redirect:/report/get?startDateString=" + generateReportDtoRequest.getStartDate().format(formatter) + "&endDateString="
                + generateReportDtoRequest.getEndDate().format(formatter);
    }



    @GetMapping("/report/get")
    public String getReport(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String startDateString, @RequestParam
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String endDateString, Model model){
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate firstDate = LocalDate.parse(startDateString, pattern);
        LocalDate secondDate = LocalDate.parse(endDateString, pattern);
        ReportDtoResponse reportDtoResponse = deliveryService.getReportBetweenDates(firstDate, secondDate);
        List<Delivery> deliveries = deliveryService.getDeliveriesBetweenDates(firstDate, secondDate);
        log.info("Было найдено " + deliveries.size() + " поставок в период c " + startDateString + " по " +
                endDateString + "{}", deliveries);
        log.info(deliveries.get(0).getDeliveryProducts().toString());
        model.addAttribute("report", reportDtoResponse);
        return "report";
    }


}
