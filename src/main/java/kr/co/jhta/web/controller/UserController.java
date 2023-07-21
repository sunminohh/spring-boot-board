package kr.co.jhta.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.jhta.service.UserService;
import kr.co.jhta.vo.User;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;
	
	//  사용자 등록폼 요청과 매핑되는 요청핸들러 메소드
	@GetMapping("/register")
	public String registerform(Model model) {
		
		return "user/registerform";
	}
	
	// 사용자 등록 요청과 매핑되는 요청핸들러 메소드
	@PostMapping("/register")
	public String createUser(String email, String password) {
		userService.createUser(email, password);
		
		return "redirect:/";
	}
	
	
	// 로그인 화면 요청과 매핑되는 요청핸들러 메소드
	@GetMapping("login")
	public String loginform(Model model) {
		
		return "user/loginform";
	}
	
	
	// 내정보보기 화면 요청과 매핑되는 요청핸들러 메소드
	@GetMapping("info")
	public String info(Model model) {
		
		return "user/info";
	}
	
	
	// 내정보 수정화면 요청과 매핑되는 요청핸들러 메소드
	@GetMapping("modify")
	public String modifyform(Model model) {
		
		return "user/modifyform";
	}
	
	// 내정보 수정 요청과 매핑되는 요청핸들러 메소드
	@PostMapping("modify")
	public String modifyUser(Model model) {
		
		return "redirect:info";
	}
}














