package com.shop.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shop.mapper.AdminMapper;
import com.shop.model.AttachImageVO;
import com.shop.model.ItemVO;
import com.shop.model.CateVO;
import com.shop.model.Criteria;
import com.shop.model.OrderDTO;

@Service
public class AdminServiceImpl implements AdminService {
	
	private static final Logger log = LoggerFactory.getLogger(AdminServiceImpl.class);

	@Autowired
	private AdminMapper adminMapper;	
	
	/* 상품 등록 */
	@Transactional
	@Override
	public void itemEnroll(ItemVO item) {
		
		log.info("(srevice)itemEnroll........");
		
		adminMapper.itemEnroll(item);
		
		if(item.getImageList() == null || item.getImageList().size() <= 0) {
			return;
		}
		
		item.getImageList().forEach(attach ->{
			
			attach.setItemId(item.getItemId());
			adminMapper.imageEnroll(attach);
			
		});
		
		
	}
	
	/* 카테고리 리스트 */
	@Override
	public List<CateVO> cateList() {
		
		log.info("(service)cateList........");
		
		return adminMapper.cateList();
	}		
	
	/* 상품 리스트 */
	@Override
	public List<ItemVO> goodsGetList(Criteria cri) {
		log.info("goodsGetTotalList()..........");
		return adminMapper.goodsGetList(cri);
	}

	/* 상품 총 갯수 */
	public int goodsGetTotal(Criteria cri) {
		log.info("goodsGetTotal().........");
		return adminMapper.goodsGetTotal(cri);
	}	
	
	/* 상품 조회 페이지 */
	@Override
	public ItemVO goodsGetDetail(int itemId) {
		
		log.info("(service)itemGetDetail......." + itemId);
		
		return adminMapper.goodsGetDetail(itemId);
	}		
	
	/* 상품 정보 수정 */
	@Transactional
	@Override
	public int goodsModify(ItemVO vo) {

		int result = adminMapper.goodsModify(vo);
		
		if(result == 1 && vo.getImageList() != null && vo.getImageList().size() > 0) {
			
			adminMapper.deleteImageAll(vo.getItemId());
			
			vo.getImageList().forEach(attach -> {
				
				attach.setItemId(vo.getItemId());
				adminMapper.imageEnroll(attach);
				
			});
			
		}
		
		return result;
	}		
	
	/* 상품 정보 삭제 */
	@Override
	@Transactional	
	public int goodsDelete(int itemId) {

		log.info("goodsDelete..........");
		
		adminMapper.deleteImageAll(itemId);			
		
		return adminMapper.goodsDelete(itemId);
	}		
	
	/* 지정 상품 이미지 정보 얻기 */
	@Override
	public List<AttachImageVO> getAttachInfo(int itemId) {
		
		log.info("getAttachInfo........");
		
		return adminMapper.getAttachInfo(itemId);
	}		
	
	/* 주문 상품 리스트 */
	@Override
	public List<OrderDTO> getOrderList(Criteria cri) {
		return adminMapper.getOrderList(cri);
	}
	
	/* 주문 총 갯수 */
	@Override
	public int getOrderTotal(Criteria cri) {
		return adminMapper.getOrderTotal(cri);
	}		
	
}