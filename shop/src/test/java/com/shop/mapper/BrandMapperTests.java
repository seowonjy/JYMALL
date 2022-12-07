package com.shop.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.shop.model.BrandVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class BrandMapperTests {
 
    @Autowired
    private BrandMapper mapper;
    
    /* 작가 등록 테스트 */
    @Test
    public void brandEnroll() throws Exception{
        
        BrandVO brand = new BrandVO();
        
        brand.setNationId("01");
        brand.setBrandName("테스트");
        brand.setBrandIntro("테스트 소개");
        
        mapper.brandEnroll(brand);
        
    }    
    
}
 