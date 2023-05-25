package com.quanlybanhangonline.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "username"
        }),
        @UniqueConstraint(columnNames = {
                "email"
        }),
        @UniqueConstraint(columnNames = {
                "phone"
        })
})
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Size(min = 3, max = 50)
    private String name;
    @NotBlank
    @Size(min = 5, max = 100)
    private String address;
    @NotBlank
    @Size(max = 10)
    @Pattern(regexp = "(84|0[3|5|7|8|9])+([0-9]{8})", message = "Phone must have 10 numbers")
    private String phone;
    @NotBlank
    @Size(min = 3, max = 50)
    private String username;
    @NaturalId(mutable = true)
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;
    @JsonIgnore
    @NotBlank
    @Size(min = 6, max = 100)
    private String password;
    @ManyToMany(fetch = FetchType.EAGER, targetEntity = Role.class)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "userId"), inverseJoinColumns = @JoinColumn(name = "roleId") )
    Set<Role> roles= new HashSet<>();


    public User(String name,String address, String phone, String username, String email, String password, Set<Role> roles) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.address= address;
        this.phone= phone;
        this.roles = roles;
    }

    public User(@NotBlank
                @Size(min = 3, max = 50) String name,
                @NotBlank
                @Size(min = 5, max = 100) String address,
                @NotBlank String phone,
                @NotBlank
                @Size(min = 3, max = 50) String username,
                @NotBlank
                @Size(max = 50)
                @Email String email,
                @NotBlank
                @Size(min = 6, max = 100) String encode
    ){
        this.name= name;
        this.address= address;
        this.phone= phone;
        this.username= username;
        this.email= email;
        this.password= encode;
    }
}

