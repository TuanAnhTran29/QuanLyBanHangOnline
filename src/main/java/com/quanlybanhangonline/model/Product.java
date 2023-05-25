package com.quanlybanhangonline.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Size(min = 3, max = 50)
    private String name;
    @NotNull
    @DecimalMin("0.0")
    private double price;
    @NotBlank
    private String picture;
    @NotBlank
    @Size(min = 5, max = 250)
    private String description;
    @NotNull
    @Max(value = 1000)
    private int inventory;
    @NotBlank
    private boolean status;
    @ManyToMany(fetch = FetchType.EAGER, targetEntity = Category.class)
    @JoinTable(name = "product_category", joinColumns = @JoinColumn(name = "productId"), inverseJoinColumns = @JoinColumn(name = "categoryId") )
    Set<Category> categories= new HashSet<>();


    public Product(@NotBlank
                   @Size(min = 3, max = 50) String name,
                   @NotNull
                   @DecimalMin("0.0")  double price,
                   @NotBlank
                   String picture,
                   @NotBlank
                   @Size(min = 5, max = 250)
                   String description,
                   @NotNull
                   @Max(value = 1000)
                   int inventory,
                   @NotBlank
                   boolean status,
                   Set<Category> categories) {
        this.name = name;
        this.price = price;
        this.picture = picture;
        this.description = description;
        this.inventory = inventory;
        this.status = status;
        this.categories = categories;
    }
}
