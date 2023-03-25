package ru.leonid.notificationservice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.leonid.notificationservice.Service.NotificationService;

@RestController
@RequestMapping("/notificate")
public class NotificationController {

    @Value("${eureka.instance.instance-id}")
    private String instanceAplication;

    @Autowired
    NotificationService notificationService;

    @GetMapping("/test")
    public String testMethod(){
        return "test service #Notification# " + instanceAplication;
    }

    @GetMapping("/send/{user_id}/{header}/{content}")
    public String sendNotificatToUser(@PathVariable("user_id") long user_id,
                                      @PathVariable("header") String header,
                                      @PathVariable("content") String content){
        return notificationService.sendNotification(user_id,header,content);
    }

    @GetMapping("/get/{user_id}")
    public String getNotification(@PathVariable("user_id") long user_id){
        return notificationService.getNotificationByUserId(user_id);
    }

}
