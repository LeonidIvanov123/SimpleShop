package ru.leonid.Orderservice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import ru.leonid.Orderservice.Service.OrderService;

import java.util.ArrayList;
import java.util.List;

/***
 * Если auth.enabled=true
 * то все запросы к сервису
 * обязаны содеражть заголовок Authorization, значение:
 * "Holder + токен, выданный userservice(конечная точка user/token/{login}/{pass})
 ***/

@RestController
@RequestMapping("/order")
public class OrderController {

    @Value("${eureka.instance.instance-id}")
    private String instanceAplication;
    @Autowired
    OrderService orderService;

    @GetMapping("/test")
    public String testMethod(){
        List<Long> prods = new ArrayList<>();
        prods.add(100L);
        prods.add(50L);
        prods.add(990L);
        orderService.createNewOrder(prods, 1L);
        return "test service #Ordersrevice# " + instanceAplication;
    }

    @PutMapping("/create/{products}/{user_id}")
    public String createOrder(@PathVariable(name = "products")List<Long> products,
                              @PathVariable(name = "user_id") Long user_id){
        return orderService.createNewOrder(products,user_id);
    }

    @GetMapping("/user_id/{user_id}")
    public String getOrdersByUser(@PathVariable(name = "user_id")Long user_id){
        return orderService.getOrdersByUser(user_id);
    }

    @DeleteMapping("/delete/{order_id}")
    public String deleteOrder(@PathVariable(name = "order_id")Long order_id){
        return orderService.deleteOrder(order_id);
    }
}
