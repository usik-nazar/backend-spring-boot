package com.nazar.backendspringboot.dao.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity(name = "app_user")
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    private String login;
    private String password;
}
