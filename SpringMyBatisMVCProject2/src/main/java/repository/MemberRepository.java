package repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.MemberDTO;

@Repository
public class MemberRepository {
	@Autowired
	private SqlSession sqlSession;
	private final String namespace="mappers.member.memberMapper";
	private String statement;
	public void memberDelete(String userId) {
		statement = namespace + ".memberDelete";
		sqlSession.update(statement, userId);
	}
	public void passwordUpdate(MemberDTO dto) {
		statement = namespace + ".passwordUpdate";
		sqlSession.update(statement, dto);
	}
	public void memberUpdate(MemberDTO dto) {
		statement = namespace + ".memberUpdate";
		sqlSession.update(statement, dto);
	}
	public int getMemberCount() {
		statement = namespace + ".getMemberCount";
		return sqlSession.selectOne(statement);
	}
	public List<MemberDTO> getMemberList(MemberDTO memberDTO) {
		statement = namespace + ".selectMember";
		return sqlSession.selectList(statement, memberDTO);
	}
	public Integer joinOkUpdate(MemberDTO memberDTO) {
		statement = namespace + ".joinOkUpdate";
		return sqlSession.update(statement, memberDTO);
	}
	public MemberDTO selectByMember(MemberDTO memberDTO) {
		String statement = namespace + ".selectMember";
		return sqlSession.selectOne(statement, memberDTO);
	}
	public Integer insertMember(MemberDTO memberDTO) {
		try {
			statement = namespace + ".insertMember";
			return sqlSession.insert(statement, memberDTO);
		}catch(Exception e) {
			return null;
		}
	}
}
