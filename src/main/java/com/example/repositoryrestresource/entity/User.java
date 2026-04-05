package com.example.repositoryrestresource.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private int age;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")
    private List<Address> address;

    public void setAddress(List<Address> address) {
        this.address = address;
        if (address != null) {
            address.forEach(addr -> addr.setUser(this));
        }
    }


    public User(String firstName, String lastName, int age, List<Address> address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.address = address;
        // Set bidirectional relationship
        if (address != null) {
            address.forEach(addr -> addr.setUser(this));
        }
    }
}

