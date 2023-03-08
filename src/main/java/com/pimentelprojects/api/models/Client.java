package com.pimentelprojects.api.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

@Entity
@Table(name = "clients")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Client {

    @Id
    @Column(nullable = false, unique = true)
    @SequenceGenerator(
            name = "client_id_seq",
            sequenceName = "client_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "client_id_seq"
    )
    private Long id;
    private String name;
    @Column(nullable = false, unique = true)
    private String mail;

    private String phoneNumber;

    private String address;



}
