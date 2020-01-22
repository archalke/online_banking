package com.banking.domain.security;

import com.banking.domain.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Getter @Setter
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
//    @JsonIgnore
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="role_id")
//    @JsonIgnore
    private Role role;

    public UserRole(User user,Role role){
        this.user = user;
        this.role = role;
    }

}
