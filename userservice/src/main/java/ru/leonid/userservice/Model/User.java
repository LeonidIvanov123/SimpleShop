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
    private String password;
    private final boolean isAdmin = false;

}
