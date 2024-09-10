package com.yedam.mapper;

import java.util.List;

import com.yedam.vo.ReplyVO;

public interface ReplyMapper {
	//댓글목록
	List<ReplyVO> selectList(int bno);
	//삭제
	int deleteReply(int rno);
	//다건삭제.
	int deleteReplys(String[] array);
	//댓글등록
	int addReply(ReplyVO reply);
	//댓글번호조회
	int selectKey();
}
