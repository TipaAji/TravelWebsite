package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "excursions")
@Getter
@Setter
public class Excursion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "excursion_id")
    private Long id;
    @Column(name = "excursion_title")
    private String excursion_title;
    @Column(name = "excursion_price")
    private BigDecimal excursion_price;
    @Column(name = "image_url")
    private String image_URL;
    @Column(name = "create_date")
    @CreationTimestamp
    private Date createDate;
    @Column(name = "last_update")
    @UpdateTimestamp
    private Date lastUpdate;
    @JoinColumn(name = "vacation_id")
    @ManyToOne
    private Vacation vacation;
    @ManyToMany
    private Set<CartItems> cartitems;
}
