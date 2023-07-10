package com.furnitureApp.dto;

import jakarta.validation.constraints.Min;

public class purchaseDTO {
	private int productId;
	@Min(0)
	private int quantity;
	
	public purchaseDTO() {}

	public purchaseDTO(int productId, int quantity) {
		super();
		this.productId = productId;
		this.quantity = quantity;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
}
