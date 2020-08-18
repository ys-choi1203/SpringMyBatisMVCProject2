package repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import model.ADTO;
import model.BDTO;
import model.CDTO;

public class DominoRepository {
	@Autowired
	private SqlSession sqlSession;
	private final String namespace = "DominoMapper";
	public List<ADTO> selectA() {
		String statement = namespace + ".selectA";
		return sqlSession.selectList(statement);
	}
	public List<BDTO> selectB(Integer a1) {
		String statement = namespace + ".selectB";
		return sqlSession.selectList(statement, a1);
	}
	public List<CDTO> selectC(BDTO bb) {
		String statement = namespace + ".selectC";
		return sqlSession.selectList(statement, bb);
	}
}
