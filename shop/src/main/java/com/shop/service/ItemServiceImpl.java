package com.shop.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.mapper.AdminMapper;
import com.shop.mapper.AttachMapper;
import com.shop.mapper.ItemMapper;
import com.shop.model.AttachImageVO;
import com.shop.model.ItemVO;
import com.shop.model.CateFilterDTO;
import com.shop.model.CateVO;
import com.shop.model.Criteria;
import com.shop.model.SelectDTO;

@Service
public class ItemServiceImpl implements ItemService{

	private static final Logger log = LoggerFactory.getLogger(ItemServiceImpl.class);


	@Autowired
	private ItemMapper itemMapper;
	
	@Autowired
	private AttachMapper attachMapper;	
	
	@Autowired
	private AdminMapper adminMapper;		
	
	/* 상품 검색 */
	@Override
	public List<ItemVO> getGoodsList(Criteria cri) {
		
		log.info("getGoodsList().......");
		
		String type = cri.getType();
		String[] typeArr = type.split("");
		String[] brandArr = itemMapper.getBrandIdList(cri.getKeyword());
		
		
		if(type.equals("A") || type.equals("AC") || type.equals("AT") || type.equals("ACT")) {
			if(brandArr.length == 0) {
				return new ArrayList();
			}
		}
		
		for(String t : typeArr) {
			if(t.equals("A")) {
				cri.setBrandArr(brandArr);
			}
		}			
		
		List<ItemVO> list = itemMapper.getGoodsList(cri);
		
		list.forEach(item -> {
			
			int itemId = item.getItemId();
			
			List<AttachImageVO> imageList = attachMapper.getAttachList(itemId);
			
			item.setImageList(imageList);
			
		});
		
		return list;
	}

	/* 사품 총 갯수 */
	@Override
	public int goodsGetTotal(Criteria cri) {
		
		log.info("goodsGetTotal().......");
		
		return itemMapper.goodsGetTotal(cri);
		
	}		
	
	/* 국내 카테고리 리스트 */
	@Override
	public List<CateVO> getCateCode1() {
		
		log.info("getCateCode1().........");
		
		return itemMapper.getCateCode1();
	}

	/* 외국 카테고리 리스트 */
	@Override
	public List<CateVO> getCateCode2() {
		
		log.info("getCateCode2().........");
		
		return itemMapper.getCateCode2();
	}		
	
	/* 검색결과 카테고리 필터 정보 */
	@Override
	public List<CateFilterDTO> getCateInfoList(Criteria cri) {

		List<CateFilterDTO> filterInfoList = new ArrayList<CateFilterDTO>();
		
		String[] typeArr = cri.getType().split("");
		String [] brandArr;
		
		for(String type : typeArr) {
			if(type.equals("A")){
				brandArr = itemMapper.getBrandIdList(cri.getKeyword());
				if(brandArr.length == 0) {
					return filterInfoList;
				}
				cri.setBrandArr(brandArr);
			}
		}
		
		String[] cateList = itemMapper.getCateList(cri);
		
		String tempCateCode = cri.getCateCode();
		
		for(String cateCode : cateList) {
			cri.setCateCode(cateCode);
			CateFilterDTO filterInfo = itemMapper.getCateInfo(cri);
			filterInfoList.add(filterInfo);
		}
		
		cri.setCateCode(tempCateCode);
		
		return filterInfoList;
	}	
	
	/* 상품 정보 */
	@Override
	public ItemVO getGoodsInfo(int itemId) {
		
		ItemVO goodsInfo = itemMapper.getGoodsInfo(itemId);
		goodsInfo.setImageList(adminMapper.getAttachInfo(itemId));
		
		
		return goodsInfo;
	}	
	
	@Override
	public ItemVO getItemIdName(int itemId) {
		
		return itemMapper.getItemIdName(itemId);
	}	
	
	@Override
	public List<SelectDTO> likeSelect() {
		
		List<SelectDTO> list = itemMapper.likeSelect();
		
		list.forEach(dto -> {
			
			int itemId = dto.getItemId();
			
			List<AttachImageVO> imageList = attachMapper.getAttachList(itemId);
			
			dto.setImageList(imageList);
			
		});				
		
		
		return list;	

	}		
	
}