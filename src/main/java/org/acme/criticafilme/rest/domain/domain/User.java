package org.acme.criticafilme.rest.domain.domain;


import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;


@Entity
@Table(name = "users")
public class User {



   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;


    @Column(name = "name")
    private String name;
    @Column(name = "age")
    private Integer age;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
