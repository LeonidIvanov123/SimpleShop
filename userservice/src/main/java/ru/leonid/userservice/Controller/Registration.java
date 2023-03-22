package ru.leonid.userservice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.leonid.userservice.Model.User;
import ru.leonid.userservice.Model.UserRepository;

@RestController
@RequestMapping("/registration")
public class Registration {

    @Value("${eureka.instance.instance-id}")
    private String instanceAplication;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/{username}/{email}/{password}")
    public User processRegistration(@PathVariable("username") String username,
                                    @PathVariable("email") String email,
                                    @PathVariable("password") String password){
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        userRepository.save(user);
        return user;
    }

    @GetMapping("/test")
    public String testController(){
        return "request from #userservice#: " + instanceAplication;
    }

}
