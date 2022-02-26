package com.mindtree.bookyourmeal.modules.admin.dto;

public class StatisticsDto {
    private int orders;
    private int totalCustomers;
    private int totalRestaurant;
    private int approvedRestaurant;
    private int rejectedRestaurant;
    private int pendingRestaurant;
	public int getOrders() {
		return orders;
	}
	public void setOrders(int orders) {
		this.orders = orders;
	}
	public int getTotalCustomers() {
		return totalCustomers;
	}
	public void setTotalCustomers(int totalCustomers) {
		this.totalCustomers = totalCustomers;
	}
	public int getTotalRestaurant() {
		return totalRestaurant;
	}
	public void setTotalRestaurant(int totalRestaurant) {
		this.totalRestaurant = totalRestaurant;
	}
	public int getApprovedRestaurant() {
		return approvedRestaurant;
	}
	public void setApprovedRestaurant(int approvedRestaurant) {
		this.approvedRestaurant = approvedRestaurant;
	}
	public int getRejectedRestaurant() {
		return rejectedRestaurant;
	}
	public void setRejectedRestaurant(int rejectedRestaurant) {
		this.rejectedRestaurant = rejectedRestaurant;
	}
	public int getPendingRestaurant() {
		return pendingRestaurant;
	}
	public void setPendingRestaurant(int pendingRestaurant) {
		this.pendingRestaurant = pendingRestaurant;
	}
	@Override
	public String toString() {
		return "StatisticsDto [orders=" + orders + ", totalCustomers=" + totalCustomers + ", totalRestaurant="
				+ totalRestaurant + ", approvedRestaurant=" + approvedRestaurant + ", rejectedRestaurant="
				+ rejectedRestaurant + ", pendingRestaurant=" + pendingRestaurant + "]";
	}
	
	
    
    
    
}
