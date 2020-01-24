package com.banking.domain;

import com.banking.domain.security.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Data
@NoArgsConstructor
@Getter @Setter
public class User implements UserDetails {

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

    @OneToMany( mappedBy = "user", cascade = CascadeType.ALL,fetch = FetchType.LAZY )
    private List<Address> Addresses;

    @OneToMany( mappedBy = "user" ,cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Account> Accounts;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<UserRole> userRoles = new HashSet<>();

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Payee> payees = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }




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
                ", Addresses=" + Addresses +
                ", Accounts=" + Accounts +
                ", userRoles=" + userRoles +
                ", payees=" + payees +
                '}';
    }
}
