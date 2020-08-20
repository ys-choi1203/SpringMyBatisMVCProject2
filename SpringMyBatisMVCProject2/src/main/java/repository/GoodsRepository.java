package repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import model.CartDTO;
import model.GoodsDTO;

public class GoodsRepository {
	@Autowired
	private SqlSession sqlSession;
	private final String namespace="goodsMapper";
	
	public void goodsInsert(GoodsDTO dto) {
		String statement = namespace + ".goodsInsert";
		sqlSession.insert(statement, dto);
	}
	public List<GoodsDTO> getGoodsList(GoodsDTO dto) {
		String statement = namespace + ".getGoodsList";
		return sqlSession.selectList(statement, dto);
	}
	public int getGoodsCount() {
		String statement = namespace + ".getGoodsCount";
		return sqlSession.selectOne(statement);
	}
	public GoodsDTO goodsDetail(GoodsDTO dto) {
		String statement = namespace + ".getGoodsList";
		return sqlSession.selectOne(statement, dto);
	}
	public Integer goodsCartAdd(CartDTO cart) {
		String statement = namespace + ".goodsCartAdd";
		return sqlSession.insert(statement, cart);
	}
	public List<CartDTO> cartList(String userId) {
		String statement = namespace + ".cartList";
		return sqlSession.selectList(statement, userId);
	}
	public void goodsCartRemove(Map<String, Object> condition) {
		String statement = namespace + ".goodsCartRemove";
		sqlSession.delete(statement, condition);
	}

}
