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
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
public class User extends AuditModel implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "user_name")
    @Size(max = 100)
    private String username;

    @Column(name = "full_name")
    @Size(max = 200)
    private String fullName;

    @Column(name = "email")
    @NaturalId
    @Size(max = 100)
    @NotBlank
    private String email;

    @NotBlank
    @Size(max = 100)
    private String password;

    @Column(name = "phone")
    @Size(max = 30)
    private String phone;

    @Column(name = "email_verified")
    private boolean emailVerified;

    @Column(name = "picture")
    private String picture;

    @Column(name = "is_locked")
    private Boolean isLocked;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

}
