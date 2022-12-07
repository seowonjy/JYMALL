package com.shop.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shop.mapper.AttachMapper;
import com.shop.mapper.ItemMapper;
import com.shop.mapper.CartMapper;
import com.shop.mapper.MemberMapper;
import com.shop.mapper.OrderMapper;
import com.shop.model.AttachImageVO;
import com.shop.model.ItemVO;
import com.shop.model.CartDTO;
import com.shop.model.MemberVO;
import com.shop.model.OrderCancelDTO;
import com.shop.model.OrderDTO;
import com.shop.model.OrderItemDTO;
import com.shop.model.OrderPageItemDTO;

@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private AttachMapper attachMapper; 
	
	@Autowired
	private MemberMapper memberMapper;
	
	@Autowired
	private CartMapper cartMapper;
	
	@Autowired
	private ItemMapper itemMapper;

	@Override
	public List<OrderPageItemDTO> getGoodsInfo(List<OrderPageItemDTO> orders) {

		List<OrderPageItemDTO> result = new ArrayList<OrderPageItemDTO>();		
		
		for(OrderPageItemDTO ord : orders) {
			
			OrderPageItemDTO goodsInfo = orderMapper.getGoodsInfo(ord.getItemId());			

			goodsInfo.setItemCount(ord.getItemCount());	
			
			goodsInfo.initSaleTotal();		
			
			List<AttachImageVO> imageList = attachMapper.getAttachList(goodsInfo.getItemId());
			
			goodsInfo.setImageList(imageList);			
			
			result.add(goodsInfo);			
		}		
		
		return result;
		
	}
	
	@Override
	@Transactional
	public void order(OrderDTO ord) {

		/* 사용할 데이터가져오기 */
			/* 회원 정보 */
			MemberVO member = memberMapper.getMemberInfo(ord.getMemberId());
			/* 주문 정보 */
			List<OrderItemDTO> ords = new ArrayList<>();
			for(OrderItemDTO oit : ord.getOrders()) {
				OrderItemDTO orderItem = orderMapper.getOrderInfo(oit.getItemId());
				// 수량 셋팅
				orderItem.setItemCount(oit.getItemCount());
				// 기본정보 셋팅
				orderItem.initSaleTotal();
				//List객체 추가
				ords.add(orderItem);
			}
			/* OrderDTO 셋팅 */
			ord.setOrders(ords);
			ord.getOrderPriceInfo();
			
		/*DB 주문,주문상품(,배송정보) 넣기*/
			
			/* orderId만들기 및 OrderDTO객체 orderId에 저장 */
			Date date = new Date();
			SimpleDateFormat format = new SimpleDateFormat("_yyyyMMddmm");
			String orderId = member.getMemberId() + format.format(date);
			ord.setOrderId(orderId);
			
			/* db넣기 */
			orderMapper.enrollOrder(ord);		
			for(OrderItemDTO oit : ord.getOrders()) {		
				oit.setOrderId(orderId);
				orderMapper.enrollOrderItem(oit);			
			}

		/* 비용 포인트 변동 적용 */
			
			/* 비용 차감 & 변동 돈(money) Member객체 적용 */
			int calMoney = member.getMoney();
			calMoney -= ord.getOrderFinalSalePrice();
			member.setMoney(calMoney);
			
			/* 포인트 차감, 포인트 증가 & 변동 포인트(point) Member객체 적용 */
			int calPoint = member.getPoint();
			calPoint = calPoint - ord.getUsePoint() + ord.getOrderSavePoint();	
			member.setPoint(calPoint);
				
			/* 변동 돈, 포인트 DB 적용 */
			orderMapper.deductMoney(member);
			
		/* 재고 변동 적용 */
			for(OrderItemDTO oit : ord.getOrders()) {
				/* 변동 재고 값 구하기 */
				ItemVO item = itemMapper.getGoodsInfo(oit.getItemId());
				item.setItemStock(item.getItemStock() - oit.getItemCount());
				/* 변동 값 DB 적용 */
				orderMapper.deductStock(item);
			}			
			
		/* 장바구니 제거 */
			for(OrderItemDTO oit : ord.getOrders()) {
				CartDTO dto = new CartDTO();
				dto.setMemberId(ord.getMemberId());
				dto.setItemId(oit.getItemId());
				
				cartMapper.deleteOrderCart(dto);
			}			
			
		
	}	
	
	
	/* 주문취소 */
	@Override
	@Transactional
	public void orderCancle(OrderCancelDTO dto) {
		
	/* 주문, 주문상품 객체 */
		/*회원*/
			MemberVO member = memberMapper.getMemberInfo(dto.getMemberId());
		/*주문상품*/
			List<OrderItemDTO> ords = orderMapper.getOrderItemInfo(dto.getOrderId());
			for(OrderItemDTO ord : ords) {
				ord.initSaleTotal();
			}
		/* 주문 */
			OrderDTO orw = orderMapper.getOrder(dto.getOrderId());
			orw.setOrders(ords);
			
			orw.getOrderPriceInfo();
			
	/* 주문상품 취소 DB */
			orderMapper.orderCancle(dto.getOrderId());
			
	/* 돈, 포인트, 재고 변환 */
			/* 돈 */
			int calMoney = member.getMoney();
			calMoney += orw.getOrderFinalSalePrice();
			member.setMoney(calMoney);
			
			/* 포인트 */
			int calPoint = member.getPoint();
			calPoint = calPoint + orw.getUsePoint() - orw.getOrderSavePoint();
			member.setPoint(calPoint);
			
				/* DB적용 */
				orderMapper.deductMoney(member);
				
			/* 재고 */
			for(OrderItemDTO ord : orw.getOrders()) {
				ItemVO item = itemMapper.getGoodsInfo(ord.getItemId());
				item.setItemStock(item.getItemStock() + ord.getItemCount());
				orderMapper.deductStock(item);
			}		
		
		
	}	
	
}