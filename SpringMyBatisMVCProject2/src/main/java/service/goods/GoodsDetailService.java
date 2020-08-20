package service.goods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import model.GoodsDTO;
import repository.GoodsRepository;

public class GoodsDetailService {
	@Autowired
	GoodsRepository goodsRepository;
	
	public void goodsDetail(String goodsNum, Model model) {
		GoodsDTO dto = new GoodsDTO();
		dto.setGoodsNum(goodsNum);
		dto = goodsRepository.goodsDetail(dto);
		model.addAttribute("goods", dto);
	}

}
