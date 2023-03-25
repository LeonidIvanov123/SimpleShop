package ru.leonid.Orderservice.Model;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
    @Query(value = "SELECT * FROM ORDERS ord WHERE ord.user_id =:user_id", nativeQuery = true)
    List<Order> findByUserId(@Param("user_id")long user_id);
}
