package com.yedam.common;



import org.apache.ibatis.session.SqlSession;

import com.yedam.mapper.ReplyMapper;
import com.yedam.service.ReplyService;
import com.yedam.service.ReplyServiceImpl;
import com.yedam.vo.ReplyVO;

public class AppTest {
	public static void main(String[] args) {
		SqlSession sqlSession = DataSource.getInstance().openSession(true);
		ReplyMapper mapper = sqlSession.getMapper(ReplyMapper.class);
		
		SearchDTO search = new SearchDTO();
		search.setBoardNo(521);
		search.setPage(1);
		
		mapper.selectListPaging(search).forEach(reply -> System.out.println(reply.toString()));
	}
}
