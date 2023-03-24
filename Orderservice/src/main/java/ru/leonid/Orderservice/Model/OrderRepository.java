package ru.leonid.Orderservice.Model;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
    @Query(value = "SELECT ord FROM ORDER ord WHERE ord.user_id = ?1", nativeQuery = true)
    List<Order> findByUserId(long user_id);
}
