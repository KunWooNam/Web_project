package com.yedam.mapper;

import java.util.List;

import com.yedam.common.SearchDTO;
import com.yedam.vo.ReplyVO;

public interface ReplyMapper {
	//댓글목록
	List<ReplyVO> selectList(int bno);
	List<ReplyVO> selectListPaging(SearchDTO search); //페이지당 건수 출력.
	//삭제
	int deleteReply(int rno);
	//다건삭제.
	int deleteReplys(String[] array);
	//댓글등록
	int addReply(ReplyVO reply);
	//댓글번호조회
	int selectKey();
	//댓글건수반환.
	int selectReplyCount(int bno);
}
