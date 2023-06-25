package com.invent.management.data.address;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The entity of the address.
 */
@Entity
@Table(name = "addresses")
@Getter
@Setter
@NoArgsConstructor
public class AddressEntity {

    /**
     * The address identifier.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * The date when the address entry was created.
     */
    @Column(name = "created_at")
    @NotNull
    private Timestamp createdAt;

    /**
     * The date when the address entry was modified.
     */
    @Column(name = "modified_at")
    private Timestamp modifiedAt;

    /**
     * The address country.
     */
    @Column(name = "country")
    @NotNull
    private String country;

    /**
     * The address city.
     */
    @Column(name = "city")
    @NotNull
    private String city;

    /**
     * The address required street information.
     */
    @Column(name = "street1")
    @NotNull
    private String street1;

    /**
     * The address optional street information.
     */
    @Column(name = "street2")
    private String street2;

    /**
     * The address zipcode.
     */
    @Column(name = "zipcode")
    @NotNull
    private String zipcode;
}
