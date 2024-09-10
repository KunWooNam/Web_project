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
		
		
		ReplyVO replyVO = new ReplyVO();
		replyVO.setReplyer("user01");
		replyVO.setBoardNo(148);
		replyVO.setReply("댓글 등록 테스트중");
		ReplyService rs = new ReplyServiceImpl();
		
		mapper.addReply(replyVO);
				
	}
}
