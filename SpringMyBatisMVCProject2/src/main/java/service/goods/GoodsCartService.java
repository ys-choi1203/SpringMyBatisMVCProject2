package service.goods;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import model.AuthInfo;
import model.CartDTO;
import model.GoodsDTO;
import repository.GoodsRepository;

@Service
public class GoodsCartService {
	@Autowired
	GoodsRepository goodsRepository;
	
	public void goodsCartAdd(String goodsNum, Model model, HttpSession session) {
		// 장바구니에 상품을 담기 위해서 먼저 상품정보를 가져와야 한다.
		GoodsDTO dto = new GoodsDTO();
		dto.setGoodsNum(goodsNum);
		dto = goodsRepository.goodsDetail(dto);
		
		CartDTO cart = new CartDTO();
		// 대표 이미지만 가져오기 위해서 split을 함
		String [] imgs = dto.getGoodsImage().split("`");
		cart.setGoodsImage(imgs[0]);
		cart.setGoodsName(dto.getGoodsName());
		cart.setGoodsNum(dto.getGoodsNum());
		cart.setGoodsPrice(dto.getGoodsPrice());
		AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
		String userId = authInfo.getUserId();
		cart.setUserId(userId);
		
		Integer i = goodsRepository.goodsCartAdd(cart);
		model.addAttribute("val", i);
	}
	
	public void goodsCartList(Model model, HttpSession session) {
		String userId = ((AuthInfo)session.getAttribute("authInfo")).getUserId();
		List<CartDTO> list = goodsRepository.cartList(userId);
		Long sumTotalPrice = 0L;
		for (CartDTO c : list) {
			sumTotalPrice += c.getTotalPrice();
		}
		model.addAttribute("cartList", list);
		model.addAttribute("sumTotalPrice", sumTotalPrice);
	}

	public void goodsCartRemove(Long[] cartNums) {
		List<Long> cs = new ArrayList<Long>();
		for (long cartNum : cartNums) {
			cs.add(cartNum);
		}
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("cartNums", cs);
		goodsRepository.goodsCartRemove(condition);
	}

	public void goodsCartQtyDown(Long cartNum) {
		goodsRepository.goodsCartQtyDown(cartNum);
	}
	
}
