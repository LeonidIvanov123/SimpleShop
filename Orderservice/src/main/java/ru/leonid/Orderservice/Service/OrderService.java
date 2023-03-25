package ru.leonid.Orderservice.Service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.leonid.Orderservice.Model.Order;
import ru.leonid.Orderservice.Model.OrderRepository;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    public String createNewOrder(List<Long> products, Long user_id){
        Order order = new Order();
        order.setUser_id(user_id);
        order.setProducts(products);
        orderRepository.save(order);
        return "Sucsess: Создан заказ "+ order;
    }
    public String getOrdersByUser(Long user_id){
        try{
            return orderRepository.findByUserId(user_id).toString();
        }catch (EntityNotFoundException e){
            return "Error: Заказов не найдено";
        }
    }
    public String deleteOrder(Long order_id){
        try {
            orderRepository.deleteById(order_id);
            return "Sucsess: Заказ удален";
        }catch (EntityNotFoundException ex){
            return "Error: Не найден заказ id= "+ order_id;
        }
    }
}
