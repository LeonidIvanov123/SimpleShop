package ru.leonid.Orderservice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import ru.leonid.Orderservice.Service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Value("${eureka.instance.instance-id}")
    private String instanceAplication;
    @Autowired
    OrderService orderService;

    @GetMapping("/test")
    public String testMethod(){
        return "test service #Ordersrevice# " + instanceAplication;
    }

    @GetMapping("/create")
    public String createOrder(List<Long> products, Long user_id){
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
