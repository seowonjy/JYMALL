package com.shop.model;

import java.util.Date;
import java.util.List;

public class ItemVO {

	/* 상품 id */
	private int itemId;
	
	/* 상품 이름 */
	private String itemName;
	
	/* 브랜드 id */
	private int brandId;
	
	/* 브랜드 이름 */
	private String brandName;
	
	/* 생산일 */
	private String cryear;
	
	/* 생산지 */
	private String creater;
	
	/* 카테고리 코드 */
	private String cateCode;
	
	/* 카테고리 이름 */
	private String cateName;
	
	/* 상품 가격 */
	private int itemPrice;
	
	/* 상품 재고 */
	private int itemStock;
	
	/* 상품 할인률(백분율) */
	private double itemDiscount;
	
	/* 상품 소개 */
	private String itemIntro;
	
	/* 상품 목차 */
	private String itemContents;
	
	/* 등록 날짜 */
	private Date regDate;
	
	/* 수정 날짜 */
	private Date updateDate;
	
	/* 이미지 정보 */
	private List<AttachImageVO> imageList;	

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getBrandId() {
		return brandId;
	}

	public void setBrandId(int brandId) {
		this.brandId = brandId;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getCryear() {
		return cryear;
	}

	public void setCryear(String cryear) {
		this.cryear = cryear;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public String getCateCode() {
		return cateCode;
	}

	public void setCateCode(String cateCode) {
		this.cateCode = cateCode;
	}

	public String getCateName() {
		return cateName;
	}

	public void setCateName(String cateName) {
		this.cateName = cateName;
	}

	public int getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(int itemPrice) {
		this.itemPrice = itemPrice;
	}

	public int getItemStock() {
		return itemStock;
	}

	public void setItemStock(int itemStock) {
		this.itemStock = itemStock;
	}

	public double getItemDiscount() {
		return itemDiscount;
	}

	public void setItemDiscount(double itemDiscount) {
		this.itemDiscount = itemDiscount;
	}

	public String getItemIntro() {
		return itemIntro;
	}

	public void setItemIntro(String itemIntro) {
		this.itemIntro = itemIntro;
	}

	public String getItemContents() {
		return itemContents;
	}

	public void setItemContents(String itemContents) {
		this.itemContents = itemContents;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public List<AttachImageVO> getImageList() {
		return imageList;
	}

	public void setImageList(List<AttachImageVO> imageList) {
		this.imageList = imageList;
	}

	@Override
	public String toString() {
		return "ItemVO [itemId=" + itemId + ", itemName=" + itemName + ", brandId=" + brandId + ", brandName="
				+ brandName + ", cryear=" + cryear + ", creater=" + creater + ", cateCode=" + cateCode
				+ ", cateName=" + cateName + ", itemPrice=" + itemPrice + ", itemStock=" + itemStock + ", itemDiscount="
				+ itemDiscount + ", itemIntro=" + itemIntro + ", itemContents=" + itemContents + ", regDate=" + regDate
				+ ", updateDate=" + updateDate + ", imageList=" + imageList + "]";
	}
	
}