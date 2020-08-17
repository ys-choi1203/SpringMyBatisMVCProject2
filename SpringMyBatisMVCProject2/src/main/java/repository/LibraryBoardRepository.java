package repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.LibraryBoardDTO;

@Repository
public class LibraryBoardRepository {
	@Autowired
	private SqlSession sqlSession;
	private final String namespace="mappers.library.libraryMapper";
	
	public void libraryUpdate(LibraryBoardDTO dto) {
		String statement = namespace+ ".libraryUpdate";
		sqlSession.update(statement, dto);
	}
	public void libraryInsert(LibraryBoardDTO dto) {
		String statement = namespace+ ".libraryInsert";
		sqlSession.insert(statement, dto);
	}
	public List<LibraryBoardDTO> getLibraryList(
			LibraryBoardDTO dto){
		String statement = namespace+ ".getLibraryList";
		return sqlSession.selectList(statement, dto);
	}
	public Integer getLibraryCount() {
		String statement = namespace+ ".getLibraryCount";
		return sqlSession.selectOne(statement);
	}
}
