package com.example.severstaltest.dto;

import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class GenerateReportDtoRequest {
    private LocalDate startDate;
    private LocalDate endDate;
}
