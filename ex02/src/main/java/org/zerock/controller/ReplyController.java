/**
 * REST 방식으로 동작하는 URL 설계시 PK를 기준으로 작성하는 것이 좋음
 * PK만으로 조회, 수정, 삭제가 가능하기 때문
 * 댓글의 목록은 PK를 사용할 수 없기 때문에 파라미터로 필요한 게시물의 번호(bno)와페이지 번호(page) 정보들을
 * URL 에서 표현하는 방식을 사용
 * 등록/POST : /replies/new
 * 조회/GET : /replies/:rno
 * 삭제/DELETE : /replies/:rno
 * 수정/PUT or PATCH : /replies/:rno
 * 페이지/GET : /replies/pages/:bno/:page
 */
package org.zerock.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.ReplyVO;
import org.zerock.service.ReplyService;

/*import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.ReplyVO;
import org.zerock.service.ReplyService;*/

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@RequestMapping("/replies/")
@RestController
@Log4j
// @Setter주입을 이용하거나 아래의 코드와 같이
// @AllArgsConstructor 한 후에
// private ReplyService service
// 생성자를 만들어서 사용(스프링 4.3이상)
@AllArgsConstructor
public class ReplyController {
	
	private ReplyService service;
	
	// @PostMapping으로 POST 방식으로만 처리하게 함
	// consumes, produces를 이용해서 JSON 방식의 데이터만 처리하도록 하고
	// 문자열을 반환하도록 함 
	// 파라미터는 @RequestBody를 적용해서 JSON 데이터를 ReplyVO 타입으로 
	// 변환하도록 지정 
	@PostMapping(value = "/new",
			consumes = "application/json",
			produces = { MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> create(@RequestBody ReplyVO vo){
		
		log.info("Reply VO: " + vo);
		
		int insertCount = service.register(vo);
		
		log.info("Reply INSERT COUNT: " + insertCount);
		
		return insertCount == 1 ? new ResponseEntity<>("success", HttpStatus.OK)
		: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
