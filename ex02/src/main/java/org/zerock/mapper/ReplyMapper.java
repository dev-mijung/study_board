package org.zerock.mapper;

import org.zerock.domain.ReplyVO;

public interface ReplyMapper {
	
	public int insert(ReplyVO vo);
	
	public ReplyVO read(long rno);
	
	public int delete (long rno);
	
	public int update(ReplyVO vo);
}
 