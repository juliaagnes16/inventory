package com.example.demo.entity;

import com.vladmihalcea.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import java.time.LocalDateTime;
import java.util.Map;

@Entity
@Table(name = "stocks")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nama_barang", nullable = false)
    private String namaBarang;

    @Column(name = "jumlah_stok")
    private int jumlahStok;

    @Column(name = "nomor_seri")
    private String nomorSeri;

    @Type(JsonType.class)
    @Column(name = "additional_info", columnDefinition = "jsonB")
    private Map<String, Object> additionalInfo;

    @Column(name = "gambar_path")
    private String gambarPath;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "updated_by")
    private String updatedBy;

}
