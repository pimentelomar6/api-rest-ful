package com.pimentelprojects.api.models;


import lombok.*;

import jakarta.persistence.*;

@Entity
@Table(name = "clients")
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
