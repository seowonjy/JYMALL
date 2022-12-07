package com.shop.service;

import java.util.List;

import com.shop.model.ItemVO;
import com.shop.model.CateFilterDTO;
import com.shop.model.CateVO;
import com.shop.model.Criteria;
import com.shop.model.SelectDTO;

public interface ItemService {

	/* 상품 검색 */
	public List<ItemVO> getGoodsList(Criteria cri);
	
	/* 상품 총 갯수 */
	public int goodsGetTotal(Criteria cri);	
	
	/* 국내 카테고리 리스트 */
	public List<CateVO> getCateCode1();
	
	/* 외국 카테고리 리스트 */
	public List<CateVO> getCateCode2();	
	
	/* 검색결과 카테고리 필터 정보 */
	public List<CateFilterDTO> getCateInfoList(Criteria cri);	
	
	/* 상품 정보 */
	public ItemVO getGoodsInfo(int itemId);		
	
	/* 상품 id 이름 */
	public ItemVO getItemIdName(int itemId);
	
	/* 평줌순 상품 정보 */
	public List<SelectDTO> likeSelect();		

}