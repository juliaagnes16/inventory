package com.example.demo.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class StockRequestDTO {
    private String namaBarang;
    private int jumlahStok;
    private String nomorSeri;
    private String additionalInfo;
    private MultipartFile gambar;
    private String createdBy;
}
