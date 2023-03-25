package ru.leonid.notificationservice.Model;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends CrudRepository<Notification, Long> {
    @Query(value = "SELECT * FROM NOTIFICATIONS n WHERE n.user_id =:user_id", nativeQuery = true)
    List<Notification> findNotificationsByUserId(@Param("user_id")long user_id);
}
