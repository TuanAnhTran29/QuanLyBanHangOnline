package com.quanlybanhangonline.controller;

import com.quanlybanhangonline.dto.request.ForgotPasswordForm;
import com.quanlybanhangonline.dto.request.SignInForm;
import com.quanlybanhangonline.dto.request.SignUpForm;
import com.quanlybanhangonline.dto.response.JWTResponse;
import com.quanlybanhangonline.dto.response.ResponseMessage;
import com.quanlybanhangonline.model.Role;
import com.quanlybanhangonline.model.RoleName;
import com.quanlybanhangonline.model.User;
import com.quanlybanhangonline.security.jwt.JWTProvider;
import com.quanlybanhangonline.security.userprinciple.UserPrinciple;
import com.quanlybanhangonline.service.roleservice.IRoleService;
import com.quanlybanhangonline.service.userservice.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/authentication")
public class AuthController {
    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTProvider jwtProvider;

    @PostMapping("/signup")
    public ResponseEntity<?> register(@Valid @RequestBody SignUpForm signUpForm){
        if(userService.existsByUsername(signUpForm.getUsername())){
            return new ResponseEntity<>(new ResponseMessage("Username Existed! Please try again!"), HttpStatus.OK);
        }
        if(userService.existsByEmail(signUpForm.getEmail())){
            return new ResponseEntity<>(new ResponseMessage("Email Existed! Please try again!"), HttpStatus.OK);
        }
        if (!signUpForm.getEmail().matches("[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}")){
            return new ResponseEntity<>(new ResponseMessage("Wrong Email Format! Please try again!"),HttpStatus.OK);
        }
        if(userService.existsByPhone(signUpForm.getPhone())){
            return new ResponseEntity<>(new ResponseMessage("Phone Existed! Please try again!"), HttpStatus.OK);
        }
        if (!signUpForm.getPhone().matches("(84|0[3|5|7|8|9])+([0-9]{8})")){
            return new ResponseEntity<>(new ResponseMessage("Wrong Phone Format! Please try again!"),HttpStatus.OK);
        }
        if(signUpForm.getPassword().length() < 6){
            return new ResponseEntity<>(new ResponseMessage("Password must be longer than 6 characters!"),HttpStatus.OK);
        }
        if(!signUpForm.getPassword().equals(signUpForm.getRe_enterPassword())){
            return new ResponseEntity<>(new ResponseMessage("Re-enter password must be matched!"),HttpStatus.OK);
        }
        User users = new User(signUpForm.getName(), signUpForm.getAddress(), signUpForm.getPhone() ,signUpForm.getUsername(), signUpForm.getEmail(), passwordEncoder.encode(signUpForm.getPassword()));
        Set<String> strRoles = signUpForm.getRoles();
        Set<Role> roles = new HashSet<>();
        strRoles.forEach(role ->{
            switch (role){
                case "ADMIN":
                    Role adminRole = roleService.findByName(RoleName.ADMIN).orElseThrow( ()-> new RuntimeException("Role not found"));
                    roles.add(adminRole);
                    break;
                default:
                    Role userRole = roleService.findByName(RoleName.USER).orElseThrow( ()-> new RuntimeException("Role not found"));
                    roles.add(userRole);
            }
        });
        users.setRoles(roles);
        userService.save(users);
        return new ResponseEntity<>(new ResponseMessage("Signed Up Successfully!"), HttpStatus.OK);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> login(@Valid @RequestBody SignInForm signInForm){
        if (!userService.findByUsername(signInForm.getUsername()).isPresent()){
            return new ResponseEntity<>(new ResponseMessage("Login failed! Try again!"),HttpStatus.NOT_FOUND);
        }
        Optional<User> user= userService.findByUsername(signInForm.getUsername());
        if (!passwordEncoder.matches(signInForm.getPassword(),user.get().getPassword())){
            return new ResponseEntity<>(new ResponseMessage("Wrong password! Try again!"),HttpStatus.NOT_FOUND);
        }
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInForm.getUsername(), signInForm.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.createToken(authentication);
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        return ResponseEntity.ok(new JWTResponse(userPrinciple.getId(),token, userPrinciple.getName(),userPrinciple.getAuthorities()));
    }

    @PostMapping("/forgotPassword")
    public ResponseEntity<?> forgotPassword(@RequestBody ForgotPasswordForm forgotPasswordForm){
        Boolean haveUser= userService.existsByUsername(forgotPasswordForm.getUsername());
        if(!haveUser){
            return new ResponseEntity<>(new ResponseMessage("Can not find this user"),HttpStatus.OK);
        }else{
            Optional<User> user= userService.findByUsername(forgotPasswordForm.getUsername());
            if (forgotPasswordForm.getPassword().length() < 6){
                return new ResponseEntity<>(new ResponseMessage("Password must be more than 6 characters"), HttpStatus.OK);
            }
            if (!forgotPasswordForm.getPassword().equals(forgotPasswordForm.getRe_enterPassword())){
                return new ResponseEntity<>(new ResponseMessage("Re-enter incorrect password!"), HttpStatus.OK);
            }
            if (forgotPasswordForm.getPassword().equals(user.get().getPassword())){
                return new ResponseEntity<>(new ResponseMessage("The new password cannot be the same as the old password"),HttpStatus.OK);
            }
            user.get().setPassword(passwordEncoder.encode(forgotPasswordForm.getPassword()));
            userService.save(user.get());
        }
        return new ResponseEntity<>(new ResponseMessage("Your password have been changed!"),HttpStatus.OK);
    }
}
