package ru.leonid.productservice.Service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.leonid.productservice.Model.Product;
import ru.leonid.productservice.Model.ProductRepository;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    @Transactional
    public String soldProduct(long product_id, int amount){
        Product product = productRepository.getReferenceById(product_id);
        if(product.getAmount() < amount)
            return "Error: Недостаточно количества товара. Имеется: " + product.getAmount();

        product.setAmount(product.getAmount() - amount);
        productRepository.save(product);
        return "Sucsess: Продан " + product_id + " количество = " + amount;
    }

    @Transactional
    public String addProduct(long product_id, int amount){
        Product product = productRepository.getReferenceById(product_id);
        product.setAmount(product.getAmount() + amount);
        productRepository.save(product);
        return "Sucsess: Добавлено "+ product_id + " количество = "+ amount;
    }

    public String addNewProduct(Product product){
        productRepository.save(product);
        return "Sucsess: Добавлен новый продукт";
    }

    public String getProduct(long product_id){
        try {
            return productRepository.getReferenceById(product_id).toString();
        }catch (EntityNotFoundException e){
            return "Product not found";
        }
    }


}
