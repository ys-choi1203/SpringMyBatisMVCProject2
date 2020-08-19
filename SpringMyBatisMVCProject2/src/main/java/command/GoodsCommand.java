package command;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class GoodsCommand {
	String goodsNum;
	String goodsName;
	Long goodsPrice;
	String goodsContent;
	MultipartFile [] goodsImage;
}
