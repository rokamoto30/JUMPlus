package com.furnitureApp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.furnitureApp.dto.newPrododuct;
import com.furnitureApp.exception.InvalidException;
import com.furnitureApp.model.Product;
import com.furnitureApp.model.Stock;
import com.furnitureApp.repo.ProductRepo;
import com.furnitureApp.repo.StockRepo;

import jakarta.transaction.Transactional;

@Service
public class ProductService {
    @Autowired
    ProductRepo repo;

    @Autowired
    StockRepo stockRepo;
    
    public List<Product> getProducts() {
    	return repo.findAll();
    }
    
    public Product getProduct(Integer id) throws InvalidException {
    	Optional<Product> found = repo.findById(id);
    	if(found.isPresent()) {
    		return found.get();
    	}
    	throw new InvalidException( "No such Product exists" );
    }
    
    public Stock getStock(Integer prodId) throws InvalidException {
    	Optional<Stock> found = stockRepo.getStockByProductId(prodId);
    	if(found.isPresent()) {
    		return found.get();
    	}
    	throw new InvalidException( "No such stock exists" );
    }

    @Transactional
    public Product createProduct(newPrododuct newProd) {
    	Product created = repo.save(new Product(newProd.getName(), newProd.getImgUrl()));
        Stock newStock = new Stock(newProd.getQuantity(), newProd.getPrice(), created);
        stockRepo.save(newStock);
	    return created;
	}

    public Product editProduct(Product product) throws InvalidException {
        if (repo.existsById(product.getId())) {
			Product updated = repo.save(product);
			return updated;
		}
		throw new InvalidException( "No such product exists" );
    }
    
    public Stock editStock(Stock stock) throws InvalidException {
    	
        if (stockRepo.existsById(stock.getId())) {
        	int count = stockRepo.updateStock(stock.getPrice(), stock.getQuantity(), stock.getId());
        	if (count > 0) {
        		return stockRepo.findById((stock.getId())).get();
        	}
        	throw new InvalidException( "Stock not updated" );
        	
		}
		throw new InvalidException( "No such stock exists" );
    }    
}
