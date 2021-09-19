package com.restaurant.model;


public class RestaurantVO implements java.io.Serializable{
	private Integer restaurantno;
	private String restaurantname;
	private String restaurantlevel;
	private String restaurantadd;
	private String restaurantnum;
	private String restauranttype;
	private Integer locationno;
	
	public Integer getRestaurantno() {
		return restaurantno;
	}
	public void setRestaurantno(Integer restaurantno) {
		this.restaurantno = restaurantno;
	}
	public String getRestaurantname() {
		return restaurantname;
	}
	public void setRestaurantname(String restaurantname) {
		this.restaurantname = restaurantname;
	}
	public String getRestaurantlevel() {
		return restaurantlevel;
	}
	public void setRestaurantlevel(String restaurantlevel) {
		this.restaurantlevel = restaurantlevel;
	}
	public String getRestaurantadd() {
		return restaurantadd;
	}
	public void setRestaurantadd(String restaurantadd) {
		this.restaurantadd = restaurantadd;
	}
	public String getRestaurantnum() {
		return restaurantnum;
	}
	public void setRestaurantnum(String restaurantnum) {
		this.restaurantnum = restaurantnum;
	}
	public String getRestauranttype() {
		return restauranttype;
	}
	public void setRestauranttype(String restauranttype) {
		this.restauranttype = restauranttype;
	}
	public Integer getLocationno() {
		return locationno;
	}
	public void setLocationno(Integer locationno) {
		this.locationno = locationno;
	}
}
