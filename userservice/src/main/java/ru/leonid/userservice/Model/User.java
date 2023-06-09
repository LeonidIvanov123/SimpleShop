package ru.leonid.userservice.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long user_id;
    @NonNull
    private String username;
    @NotNull
    private String email;
    private long balance;
    private String passwordhash;
    private final boolean isAdmin = false;

    public User(@NonNull String username, String email, String passwordhash) {
        this.username = username;
        this.email = email;
        this.passwordhash = passwordhash;
    }


    //Должен ли юзер хранить у себя список заказов? Скорее нет. Их можно запросить у order service по user_id
}
