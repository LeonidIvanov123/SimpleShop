package ru.leonid.productservice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.leonid.productservice.Model.Product;
import ru.leonid.productservice.Model.ProductRepository;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @GetMapping("test")
    public String testController(){
        return "test controller productService";
    }

    @GetMapping("/add")
    public Product addProduct(){ //@PathVariable Product product){
        Product product = new Product("Картоха", "Еда картошка", 1, 500);
        productRepository.save(product);
        return product;
    }
    @GetMapping("/id/{productId}")
    public String getProduct(@PathVariable(name = "productId") Long id){
        Product product = productRepository.getReferenceById(id);
        return product.toString();
    }

}
