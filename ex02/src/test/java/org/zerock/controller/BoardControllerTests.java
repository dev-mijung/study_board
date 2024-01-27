package org.zerock.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@Log4j

// Test for Controller
// Servlet의 ServletContext를 이용하기 위해
// 스프링에서는 WebApplicationContext라는 존재를 이용하기 위해
@WebAppConfiguration

public class BoardControllerTests {
	
	@Setter(onMethod_ = @Autowired)
	private WebApplicationContext ctx;
	
	// 가짜 mvc라고 생각하면 됨
	// 가짜로 URL과 파라미터 등을 브라우저에서 사용하는 것 처럼 만들어서 Controller를 실행 해 볼 수 있음
	private MockMvc mockMvc;  
	
	@Before // import 할때 JUnit을 이용해야 함, Before가 적용된 메서드는 모든 테스트 전에 매번 실행 됨
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	
	// Get Test
	// testList()는 MockMvcRequestBuilders라는 존재를 이용해서 GET방식의 호출을함
	// 이후에는 BoardController의 getList()에서 반환된 결과를 이용해서 Model에 어던 데이터들이 담겨 있는지 확인 함
	//	Tomcat을 통해서 실행되는 방식이 아니므로 기존의 테스트 코드를 실행하는 것과 동일하게 실행 함
	@Test
	public void testList() throws Exception {
		
		log.info(
				mockMvc.perform(MockMvcRequestBuilders.get("/board/list")
						.param("pageNum", "2")
						.param("amount", "50"))
				.andReturn()
				.getModelAndView()
				.getModelMap());
	}
	
	// Post Test
	// MockMvcRequestBuilders의 post()를 이용하면 POST 방식으로 데이터를 전달할 수 있고
	//param()을 이용해서 전달해야 하는 파라미터들을 지정할 수 있음(<input>태그와 유사한 역할)
	//이러한 방식으로 코드를 작성하면 최초 작성 시에는 일이 많도고 느껴지지만 
	//매번 입력할 필요가 없기 때문에 오류가 발생하거나 수정하는 경우 반복적인 테스트가 수월해짐
	//@Test
	//public void testRegister() throws Exception {
		
	//	String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/board/register")
	//			.param("title", "new title")
	//			.param("content", "new content")
	//			.param("writer", "new writer")
	//			).andReturn().getModelAndView().getViewName();
		
	//	log.info(resultPage);
	//}
	
	//@Test
	//public void testGet() throws Exception {
		
	//	log.info(mockMvc.perform(MockMvcRequestBuilders
	//			.get("/board/get")
	//			.param("bno","30"))
	//			.andReturn()
	//			.getModelAndView()
	//			.getModelMap());
	//}
	
	//@Test
	//public void testModify() throws Exception {
		
	//	String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/board/modify")
	//			.param("bno", "30")
	//			.param("title", "modify title")
	//			.param("content", "modify content")
	//			.param("writer", "modify writer"))
	//			.andReturn().getModelAndView().getViewName();
		
	//	log.info(resultPage);
	//}
	
	//@Test
	//public void testRemove() throws Exception {
		
		// 삭제전 데이터베이스에 게시물 번호 확인할 것
	//	String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/board/remove")
	//			.param("bno", "30"))
	//			.andReturn().getModelAndView().getViewName();
		
	//	log.info(resultPage);
	//}
}
