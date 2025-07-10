package com.example.demo.service.impl;

import com.example.demo.dto.StockRequestDTO;
import com.example.demo.dto.StockResponseDTO;
import com.example.demo.entity.Stock;
import com.example.demo.repository.StockRepository;
import com.example.demo.service.StockService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class StockServiceImpl implements StockService {
    private final StockRepository repository;

    @Override
    public StockResponseDTO createStock(StockRequestDTO request) throws IOException {
        validateImage(request.getGambar());

        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> additional = mapper.readValue(request.getAdditionalInfo(), new TypeReference<>() {});

        String filePath = saveImage(request.getGambar(), request.getNamaBarang());

        Stock stock = Stock.builder()
                .namaBarang(request.getNamaBarang())
                .jumlahStok(request.getJumlahStok())
                .nomorSeri(request.getNomorSeri())
                .additionalInfo(additional)
                .gambarPath(filePath)
                .createdAt(LocalDateTime.now())
                .createdBy(request.getCreatedBy())
                .build();

        Stock saved = repository.save(stock);
        return mapToDto(saved);
    }

    @Override
    public List<StockResponseDTO> listStock() {
        return repository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public StockResponseDTO getDetail(Long id) {
        Stock stock = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Stock not found"));
        return mapToDto(stock);
    }

    @Override
    public StockResponseDTO updateStock(Long id, StockRequestDTO request) throws IOException {
        Stock stock = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Stock not found"));
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> additional = mapper.readValue(request.getAdditionalInfo(), new TypeReference<>() {});

        if (request.getGambar() != null) {
            validateImage(request.getGambar());
            stock.setGambarPath(saveImage(request.getGambar(), request.getNamaBarang()));
        }

        stock.setNamaBarang(request.getNamaBarang());
        stock.setJumlahStok(request.getJumlahStok());
        stock.setNomorSeri(request.getNomorSeri());
        stock.setAdditionalInfo(additional);
        stock.setUpdatedAt(LocalDateTime.now());
        stock.setUpdatedBy(request.getCreatedBy());

        return mapToDto(repository.save(stock));
    }

    @Override
    public void deleteStock(Long id) {
        repository.deleteById(id);
    }

    private void validateImage(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("File gambar wajib diisi.");
        }

        String type = file.getContentType();
        if (!Objects.equals(type, "image/jpeg") && !Objects.equals(type, "image/png")) {
            throw new IllegalArgumentException("Only JPG or PNG allowed");
        }
    }

    private String saveImage(MultipartFile file, String namaBarang) throws IOException {
        String dir = "uploads/";
        Files.createDirectories(Paths.get(dir));
        String barang = namaBarang.replaceAll("[^a-zA-Z0-9]", "-");
        String fileName = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")) + "_" + barang;
        Path path = Paths.get(dir + fileName);
        Files.write(path, file.getBytes());
        return path.toString();
    }

    private StockResponseDTO mapToDto(Stock s) {
        return StockResponseDTO.builder()
                .id(s.getId())
                .namaBarang(s.getNamaBarang())
                .jumlahStok(s.getJumlahStok())
                .nomorSeri(s.getNomorSeri())
                .additionalInfo(s.getAdditionalInfo())
                .gambarPath(s.getGambarPath())
                .createdAt(s.getCreatedAt())
                .createdBy(s.getCreatedBy())
                .updatedAt(s.getUpdatedAt())
                .updatedBy(s.getUpdatedBy())
                .build();
    }
}
