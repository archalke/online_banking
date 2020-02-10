package com.banking.domain;

import com.banking.domain.security.Authority;
import com.banking.domain.security.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.*;


@Entity
@Data
@NoArgsConstructor
@Getter @Setter
public class User implements Serializable {

    @Id
    @GeneratedValue
    private Long Id;
    @NotBlank(message = "Username is mandatory")
    private String username;
    @NotBlank(message = "First Name is mandatory")
    private String firstName;
    @NotBlank(message = "Last Name is mandatory")
    private String lastName;
    private String password;
    private String email;
    private String phone;
    private boolean enabled=true;
    private String roles;

    @OneToMany( mappedBy = "user", cascade = CascadeType.ALL,fetch = FetchType.LAZY )
    private List<Address> Addresses;

    @OneToMany( mappedBy = "user" ,cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Account> Accounts;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Payee> payees = new HashSet<>();

    @Override
    public String toString() {
        return "User{" +
                "Id=" + Id +
                ", username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", enabled=" + enabled +
                ", Addresses size =" + Addresses.size() +
                ", Accounts size =" + Accounts.size() +
                "Roles "+roles+
                ", payees size=" + payees.size() +
                '}';
    }

}
