package ru.leonid.notificationservice.Service;

import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.leonid.notificationservice.Model.Notification;
import ru.leonid.notificationservice.Model.NotificationRepository;

import java.util.Objects;

@Service
public class NotificationService {
    @Autowired
    NotificationRepository notificationRepository;

    public String sendNotification(long user_id, String header, String context){
        Notification notification = new Notification(user_id, header, context);
        notificationRepository.save(notification);
        return "Sucsess";
    }

    public String getNotificationByUserId(long user_id){
        return notificationRepository.findNotificationsByUserId(user_id).stream().map(Objects::toString)
                .reduce((t, u) -> t + "\n" + u).orElse("No notifications");
    }
}
