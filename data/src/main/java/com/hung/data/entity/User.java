/**
 * ****************************************************
 * * Description :
 * * File        : User.java
 * * Author      : hung.tran
 * * Date        : Nov 11, 2020
 * ****************************************************
 **/
package com.hung.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Data
@Entity
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email_verified")
    private boolean emailVerified;

    @Column(name = "picture")
    private String picture;

    @Column(name = "is_locked")
    private Boolean isLocked;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

}
