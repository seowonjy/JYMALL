package com.shop.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.shop.model.AttachImageVO;
import com.shop.model.ItemVO;
import com.shop.model.Criteria;
import com.shop.model.PageDTO;
import com.shop.model.ReplyDTO;
import com.shop.service.AttachService;
import com.shop.service.ItemService;
import com.shop.service.ReplyService;

@Controller
public class ShopController {

	private static final Logger logger = LoggerFactory.getLogger(ShopController.class);
			
	@Autowired
	private AttachService attachService;
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private ReplyService replyService;
	
	//메인 페이지 이동
	@RequestMapping(value="/main", method = RequestMethod.GET)
	public void mainPageGET(Model model) {
		
		logger.info("메인 페이지 진입");
		
		model.addAttribute("cate1", itemService.getCateCode1());
		model.addAttribute("cate2", itemService.getCateCode2());
		model.addAttribute("ls", itemService.likeSelect());		
		
	}
	
			
	/* 이미지 출력 */
	@GetMapping("/display")
	public ResponseEntity<byte[]> getImage(String fileName){
		
		logger.info("getImage()........" + fileName);
		
		File file = new File("c:\\upload\\" + fileName);
		
		ResponseEntity<byte[]> result = null;
		
		try {
			
			HttpHeaders header = new HttpHeaders();
			
			header.add("Content-type", Files.probeContentType(file.toPath()));
			
			result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
			
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		return result;
		
	}			

	/* 이미지 정보 반환 */
	@GetMapping(value="/getAttachList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<AttachImageVO>> getAttachList(int itemId){
		
		logger.info("getAttachList.........." + itemId);
		
		return new ResponseEntity<List<AttachImageVO>>(attachService.getAttachList(itemId), HttpStatus.OK);
		
	}	
	
	/* 상품 검색 */
	@GetMapping("/search")
	public String searchGoodsGET(Criteria cri, Model model) {
		
		logger.info("cri : " + cri);
		
		List<ItemVO> list = itemService.getGoodsList(cri);
		logger.info("pre list : " + list);
		if(!list.isEmpty()) {
			model.addAttribute("list", list);
			logger.info("list : " + list);
		} else {
			model.addAttribute("listcheck", "empty");
			
			return "search";
		}
		
		model.addAttribute("pageMaker", new PageDTO(cri, itemService.goodsGetTotal(cri)));
		
		String[] typeArr = cri.getType().split("");
		
		for(String s : typeArr) {
			if(s.equals("T") || s.equals("A")) {
				model.addAttribute("filter_info", itemService.getCateInfoList(cri));		
			}
		}
		
		return "search";
		
	}		
	
	/* 상품 상세 */
	@GetMapping("/goodsDetail/{itemId}")
	public String goodsDetailGET(@PathVariable("itemId")int itemId, Model model) {
		
		logger.info("goodsDetailGET()..........");
		
		model.addAttribute("goodsInfo", itemService.getGoodsInfo(itemId));
		
		return "/goodsDetail";
	}	
	
	/* 리뷰 쓰기 */
	@GetMapping("/replyEnroll/{memberId}")
	public String replyEnrollWindowGET(@PathVariable("memberId")String memberId, int itemId, Model model) {
		ItemVO item = itemService.getItemIdName(itemId);
		model.addAttribute("bookInfo", item);
		model.addAttribute("memberId", memberId);
		
		return "/replyEnroll";
	}		
	
	/* 리뷰 수정 팝업창 */
	@GetMapping("/replyUpdate")
	public String replyUpdateWindowGET(ReplyDTO dto, Model model) {
		ItemVO item = itemService.getItemIdName(dto.getItemId());
		model.addAttribute("bookInfo", item);
		model.addAttribute("replyInfo", replyService.getUpdateReply(dto.getReplyId()));
		model.addAttribute("memberId", dto.getMemberId());
		
		return "/replyUpdate";
	}	
			
}