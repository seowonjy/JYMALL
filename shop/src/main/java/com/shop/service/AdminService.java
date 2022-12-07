package com.shop.service;

import java.util.List;

import com.shop.model.AttachImageVO;
import com.shop.model.ItemVO;
import com.shop.model.CateVO;
import com.shop.model.Criteria;
import com.shop.model.OrderDTO;

public interface AdminService {

	/* 상품 등록 */
	public void itemEnroll(ItemVO item);		
	
	/* 카테고리 리스트 */
	public List<CateVO> cateList();	
	
	/* 상품 리스트 */
	public List<ItemVO> goodsGetList(Criteria cri);
	
	/* 상품 총 개수 */
	public int goodsGetTotal(Criteria cri);	
	
	/* 상품 조회 페이지 */
	public ItemVO goodsGetDetail(int itemId);		

	/* 상품 수정 */
	public int goodsModify(ItemVO vo);	
	
	/* 상품 정보 삭제 */
	public int goodsDelete(int itemId);	
	
	/* 지정 상품 이미지 정보 얻기 */
	public List<AttachImageVO> getAttachInfo(int itemId);	
	
	/* 주문 상품 리스트 */
	public List<OrderDTO> getOrderList(Criteria cri);
	
	/* 주문 총 갯수 */
	public int getOrderTotal(Criteria cri);		
	
}