package com.test.productmanager.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString(exclude = "products")
@EqualsAndHashCode(exclude = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProductId")
    private Long productId;

    @Column(name = "ProductUUID")
    private String productUUID;

    @Column(name = "ProductName")
    private String productName;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "CategoryId")
    private Category category;

    @Column(name = "ProductDescription")
    private String productDescription;

    @Column(name = "BasePrice")
    private BigDecimal basePrice;

    @Column(name = "ImageUrl")
    private String imageUrl;

    @Column(name = "AddedOn")
    private LocalDateTime addedOn;
}
