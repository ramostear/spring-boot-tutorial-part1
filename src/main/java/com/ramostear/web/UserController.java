package com.ramostear.web;

import com.ramostear.persistence.model.User;
import com.ramostear.persistence.repo.UserRepository;
import com.ramostear.web.exception.UserIdMismatchException;
import com.ramostear.web.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ramostear
 * @create-time 2019/3/3 0003-3:11
 * @modify by :
 * @since:
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public Iterable<User> findAll(){
        return userRepository.findAll();
    }

    @GetMapping("/username/{username}")
    public List<User> findByUsername(@PathVariable(name = "username")String username){
        return userRepository.findAllByUsername(username);
    }

    @GetMapping("/{id}")
    public User findOne(@PathVariable Long id){
        return userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestBody User user){
        User u = userRepository.save(user);
        return u;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
        userRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public User updateUser(@RequestBody User user,@PathVariable Long id){
        if(!id.equals(user.getId())){
            throw new UserIdMismatchException();
        }
        userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
        return userRepository.save(user);
    }



}
