package com.quanlybanhangonline.controller;

import com.quanlybanhangonline.dto.response.ResponseMessage;
import com.quanlybanhangonline.model.User;
import com.quanlybanhangonline.service.userservice.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user/api")
public class UserController {
    @Autowired
    private IUserService userService;

    @GetMapping
    public ResponseEntity<Iterable<User>> getAllUser(){
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<User>> getUserById(@PathVariable Long id){
        return new ResponseEntity<>(userService.findById(id),HttpStatus.OK);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> updateUser(@RequestBody User user , @PathVariable Long id){
        Optional<User> userOptional= userService.findById(id);
        if (!userOptional.isPresent()){
            return new ResponseEntity<>(new ResponseMessage("Can not find this user!") ,HttpStatus.NOT_FOUND);
        }

        if (userService.existsByEmail(user.getEmail()) && !userOptional.get().getEmail().equals(user.getEmail())){
            if (userService.existsByPhone(user.getPhone()) && !userOptional.get().getPhone().equals(user.getPhone())){
                return new ResponseEntity<>(new ResponseMessage("Your email and phone number are existed! Please try again!"),HttpStatus.OK);
            }else {
                return new ResponseEntity<>(new ResponseMessage("Your email is existed! Please try again!"),HttpStatus.OK);
            }
        }else {
            if (userService.existsByPhone(user.getPhone()) && !userOptional.get().getPhone().equals(user.getPhone())){
                return new ResponseEntity<>(new ResponseMessage("Your phone is existed! Please try again!"),HttpStatus.OK);
            }else {
                userOptional.get().setPhone(user.getPhone());
                userOptional.get().setEmail(user.getEmail());
                userOptional.get().setName(user.getName());
                userOptional.get().setAddress(user.getAddress());
                userService.save(userOptional.get());
                return new ResponseEntity<>(new ResponseMessage("Updated user successfully!"),HttpStatus.OK);
            }
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        userService.remove(id);
        return new ResponseEntity<>(new ResponseMessage("Deleted successfully"), HttpStatus.OK);
    }
}
