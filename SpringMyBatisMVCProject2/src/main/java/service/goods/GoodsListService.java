package service.goods;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import controller.PageAction;
import model.GoodsDTO;
import model.StartEndPageDTO;
import repository.GoodsRepository;

public class GoodsListService {
	@Autowired
	GoodsRepository goodsRepository;
	
	public void getGoodsList(Model model, Integer page) {
		int limit = 10;
		int limitPage = 10;
		
		Long startRow = ((long)page - 1) * 10 + 1;
		Long endRow = startRow + limit - 1;
		
		GoodsDTO dto = new GoodsDTO();
		dto.setStartEndPageDTO(new StartEndPageDTO());
		dto.getStartEndPageDTO().setStartPage(startRow);
		dto.getStartEndPageDTO().setEndPage(endRow);
		
		List<GoodsDTO> list = goodsRepository.getGoodsList(dto);
		int count = goodsRepository.getGoodsCount();
		
		model.addAttribute("list", list);
		model.addAttribute("count", count);
		
		PageAction pageAction = new PageAction();
		pageAction.page(model, count, limit, limitPage, page, "goodsList?");
	}
}
