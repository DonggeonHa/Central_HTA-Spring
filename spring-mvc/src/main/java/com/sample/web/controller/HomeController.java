package com.sample.web.controller;

import com.sample.service.UserService;
import com.sample.vo.User;
import com.sample.web.form.UserRegisterForm;
import com.sample.web.utils.SessionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/*
	@Controller
		- 클라이언트의 요청을 처리하는 컨트롤러 클래스임을 나타낸다.
		- <context:component-scan /> 태그를 xml에 설정했을 대 자동으로 스프링 컨테이너의 빈으로 등록된다.
 */
@Controller
public class HomeController {

	private static Logger logger = LogManager.getLogger(HomeController.class);

	@Autowired
	private UserService userService;
	/*
		@RequestMapping, @GetMapping, @PostMapping, @PutMapping, @DeleteMapping
			- 요청 URL과 요청핸들러 메소드를 매핑시킨다.
			- 요청 URL은 "/"로 시작한다.(절대경로)
			- @RequestMapping은 메소드, 클래스 두 군데 붙일 수 있고, 나머지는 메소드에만 붙일 수 있다.
			- 다양한 속성값을 가질 수 있다.
				* path 혹은 value
					요청핸들러 메소드와 매핑되는 요청 URL을 지정할 때 사용한다.
					2개 이상의 요청URL과 매핑되는 경우 배열형식으로 요청URL을 정의한다.
					작성예)
						@RequestMapping("/home")
						@RequestMapping(value = "/home")
						@RequestMapping(path = "/home")
						@RequestMapping(value = {"/home"})
						@RequestMapping(path = {"/home"})
						@RequestMapping(value = {"/", "/home", "/main"})
						@RequestMapping(path = {"/", "/home", "/main"})
	 */

	/*
		Model
			- 뷰 객체 혹은 뷰페이지(JSP)에 값을 전달할 떄 사용하는 객체다.
			- 요청핸들러 메소드의 매개변수로 Model객체를 정의하면 handlerAdapter가 요청핸들러 호출할 떄 Model객체를 전달해준다.
			- 요청핸들러 메소드에서 Model객체에 값 혹은 객체를 저장하면 HttpServletRequest객체의 속성으로 옮겨진다.(JSP 기반의 웹 애플리케이션에 한함)
	 */
	@GetMapping(path = {"/", "/home"})
	public String home() {
		logger.info("홈페이지 요청을 처리함");
		// 뷰 페이지에 값 전달하기
		
		return "home"; // /WEB-INF/views/home.jsp 경로에서 "/WEB-INF/views/"와 ".jsp"를 제외한 이름
	}
	/*
		요청핸들러 메소드의 반환값
			- String
				* 내부이동할 JSP페이지의 이름을 반환한다.
					내부이동할 JSP페이지의 이름은 /WEB-INF/views/ 폴더에 있는 jsp파일의 파일명이다.
					내부이동할 JSP페이지가 /WEB-INF/views/home.jsp인 경우는 "home"다.
					내부이동할 JSP페이지가 /WEB-INF/views/board/list.jsp인 경우는 "board/list"다.
					내부이동하는 경로를 적을 때는 "/"로 시작할 필요가 없음
					InternalResourceView, JstlView가 해당 경로의 jsp로 내부이동시킨다.
				* 클라이언트가 재요청할 URL을 반환한다.
					재요청할 URL은 "redirect:"로 시작한다.
					"redirect:list"와  "redirect:/list"는 서로 다른 경로다.
					"redirect:list"는 상대주소표기법으로 작성된 경로다
					"redirect:/list"는 절대주소표기법으로 작성된 경로다.
					RedirectView가 해당 URL을 재요청URL로 클라이언트에게 응답으로 보낸다.
				* 작성예)
					@Controller
					@RequestMapping("/board")
					public class BoardController {
						@GetMapping("/list")
						public String boardList() {
							...
							return "board/list"; // 실제
							
						}
					}
			- VO 객체, List 객체, Map 객체
				* JSP 페이지로 이동하는 것이 아니다.
				* 요청 핸들러 메소드가 반환하는 갑싱 직접 응답으로 클라이언트에게 전달된다.
				* 주로 JSON, XML 형태로 변환되어서 전달된다.
				* 대부분의 경우, 컨트롤러 클래스에 @Controller 대신 @RestController이 지정되어 있다.
				* 작성예)
					@RestController
					@RequestMapping("/board")
					public class BoardController {
						@GetMapping("/detail")
						public Board getDetail() { ... }
					}
	 */

	/**
	 * 회원가입화면으로 내부이동하는 요청핸들러 메소드
	 * @return
	 */
	@GetMapping("/register")
	public String registerform() {
		logger.debug("registerform() 실행 됨");

		logger.info("회원가입폼 요청을 처리함");
		logger.debug("registerform() 종료 됨");
		return "form";
	}

	/*
	@PostMapping("/register")
	public String register(@RequestParam("id") String userId,
						   @RequestParam("password") String userPassword,
						   @RequestParam("passwordConfirm") String userPasswordConfirm,
						   @RequestParam("name") String userName,
						   @RequestParam("email") String userEmail,
						   @RequestParam("phone") String userPhone) {
		logger.debug("registerform() 실행 됨");
		logger.info("회원정보를 등록함");

		logger.debug("registerform() 종료 됨");
		return "redirect:home";
	}
	*/

	@PostMapping("/register")
	public String register(UserRegisterForm userRegisterForm) {
		logger.debug("registerform() 실행 됨");
		logger.info("회원가입정보" + userRegisterForm);

		// User객체를 생성하고, UserRegisterForm의 값을 User객체로 복사한다
		User user = new User();
		BeanUtils.copyProperties(userRegisterForm, user);

		// UserService의 registerUser(user)를 호출해서 업무로직을 수행한다
		userService.registerUser(user);

		logger.info("회원정보 등록 요청을 처리함");
		logger.debug("registerform() 종료 됨");
		return "redirect:home";
	}

	@GetMapping("/login")
	public String login() {
		logger.debug("loginform() 실행 됨");

		logger.info("로그인폼 요청을 처리함");
		logger.debug("loginform() 종료 됨");
		return "loginform";
	}

	@PostMapping("/login")
	public String login(@RequestParam("id") String userId, @RequestParam("password") String userPassword) {
		logger.debug("login() 실행됨");
		logger.info("로그인하는 사용자의 아이디 : " + userId);
		logger.info("로그인하는 사용자의 비밀번호: " + userPassword);


		userService.login(userId, userPassword);
		logger.debug("login() 종료됨");

		return "redirect:home";
	}

	@GetMapping("/logout")
	public String logout() {
		SessionUtils.destroySession();
		return "redirect:home";
	}

}
