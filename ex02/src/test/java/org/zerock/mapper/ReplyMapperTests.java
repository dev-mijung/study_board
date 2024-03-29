package org.zerock.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
// Java Config
// @ContextConfiguration(classes = {org.zerock.config.RootConfig.class})
@Log4j
public class ReplyMapperTests {
	
	@Setter(onMethod_ = @Autowired)
	private ReplyMapper mapper;
	
	// 테스트 전에 해당 번호의 게시물이 존재하는지 반드시 확인
	private Long[] bnoArr = { 4063784L, 4063783L, 4063781L, 4063779L, 
			4063778L, 4063777L, 4063776L, 4063775L, 4063773L};  
			
	//@Test
	//public void testMapper() {
		
	//	log.info(mapper);
	//}
	
	//@Test
	//public void testCreate() {
		
	//	IntStream.rangeClosed(1, 10).forEach(i -> {
	//		ReplyVO vo = new ReplyVO();
			
			// 게시물의 번호
	//		vo.setBno(bnoArr[i % 5]);
	//		vo.setReply("댓글 테스트 " + i);
	//		vo.setReplyer("replyer" + i);
			
	//		mapper.insert(vo);
	//	});
	
	//@Test
	//public void testRead() {
		
	//	Long targetNo = 7982L;
		
	//	ReplyVO vo = mapper.read(targetNo);
		
	//	log.info(vo);
		
	//}
	
	//@Test
	//public void testDelete() {
		
	//	Long targetNo = 7982L;
		
	//	log.info(mapper.delete(targetNo)); // INFO : org.zerock.mapper.ReplyMapperTests - 1
	//}
	
	//@Test
	//public void testUpdate() {
		
	//	long targetNo = 7981L;
		
	//	ReplyVO vo = mapper.read(targetNo);
		
	//	vo.setReply("Update Reply");
		
	//	int count = mapper.update(vo);
		
	//	log.info("UPDATE COUNT : " + count); // org.zerock.mapper.ReplyMapperTests - UPDATE COUNT : 1
	//}
	
	@Test
	public void testList() {
		Criteria cri = new Criteria();
		
		// 4063778L
		List<ReplyVO> replies = mapper.getListWithPaging(cri, bnoArr[4]);
		
		replies.forEach(reply -> log.info(reply));
	}
}
