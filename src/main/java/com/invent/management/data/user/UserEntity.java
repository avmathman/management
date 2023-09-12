package com.invent.management.data.user;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The entity of the application user with login.
 */
@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class UserEntity {

    /**
     * The user identifier.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * The date when the user entry was created.
     */
    @Column(name = "created_at")
    @CreationTimestamp
    @NotNull
    private Timestamp createdAt;

    /**
     * The date when the user entry was modified.
     */
    @Column(name = "modified_at")
    @UpdateTimestamp
    @NotNull
    private Timestamp modifiedAt;

    /**
     * The user firstname.
     */
    @Column(name = "firstname")
    @NotNull
    private String firstname;

    /**
     * The user lastname.
     */
    @Column(name = "lastname")
    @NotNull
    private String lastname;

    /**
     * The user e-mail address.
     */
    @Column(name = "email")
    @NotNull
    private String email;

    /**
     * Is user enabled in system.
     */
    @Column(name = "enabled")
    @NotNull
    private boolean enabled;
}
