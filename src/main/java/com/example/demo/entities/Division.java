package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "divisions")
@Getter
@Setter
public class Division {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "division_id")
    private Long id;
    @Column(name = "division")
    private String divisionName;
    @Column(name = "create_date")
    @CreationTimestamp
    private Date createDate;
    @Column(name = "last_update")
    @UpdateTimestamp
    private Date lastUpdate;
    @JoinColumn(name = "country_id", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Country country;
    @OneToMany(mappedBy = "division")
    private Set<Customer> customers;
    @Column(name = "country_id")
    private long country_id;

    public void setCountry(Country country){
        setCountry_id((country.getId()));
        this.country = country;
    }
}
