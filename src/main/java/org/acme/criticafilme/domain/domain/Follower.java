package org.acme.criticafilme.domain.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "followers")
public class Follower {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn (name = "user_id")
    private User user;


    @ManyToOne
    @JoinColumn (name = "follower_id")
    private User follower;




}
