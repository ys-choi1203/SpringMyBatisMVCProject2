package model;

import lombok.Data;

@Data
public class CartDTO {
	Long cartNum;
	String goodsNum;
	String userId;
	String goodsName;
	Long goodsPrice;
	String goodsImage;
	Long qty;
	Long totalPrice;
}
