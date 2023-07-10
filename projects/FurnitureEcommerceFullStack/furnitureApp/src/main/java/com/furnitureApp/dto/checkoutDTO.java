package com.furnitureApp.dto;

import java.time.LocalDateTime;
import java.util.List;


public class checkoutDTO {
	private LocalDateTime time;
	private int userId;
	private List<purchaseDTO> purchases;
	
	public checkoutDTO() {
	}

	public checkoutDTO(LocalDateTime time, int userId, List<purchaseDTO> purchases) {
		super();
		this.time = time;
		this.userId = userId;
		this.purchases = purchases;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public List<purchaseDTO> getPurchases() {
		return purchases;
	}

	public void setPurchases(List<purchaseDTO> purchases) {
		this.purchases = purchases;
	}
	
	
}
