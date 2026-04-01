package com.cfs.SecurityP03.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = false,length = 50)
    private String name;

    @Column(length = 200)
    private String description;


    public Role(){}

    private Role(String name,String description)
    {
        this.name=name;
        this.description=description;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static RoleBuilder builder(){
        return new RoleBuilder();
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    //builder pattern
    public static class RoleBuilder
    {
        private String name;
        private String description;

        public RoleBuilder name(String name)
        {
            this.name=name;
            return this;
        }

        public RoleBuilder description(String description)
        {
            this.description=description;
            return this;
        }

        public Role build()
        {
            return new Role(name,description);
        }


    }
}
