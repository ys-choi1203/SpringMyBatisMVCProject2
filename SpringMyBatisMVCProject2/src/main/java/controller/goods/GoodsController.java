package controller.goods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;

import command.GoodsCommand;
import service.goods.GoodsWriteService;
import validator.GoodsCommandValidator;

@Controller
@RequestMapping("gd")
public class GoodsController {
	@Autowired
	GoodsWriteService goodsWriteService;

	
	@RequestMapping("goodsList")
	public String goodsList() {
		return "goodsView/goodsList";
	}
	
	@RequestMapping("goodsForm")
	public String goodsForm() {
		return "goodsView/goodsForm";
	}
	
	@RequestMapping("goodsPro")
	public String goodsPro(GoodsCommand goodsCommand, Errors errors) {
		
		new GoodsCommandValidator().validate(goodsCommand, errors);
		if(errors.hasErrors()) {
			return "goodsView/goodsForm";
		}
		goodsWriteService.goodsWrite(goodsCommand);
		return "redirect:/gd/goodsList";
	}
}
