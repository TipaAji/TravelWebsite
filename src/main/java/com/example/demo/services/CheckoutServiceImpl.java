package com.example.demo.services;

import com.example.demo.dao.CartRepository;
import com.example.demo.dao.CustomerRepository;
import com.example.demo.entities.Cart;
import com.example.demo.entities.CartItems;
import com.example.demo.entities.Customer;
import com.example.demo.entities.StatusType;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public class CheckoutServiceImpl implements CheckoutService{

    private final CartRepository cartRepository;

    public CheckoutServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {
        Cart cart = purchase.getCart();

        String orderTrackingNumber = generateOrderTrackingNumber();
        cart.setOrderTrackingNumber(orderTrackingNumber);

        Set<CartItems> cartItems = purchase.getCartItems();
        cartItems.forEach(cart::add);

        cart.setCustomer(purchase.getCustomer());
        cart.setCartItem(purchase.getCartItems());
        Customer customer = purchase.getCustomer();
        customer.add(cart);

        if(cartItems.isEmpty()){
            return new PurchaseResponse("Cart is empty");
        }
        if(cart.getParty_size() < 1){
            return new PurchaseResponse("Party Size must be positive");
        }

        cart.setStatus(StatusType.ordered);
        cartRepository.save(cart);

        return new PurchaseResponse(orderTrackingNumber);
    }

    private String generateOrderTrackingNumber() {
        return UUID.randomUUID().toString();
    }
}
