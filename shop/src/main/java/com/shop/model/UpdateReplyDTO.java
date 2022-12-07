package com.shop.model;

public class UpdateReplyDTO {
	
	private int itemId;
	
	private double ratingAvg;

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public double getRatingAvg() {
		return ratingAvg;
	}

	public void setRatingAvg(double ratingAvg) {
		this.ratingAvg = ratingAvg;
	}

	@Override
	public String toString() {
		return "UpdateReplyDTO [itemId=" + itemId + ", ratingAvg=" + ratingAvg + "]";
	}
	
}