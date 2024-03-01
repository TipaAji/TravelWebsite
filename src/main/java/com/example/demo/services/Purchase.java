package com.example.demo.services;

import com.example.demo.entities.Cart;
import com.example.demo.entities.CartItems;
import com.example.demo.entities.Customer;
import lombok.Data;

import java.util.Set;

@Data
public class Purchase {
    private Customer customer;
    private Cart cart;
    private Set<CartItems> cartItems;
}
