package ru.leonid.productservice.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.util.List;
@Entity
@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long productId;
    @NotNull
    private String productName;
    @NotNull
    private String description;
    @NotNull
    private long organizationId; //запрос к сервису organizationservice
    private long price;
    @NotNull
    private int amount; //кол-во
    private String discount; //скидочки
    private List<Review> reviews; //встраиваемый обзор и оценка
    private String keywords; //через запятую
    private String characteristics; //создать тип Characteristics(Param--value)

    public Product(String productName, String description, long organizationId, int amount) {
        this.productName = productName;
        this.description = description;
        this.organizationId = organizationId;
        this.amount = amount;
    }
}
