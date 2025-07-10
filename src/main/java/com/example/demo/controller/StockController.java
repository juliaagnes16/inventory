package com.example.demo.controller;

import com.example.demo.dto.StockRequestDTO;
import com.example.demo.dto.StockResponseDTO;
import com.example.demo.service.StockService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/stocks")
@RequiredArgsConstructor
@Log4j2
public class StockController {
    private final StockService stockService;

    @PostMapping
    public ResponseEntity<StockResponseDTO> createStock(@ModelAttribute StockRequestDTO request) throws IOException {
        return ResponseEntity.ok(stockService.createStock(request));
    }

    @GetMapping
    public List<StockResponseDTO> listStock() {
        return stockService.listStock();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StockResponseDTO> detail(@PathVariable Long id) {
        return ResponseEntity.ok(stockService.getDetail(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StockResponseDTO> update(@PathVariable Long id, @ModelAttribute StockRequestDTO request) throws IOException {
        return ResponseEntity.ok(stockService.updateStock(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        stockService.deleteStock(id);
        return ResponseEntity.noContent().build();
    }
}
