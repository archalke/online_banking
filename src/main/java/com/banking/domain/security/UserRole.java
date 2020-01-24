package com.banking.domain.security;

import com.banking.domain.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@Getter @Setter
public class UserRole implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne( fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="role_id")
    private Role role;

    public UserRole(User user,Role role){
        this.user = user;
        this.role = role;
    }

}
