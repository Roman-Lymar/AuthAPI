package com.springboot.authapiproject.models;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(name = "role")
    private String role;

//    @OneToMany(mappedBy = "role")
//    private List<User> users;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setName(String role) {
        this.role = role;
    }

    public String getName() {
        return role;
    }
}
