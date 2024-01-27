package org.zerock.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.Criteria;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardServiceTests {

	@Setter(onMethod_ = {@Autowired})
	private BoardService service;
	
	// BoardService 객체가 제대로 주입이 가능한지 확인하는 작업으로 시작
	//@Test
	//public void testExist() {
		
	//	log.info(service);
	//	assertNotNull(service);
	//}
	
	//@Test
	//public void testRegister() {
		
	//	BoardVO board = new BoardVO();
	//	board.setTitle("새로 작성하는 글");
	//	board.setContent("새로 작성하는 내용");
	//	board.setWriter("newbie");
		
	//	service.register(board);
		
	//	log.info("생성된 게시물의 번호:" + board.getBno());
	//}
	
	@Test
	public void testGetList() {
		
	//	service.getList().forEach(board -> log.info(board));
		
		service.getList(new Criteria(2,10)).forEach(board -> log.info(board));
	}
	
	//@Test
	//public void testGet() {
		
	//	service.get(16L);
	//}
	
	//@Test
	//public void testUpdate() {
		
	//	BoardVO board = service.get(16L);
		
	//	if(board == null) {
	//		return;
	//	}
		
	//	board.setTitle("title update update");
		
	//	log.info("MODIFY RESULT: " + service.modify(board));
	
	//@Test
	//public void testDelete() {
		
		// 게시물 번호의 존재 여부를 확인하고 테스트할 것
	//	log.info("REMOVE RESULT: " + service.delete(16L));
	//}
}
