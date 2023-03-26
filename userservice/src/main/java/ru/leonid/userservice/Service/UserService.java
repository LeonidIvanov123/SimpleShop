package ru.leonid.userservice.Service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.leonid.userservice.Model.User;
import ru.leonid.userservice.Model.UserRepository;

import javax.security.auth.login.LoginException;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    //registration
    public String createUser(String username, String email, String password){
        if(userRepository.findByUsername(username).isPresent())
            return "this username already use!";
        String passwordhash = BCrypt.hashpw(password, BCrypt.gensalt());
        User user = new User(username, email, passwordhash);
        userRepository.save(user);
        return user.toString();
    }

    //check pwd
    public void checkAuthority(String username, String password) throws LoginException {
        Optional<User> opUser = userRepository.findByUsername(username);
        if (opUser.isEmpty())
            throw new LoginException(
                    "Client with id: " + username + " not found");
        User user = opUser.get();
        if (!BCrypt.checkpw(password, user.getPasswordhash()))  //todo
            throw new LoginException("Password is incorrect");
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
