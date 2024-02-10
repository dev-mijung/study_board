package org.zerock.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class ReplyServiceImpl implements ReplyService {

	@Override
	public int insert(ReplyVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ReplyVO read(long rno) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(long rno) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(ReplyVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ReplyVO> getListWithPaging(Criteria cri, Long bno) {
		// TODO Auto-generated method stub
		return null;
	}

}
