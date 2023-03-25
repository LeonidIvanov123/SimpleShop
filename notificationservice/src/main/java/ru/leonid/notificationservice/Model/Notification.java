package ru.leonid.notificationservice.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/***
 * pull-notification.
 * хранятся в бд сервиса. Юзер сам запрашивает
 */
@Entity
@Table(name = "notifications")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long notification_id;
    private long user_id;
    private String header;
    private Date date;
    private String content;
    public Notification(Long user_id, String header, String content) {
        this.user_id = user_id;
        this.header = header;
        this.content = content;
    }
}
