package com.example.demo.services;

import com.example.demo.dao.CartItemRepository;
import com.example.demo.dao.CartRepository;
import com.example.demo.entities.Cart;
import com.example.demo.entities.CartItem;
import com.example.demo.entities.StatusType;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public class CheckoutServiceImpl implements CheckoutService{

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    public CheckoutServiceImpl(CartRepository cartRepository, CartItemRepository cartItemRepository) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
    }

    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {
        Cart cart = purchase.getCart();

        String orderTrackingNumber = generateOrderTrackingNumber();
        cart.setOrderTrackingNumber(orderTrackingNumber);

        Set<CartItem> cartItems = purchase.getCartItems();
        cartItems.forEach(item -> {cart.add(item);
            item.setCart(cart);
            item.setVacation(item.getVacation());
            item.getExcursions().forEach(excursion -> {excursion.setVacation(item.getVacation());});
        });

        //cart.setCustomer(purchase.getCustomer());
        cart.setCartItems(cartItems);


        if(cartItems.isEmpty()){
            return new PurchaseResponse("Cart is empty");
        }
        if(cart.getParty_size() < 1){
            return new PurchaseResponse("Party Size must be positive");
        }

        cart.setStatus(StatusType.ordered);
        cartRepository.save(cart);
        //cartItemRepository.saveAll(cartItems);
        return new PurchaseResponse(orderTrackingNumber);
    }

    private String generateOrderTrackingNumber() {
        return UUID.randomUUID().toString();
    }
}
