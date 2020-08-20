package service.goods;

import java.io.File;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.AuthInfo;
import model.GoodsDTO;
import repository.GoodsRepository;

@Service
public class GoodsDeleteService {
	@Autowired
	GoodsRepository goodsRepository;
	public void goodsDelete(String goodsNum, HttpSession session) {
		String userId = ((AuthInfo)session.getAttribute("authInfo")).getUserId();
		GoodsDTO dto = new GoodsDTO();
		dto.setUserId(userId);
		dto.setGoodsNum(goodsNum);
		// 파일 정보를 가져오기 위해서
		dto = goodsRepository.goodsDetail(dto);
		Integer i = goodsRepository.goodsDelete(dto);
		if(i >= 1) {
			String path1 = "WEB-INF/view/goodsView/upload";
			String filePath = session.getServletContext().getRealPath(path1);
			
			File file = null;
			String [] files = dto.getGoodsImage().split("`");
			for (String fileName : files) {
				file = new File(filePath+"/"+fileName);
				if(file.exists()) {
					file.delete();
				}
			}
		}
	}

}
