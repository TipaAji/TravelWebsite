package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "countries")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "country_id")
    private Long id;
    @Column(name = "country")
    private String country_name;
    @Column(name = "create_date")
    @CreationTimestamp
    private Date createDate;
    @Column(name = "last_update")
    @UpdateTimestamp
    private Date lastUpdate;
    @OneToMany(mappedBy = "country")
    private Set<Division> divisions;
}
