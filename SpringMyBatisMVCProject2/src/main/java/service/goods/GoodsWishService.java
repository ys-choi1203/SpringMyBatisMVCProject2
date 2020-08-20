package service.goods;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import model.AuthInfo;
import model.GoodsDTO;
import model.WishDTO;
import repository.GoodsRepository;

@Service
public class GoodsWishService {
	@Autowired
	GoodsRepository goodsRepository;
	
	public void goodsWishAdd(String goodsNum,
			HttpSession session,Model model) {
		String userId = ((AuthInfo)session.getAttribute("authInfo")).getUserId();
		WishDTO dto = new WishDTO();
		dto.setGoodsNum(goodsNum);
		dto.setUserId(userId);
		Integer i = goodsRepository.wishAdd(dto);
		model.addAttribute("val", i);
	}
	public void goodsWishList(Model model, HttpSession session) {
		String userId = ((AuthInfo)session.getAttribute("authInfo")).getUserId();
		List<GoodsDTO> list = goodsRepository.goodsWishList(userId);
		model.addAttribute("wishList", list);
	}

}
