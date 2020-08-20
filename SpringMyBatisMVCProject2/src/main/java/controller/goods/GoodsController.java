package controller.goods;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import command.GoodsCommand;
import service.goods.GoodsCartService;
import service.goods.GoodsDetailService;
import service.goods.GoodsListService;
import service.goods.GoodsWriteService;
import validator.GoodsCommandValidator;

@Controller
@RequestMapping("gd")
public class GoodsController {
	@Autowired
	GoodsWriteService goodsWriteService;
	@Autowired
	GoodsListService goodsListService;
	@Autowired
	GoodsDetailService goodsDetailService;
	
	
	@RequestMapping("goodsList")
	public String goodsList(
			@RequestParam(value="page", defaultValue = "1") Integer page,
			Model model) {
		goodsListService.getGoodsList(model, page);
		return "goodsView/goodsList";
	}
	
	@RequestMapping("goodsForm")
	public String goodsForm(
				@ModelAttribute(value="goodsCommand") GoodsCommand goodsCommand) {
		return "goodsView/goodsForm";
	}
	
	@RequestMapping("goodsPro")
	public String goodsPro(GoodsCommand goodsCommand, 
							Errors errors, HttpServletRequest request) {
		
		new GoodsCommandValidator().validate(goodsCommand, errors);
		if(errors.hasErrors()) {
			return "goodsView/goodsForm";
		}
		goodsWriteService.goodsWrite(goodsCommand, request);
		return "redirect:/gd/goodsList";
	}
	
	@RequestMapping("goodsDetail")
	public String goodsDetail(@RequestParam(value = "goodsNum") String goodsNum,
								Model model) {
		goodsDetailService.goodsDetail(goodsNum, model);
		return "goodsView/goodsDetail";
	}
	
}
