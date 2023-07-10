package com.furnitureApp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.furnitureApp.dto.checkoutDTO;
import com.furnitureApp.dto.purchaseDTO;
import com.furnitureApp.exception.InvalidException;
import com.furnitureApp.model.Checkout;
import com.furnitureApp.model.Product;
import com.furnitureApp.model.Purchase;
import com.furnitureApp.model.Stock;
import com.furnitureApp.repo.CheckoutRepo;
import com.furnitureApp.repo.PurchaseRepo;
import com.furnitureApp.repo.StockRepo;

import jakarta.transaction.Transactional;

@Service
public class CheckoutService {
    @Autowired
    CheckoutRepo repo;
    
    @Autowired
    PurchaseRepo purchaseRepo;
    
    @Autowired
    StockRepo stockRepo;
    
    @Autowired
    UserService userService;
    
    @Autowired
    ProductService productService;
    
    @Transactional
    public Checkout createCheckout(checkoutDTO checkoutDTO) throws InvalidException {
    	
    	Checkout checkout = new Checkout(checkoutDTO.getTime(), userService.getUserById(checkoutDTO.getUserId()));
    	Checkout savedCheckout = repo.save(checkout);
    	
    	for(purchaseDTO purchase : checkoutDTO.getPurchases()) {
    		Product product = productService.getProduct(purchase.getProductId());
    		Stock stock = productService.getStock(product.getId());
    		if (purchase.getQuantity() > stock.getQuantity()) {
    			throw new InvalidException("There are only " + stock.getQuantity() + " of product " + product.getName() + "left in stock");
    		}
    		
    		stockRepo.updateStock(stock.getPrice(), stock.getQuantity() - purchase.getQuantity(), stock.getId());
    		
    		Purchase newPurchase = new Purchase(purchase.getQuantity(), savedCheckout, product);
    		purchaseRepo.save(newPurchase);
    	}
    	
    	return savedCheckout;
    }
    
    public Checkout getCheckoutById(Integer id) throws InvalidException {
    	Optional<Checkout> found = repo.findById(id);
    	if (found.isPresent()) {
    		return found.get();
    	}
    	throw new InvalidException("Checkout not found");
    }
    
    public List<Checkout> getCheckoutByUserId(Integer id) throws InvalidException {
    	return repo.getCheckoutByUserId(id);
    }
    
    

    

    
}
