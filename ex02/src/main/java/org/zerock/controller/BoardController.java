package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.PageDTO;
import org.zerock.service.BoardService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller // 스프링의 빈으로 인식하게 함
@Log4j
@RequestMapping("/board/*") 
// board로 시작하는 모든 처리를 BoardController가 하도록 지정 함
// BoardController가 속한 org.zerock.controller 패키지는 
// servlet-context.xml에 기본으로 설정되어 있으므로 별도의 설정 필요하지 않음
// Java 설정의 경우에는 componentScan을 이용

// BoardController는 BoardService에 의존적이므로 @AllArgsConstructor를 이용해서 
// 생성자를 만들고 자동으로 주입하도록 함
// 만일 생성자를 만들지 않을 경우에는 @Setter(onMethod_ = {@Autowired})를 이용해서 처리
@AllArgsConstructor
public class BoardController {

	//BoardService 타입의 객체와 같이 연동해야 하므로 의존성에 대한 처리도 같이 진행 함
	private BoardService service;
	
	//@GetMapping("/list")
	//public void list(Model model) {
	//	log.info("list");
	//	model.addAttribute("list", service.getList());
	//}
	
	@GetMapping("/list")
	public void list(Criteria cri, Model model) {
		
		log.info("list: " + cri);

		model.addAttribute("list", service.getList(cri));
		
		int total = service.getTotal(cri);
		
		log.info(total);
		
		model.addAttribute("pageMaker", new PageDTO(cri, total));
	}
	
	@PostMapping("/register")
	public String register(BoardVO board, RedirectAttributes rttr) {
		
		log.info("register: " + board);
		
		service.register(board);
		
		// 새롭게 등록된 게시물의 번호를 같이 전달하기 위해서 RedirectAttributes를 이용
		// 리턴시 redirect: 접두어를 사용하는데 이를 이용하면 스프링MVC가 내부적으로 
		// response.sendRedirect()를 처리해 주기 때문에 편리 함
		rttr.addFlashAttribute("result", board.getBno());
		
		// redirect를 처리할때 RedirectAttributes라는 특별한 타입의 객체를 이용했음
		//addFlashAttribute()의 경우 일회성으로만 데이터를 전달하기 때문에,
		//이런한 처리에 적합 함
		//addFlashAttribute()로 보관된 데이터는 단 한 번만 사용할 수 있게 보관 됨(내부적으로는 HttpSession을 이용해서 처리 함)

		return "redirect:/board/list";
	}
	
	// @ModelAttribute 는 자동으로 Model에 데이터를 지정한 이름으로 담아 줌
	// @ModelAttribute를 사용하지 않아도 Controller에서 화면으로 파라미터가 된 객체는
	// 전달이 되지만, 좀 더 명시적으로 이름을 지정하기 위해 사용 함
	@GetMapping({"/get", "/modify"})
	public void get(@RequestParam("bno") long bno, Model model, @ModelAttribute("cri") Criteria cri) {
		
		log.info("/get or modify");
		
		model.addAttribute("board", service.get(bno));
	}
	
	// 수정 작업을 시작하는 화면의 경우에는 GET 방식으로 접근하지만 실제 작업은 POST 방식으로 동작하므로 
	// @PostMapping을 이용
	// 메서드의 파라미터에 Critera 추가
	@PostMapping("/modify")
	public String modify(BoardVO board, @ModelAttribute("cri") Criteria cri,
			RedirectAttributes rttr) {
		
		log.info("modify: " + board);
		log.info("cri: " + cri);
		// 수정여부를 boolean으로 처리하므로 이를 이용해서 성공한 경우에만
		// RedirectAttributes에 추가 함
		
		if(service.modify(board)) { // true 이면
			rttr.addFlashAttribute("result", "success");
		}
		
		// URL 뒤에 원래의 페이지로 이동하기 위해 pageNum과 amount 값을 가지고 이동하도록 수정
		// 이와 같이 수정하면 수정/삭제 후 기존 사용자가 보던 페이지로 이동 함
		// 수정, 삭제처리는 redirect 방식으로 동작하므로
		// type과 keyword 조건도 redirect시에 포함시켜야 함
		// 리다이렉트는 get방식 이기 때문에 추가적인 파라미터를 처리해야 함
		//rttr.addAttribute("pageNum", cri.getPageNum());
		//rttr.addAttribute("amount", cri.getAmount());
		//rttr.addAttribute("type", cri.getType());
		//rttr.addAttribute("keyword", cri.getKeyword());
		
		//log.info("rttr: " + rttr);
		// rttr에 pageNum, amount, type, keyword를 추가하는 대신에 criteria 클래스에 getListLink를 만들어서 이용하면 더 간단함.
		return "redirect:/board/list" + cri.getListLink();
	}
	
	// 삭제는 반드시 POST로만 처리 함
	@PostMapping("/remove")
	public String remove(@RequestParam("bno") long bno, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr) {
		
		log.info("remove..." + bno);
		
		// true 일 경우
		if(service.delete(bno)) {
			rttr.addFlashAttribute("result", "success");
		}
		
		// URL 뒤에 원래의 페이지로 이동하기 위해 pageNum과 amount 값을 가지고 이동하도록 수정
		// 이와 같이 수정하면 수정/삭제 후 기존 사용자가 보던 페이지로 이동 함
		// 수정, 삭제처리는 redirect 방식으로 동작하므로
		// type과 keyword 조건도 redirect시에 포함시켜야 함
		// 리다이렉트는 get방식 이기 때문에 추가적인 파라미터를 처리해야 함
		//rttr.addAttribute("pageNum", cri.getPageNum());
		//rttr.addAttribute("amount", cri.getAmount());
		//rttr.addAttribute("type", cri.getType());
		//rttr.addAttribute("keyword", cri.getKeyword());
		
		// rttr에 pageNum, amount, type, keyword를 추가하는 대신에 criteria 클래스에 getListLink를 만들어서 이용하면 더 간단함.
		// UriComponentsBuilder로 생성 된 URL은 화면에서도 유용하게 사용될 수 있는데,
		// 주로 JavaScript를 사용할 수 없는 상황에서 링크를 처리해야 하는 상황에서 쓰임
		return "redirect:/board/list" + cri.getListLink();
	}
	
	// get으로 입력 페이지 볼수 있도록 메서드 추가
	// 입력 페이지를 보여주는 역할만을 하기 때문에 별도의 처리가 필요치 않음
	@GetMapping("/register")
	public void register() {
		
	}
}
