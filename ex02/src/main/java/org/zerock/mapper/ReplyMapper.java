package org.zerock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;

public interface ReplyMapper {
	
	public int insert(ReplyVO vo);
	
	public ReplyVO read(long rno);
	
	public int delete (long rno);
	
	public int update(ReplyVO vo);
	
	// MyBatis는 두 개 이상 데이터를 파라미터로 전달 받을 때
	// 1) 별도의 객체로 구성하거나
	// 2) Map을 이용하는 방식
	// 3) @Param을 이용해서 이름을 사용하는 방식 이 있다 (가장간단)
	// @Param의 속성값은  MyBatis에서 SQL을 이용할때 #{} 의 이름으로 사용이 가능 함
	public List<ReplyVO> getListWithPaging(
			@Param("cri") Criteria cri,
			@Param("bno") Long bno);
}
 