package com.pikolic.meli.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "client")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Integer age;
    @Column(nullable = false)
    private String email;
    private String address;

    @OneToMany(mappedBy = "client", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<OrderEntity> orders = new ArrayList<>();
}