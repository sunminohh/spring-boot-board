package kr.co.jhta.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.jhta.service.BoardService;
import kr.co.jhta.vo.Board;
import kr.co.jhta.vo.User;
import kr.co.jhta.web.form.ModifyBoardForm;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

	private final BoardService boardService;
	
	// 새 게시글 등록화면 요청과 매핑되는 요청핸들러 메소드
	@GetMapping("/register")
	@PreAuthorize("isAuthenticated()")
	public String form(Model model) {
		
		return "board/form";
	}
	
	// 새 게시글 등록요청과 매핑되는 요청핸들러 메소드
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/register")
	public String createBoard(@AuthenticationPrincipal User user, Board board) {
		board.setUser(user);  // 유저넘버 획득 
		boardService.createBoard(board);
		
		return "redirect:list";
	}
	
	// 최근 게시글 목록(가장 최근에 등록된 게시글 10개)요청과 매핑되는 요청핸들러 메소드
	@GetMapping("/list")
	public String getBoards(@RequestParam(name = "page", required = false, defaultValue = "1") int page,
						Model model) {

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("page", page);
		
		Map<String, Object> result = boardService.getBoards(param);
		
		model.addAttribute("boardList", result.get("boardList"));
		model.addAttribute("pagination", result.get("pagination"));
		
		return "board/list";
	}
	
	// 게시글 상세정보 화면 요청과 매핑되는 요청핸들러 메소드
	@GetMapping("/detail")
	@PreAuthorize("isAuthenticated()")
	public String getBoard(@RequestParam("no") int boardNo, Model model) {
		Board board = boardService.getBoard(boardNo);
		model.addAttribute("board", board);
		
		return "board/detail";
	}
	
	// 게시글 삭제 요청과 매핑되는 요청핸들러 메소드
	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("/delete")
	public String deleteBoard(@RequestParam("no") int boardNo, Model model) {
		boardService.deleteBoard(boardNo);
		
		return "redirect:list";
	}
	
	// 게시글 수정화면 요청과 매핑되는 요청핸들러 메소드
	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("/modify")
	public String modifyform(@RequestParam("no") int boardNo, Model model) {
		Board board = boardService.getBoard(boardNo);
		model.addAttribute("board", board);
		
		return "board/modifyform";
	}
	
	// 게시글 수정 요청과 매핑되는 요청핸들러 메소드 
	@PreAuthorize("hasRole('ROLE_USER')")
	@PostMapping("/modify")
	public String modifyBoard(ModifyBoardForm form) {
		boardService.modifyBoard(form);
		
		return "redirect:list";
	}
}












