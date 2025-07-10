package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@Builder
public class StockResponseDTO {
    private Long id;
    private String namaBarang;
    private int jumlahStok;
    private String nomorSeri;
    private Map<String, Object> additionalInfo;
    private String gambarPath;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;
}
