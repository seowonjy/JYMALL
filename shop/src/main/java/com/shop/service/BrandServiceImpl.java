package com.shop.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.mapper.BrandMapper;
import com.shop.model.BrandVO;
import com.shop.model.Criteria;

@Service
public class BrandServiceImpl implements BrandService{
	
	private static final Logger log = LoggerFactory.getLogger(BrandServiceImpl.class);
	
	@Autowired
	BrandMapper brandMapper;
	
	@Override
    public void brandEnroll(BrandVO brand) throws Exception {
        
        brandMapper.brandEnroll(brand);   
    }
	
	@Override
    public List<BrandVO> brandGetList(Criteria cri) throws Exception {
		log.info("(service)brandGetList()"+cri);
        
        return brandMapper.brandGetList(cri);
    }
	
	@Override
    public int brandGetTotal(Criteria cri) throws Exception {
        log.info("(service)brandGetTotal()......." + cri);
        return brandMapper.brandGetTotal(cri);
    }
	
	@Override
	public BrandVO brandGetDetail(int brandId) throws Exception {
		log.info("brandGetDetail........" + brandId);
		return brandMapper.brandGetDetail(brandId);
	}
	
	@Override
	public int brandModify(BrandVO brand) throws Exception {
		log.info("(service) brandModify........." + brand);
		return brandMapper.brandModify(brand);
	}
	
	@Override
	public int brandDelete(int brandId) {
		
		log.info("brandDelete..........");
		
		return brandMapper.brandDelete(brandId);
	}	

}
