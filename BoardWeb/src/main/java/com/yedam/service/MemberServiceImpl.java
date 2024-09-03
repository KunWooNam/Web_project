package com.yedam.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.yedam.common.DataSource;
import com.yedam.mapper.MemberMapper;
import com.yedam.vo.MemberVO;

public class MemberServiceImpl implements MemberService{

	SqlSession sqlSession = DataSource.getInstance().openSession(true);
	MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
	
	@Override
	public List<MemberVO> getmembers() {
		return mapper.memberList();
	}

	@Override
	public boolean addMember(MemberVO member) {
		if(mapper.selectMember(member.getMemberId()) != null) {
			return false; //이미 존재하는 아이디
		}
		return mapper.insertMember(member) == 1;		
	}

	@Override
	public boolean removeMember(String memberId) {
		if(mapper.selectMember(memberId) == null) {
			return false; //없는 아이디
		}
		return mapper.deleteMember(memberId) == 1;
	}

	@Override
	public boolean modifyMember(MemberVO member) {
		if(mapper.selectMember(member.getMemberId()) == null) {
			return false; //없는 아이디
		}
		return mapper.updateMember(member) == 1;
	}

	@Override
	public MemberVO getMember(String memberId) {
		if(mapper.selectMember(memberId) != null) {
			return mapper.selectMember(memberId);
		}
		return null;
	}
}
