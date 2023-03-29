package ru.leonid.productservice.Controller;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.leonid.productservice.Model.Product;
import ru.leonid.productservice.Model.ProductRepository;
import ru.leonid.productservice.Service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Value("${eureka.instance.instance-id}")
    private String instanceAplication;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductService productService;

    //////
    @GetMapping("test")
    public String testController(){
        productRepository.save(new Product("Snickers", "chocolate", 1, 55));
        productRepository.save(new Product("Ball", "small ball", 2, 550));
        productRepository.save(new Product("Snack", "meat snack", 1, 700));
        productRepository.save(new Product("Card", "game card", 3, 600));
        return "test controller #productService#: "+ instanceAplication;
    }
    /////

    @PostMapping("/add")
    public ResponseEntity<Product> addProduct(){ //@PathVariable Product product){ //нужна форма для заполнения для валидации полей
        Product product = new Product("Картоха", "Еда картошка", 1, 500);
        productService.addNewProduct(product);
        return ResponseEntity.ok(product);
    }
    @GetMapping("/id/{productId}")
    public String getProduct(@PathVariable(name = "productId") Long product_id, @RequestParam(required = false) int amount){
        if(amount > 0){
           return productService.soldProduct(product_id, amount);
        }
        return productService.getProduct(product_id); //only return info about product (when ?amount=0)
    }



}
