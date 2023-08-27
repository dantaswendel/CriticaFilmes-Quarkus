package org.acme.criticafilme.rest.domain.domain;


import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "users")
@Data
public class User {



   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;


    @Column(name = "name")
    private String name;
    @Column(name = "age")
    private Integer age;


}
