package com.shop.mapper;

import java.util.List;

import com.shop.model.ItemVO;
import com.shop.model.CateFilterDTO;
import com.shop.model.CateVO;
import com.shop.model.Criteria;
import com.shop.model.SelectDTO;

public interface ItemMapper {

	/* 상품 검색 */
	public List<ItemVO> getGoodsList(Criteria cri);
	
	/* 상품 총 갯수 */
	public int goodsGetTotal(Criteria cri);		
	
	/* 브랜드 id 리스트 요청 */
	public String[] getBrandIdList(String keyword);	
	
	/* 국내 카테고리 리스트 */
	public List<CateVO> getCateCode1();
	
	/* 외국 카테고리 리스트 */
	public List<CateVO> getCateCode2();		
	
	/* 검색 대상 카테고리 리스트 */
	public String[] getCateList(Criteria cri);
	
	/* 카테고리 정보(+검색대상 갯수) */
	public CateFilterDTO getCateInfo(Criteria cri);		
	
	/* 상품 정보 */
	public ItemVO getGoodsInfo(int itemId);	
	
	/* 상품 id 이름 */
	public ItemVO getItemIdName(int itemId);	
	
	/* 평줌순 상품 정보 */
	public List<SelectDTO> likeSelect();	

}
