package repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.BoardDTO;
@Repository
public class BoardRepository {
	@Autowired
	private SqlSession sqlSession;
	
	private final String namespace="mappers.board.boardMapper";
	public Integer boardDelete(BoardDTO board) {
		String statement = namespace+".boardDelete";
		return sqlSession.delete(statement, board);
	}
	public void boardUpdate(BoardDTO board) {
		String statement = namespace+".boardUpdate";
		sqlSession.update(statement, board);
	}
	public void insertBoard(BoardDTO dto) {
		String statement = namespace+".insertBoard";
		sqlSession.insert(statement, dto);
	}
	public List<BoardDTO> getBoardList(BoardDTO boardDTO){
		String statement = namespace+".getBoardList";
		return sqlSession.selectList(statement, boardDTO);
	}
	public Integer getBoardCount() {
		String statement = namespace+".getBoardCount";
		return sqlSession.selectOne(statement);
	}
}
