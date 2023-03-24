package ru.leonid.Orderservice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.leonid.Orderservice.Model.Order;
import ru.leonid.Orderservice.Model.OrderRepository;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderRepository orderRepository;

    @GetMapping("/test")
    public String testMethod(){
        return "test service #Ordersrevice#";
    }

    @GetMapping("/create")
    public Long createOrder(List<Long> products, Long user_id){ //возврат id-заказа
        Order order = new Order();
        order.setUser_id(user_id);
        order.setProducts(products);
        orderRepository.save(order);
        return order.getOrder_id();
    }

    @GetMapping("/user_id/{user_id}")
    public String getOrdersByUser(@PathVariable(name = "user_id")Long user_id){
        List<Order> orders = orderRepository.findByUserId(user_id);
        return orders.stream().toString(); //добавить проверку на null
    }
}
