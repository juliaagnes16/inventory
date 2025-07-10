package com.example.demo.service;

import com.example.demo.dto.StockRequestDTO;
import com.example.demo.dto.StockResponseDTO;

import java.io.IOException;
import java.util.List;

public interface StockService {
    StockResponseDTO createStock(StockRequestDTO request) throws IOException;
    List<StockResponseDTO> listStock();
    StockResponseDTO getDetail(Long id);
    StockResponseDTO updateStock(Long id, StockRequestDTO request) throws IOException;
    void deleteStock(Long id);
}
