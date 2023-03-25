package ru.leonid.userservice.Service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.leonid.userservice.Model.User;
import ru.leonid.userservice.Model.UserRepository;
@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User createUser(String username, String email, String password){
        User user = new User(username, email, password);
        userRepository.save(user);
        return user;
    }

    @Transactional
    public String upBalance(long user_id, long balance){
        User user = userRepository.getReferenceById(user_id);
        try{
            user.setBalance(user.getBalance() + balance);
            userRepository.save(user);
        }catch (EntityNotFoundException ex){
            return "Error: Не найден user с id= "+ user_id;
        }
        return "Sucsess: баланс пополнен. Текущий = "+ user.getBalance();
    }

    public String userById(long user_id){
        User user = userRepository.getReferenceById(user_id);
        try {
            return user.toString();
        }catch (EntityNotFoundException ex){
            return "Error: Не найден user c id: "+ user_id;
        }
    }
    public String allUsers(){
        try {
            return userRepository.findAll().toString();
        }catch (EntityNotFoundException ex){
            return "Error: Нет пользователей";
        }
    }
}
