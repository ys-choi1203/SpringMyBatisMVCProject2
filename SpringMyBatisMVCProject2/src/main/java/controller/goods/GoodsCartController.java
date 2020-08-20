package controller.goods;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import service.goods.GoodsCartService;

@RequestMapping("cart")
public class GoodsCartController {
	@Autowired
	GoodsCartService goodsCartService;

	@RequestMapping("goodsCartList")
	public String goodsCartList(Model model, HttpSession session) {
		goodsCartService.goodsCartList(model, session);
		return "goodsView/goodsCartList";
	}
	
	@RequestMapping(value = "goodsCartAdd", method = RequestMethod.POST)
	public String goodsCartAdd(@RequestParam(value = "goodsNum") String goodsNum,
			Model model, HttpSession session) {
		goodsCartService.goodsCartAdd(goodsNum, model, session);
		return "board/delPage";
	}
	
	@RequestMapping("goodsCartRemove")
	public String goodsCartRemove(@RequestParam(value = "delete") Long [] cartNums) {
		goodsCartService.goodsCartRemove(cartNums);
		return "redirect:/cart/goodsCartList";
	}
}
