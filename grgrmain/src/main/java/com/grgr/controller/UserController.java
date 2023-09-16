package com.grgr.controller;

import java.util.Random;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.grgr.dto.UserVO;
import com.grgr.mapper.UserMapper;
import com.grgr.service.UserService;
import com.grgr.service.UserServiceImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")

public class UserController {

	private final UserService userService;
	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private BCryptPasswordEncoder pwEncoder;
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	/* 회원가입페이지 이동 */
	@GetMapping("/register")
	public void registerPageGET() {
		logger.info("회원가입 페이지 진입");

	}

	/* 회원가입 */ /* 추후에 수정 필요(회원가입 페이지 + 로그인 페이지 통합) */
	@PostMapping("/join")
	public String joinPOST(UserVO user) throws Exception {
		logger.info("가입 진행");

		String rawPw = ""; // 인코딩 전 비밀번호
		String encodePw = ""; // 인코딩 후 비밀번호

		rawPw = user.getUserPw(); // 비밀번호 데이터 얻음
		encodePw = pwEncoder.encode(rawPw); // 비밀번호 인코딩
		user.setUserPw(encodePw); // 인코딩된 비밀번호를 user 객체에 다시 저장

		/* 회원가입 쿼리 실행 */
		userService.userJoin(user);
		return "redirect:/main";
	}

	/* 아이디 중복 검사 */
	@PostMapping("/userIdChk")
	@ResponseBody
	public String userIdChkPOST(String userId) throws Exception {

		logger.info("아이디 중복 검사 진입");

		int result = userService.idCheck(userId);

		logger.info("결과값 : " + result);

		if (result != 0) {
			return "fail";
		} else {
			return "success";
		}

	} // userIdChkPOST() 종료

	/* 닉네임 중복 검사 */
	@PostMapping("/userNickNameChk")
	@ResponseBody
	public String userNickNameChkPOST(String nickName) throws Exception {

		logger.info("닉네임 중복 검사 진입");

		int result = userService.nickNameCheck(nickName);

		logger.info("결과값 : " + result);

		if (result != 0) {
			return "fail";
		} else {
			return "success";
		}

	} // userNickNameChkPOST() 종료

	@GetMapping("/emailCheck")
	@ResponseBody
	public String emailCheckGET(String email) throws Exception {

		/* 뷰(view)로부터 넘어온 데이터 확인 */
		logger.info("이메일 데이터 전송 확인");
		logger.info("인증번호 : " + email);

		/* 인증번호(난수) 생성 */
		Random random = new Random();
		int checkNum = random.nextInt(888888) + 111111;
		logger.info("인증번호 " + checkNum);

		/* 이메일 보내기 */
		String setFrom = "shon5982@gmail.com";
		String toMail = email;
		String title = "회원가입 인증 이메일 입니다.";
		String content = "홈페이지를 방문해주셔서 감사합니다." + "<br><br>" + "인증 번호는 " + checkNum + "입니다." + "<br>"
				+ "해당 인증번호를 인증번호 확인란에 기입하여 주세요.";

		try {

			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
			helper.setFrom(setFrom);
			helper.setTo(toMail);
			helper.setSubject(title);
			helper.setText(content, true);
			mailSender.send(message);

		} catch (Exception e) {
			e.printStackTrace();
		}

		String num = Integer.toString(checkNum);
		return num;

	}

	/* 로그인 페이지 이동 */
	@GetMapping("/login")
	public void loginPageGET() {
		logger.info("로그인 페이지 진입");
	}

	/* 로그인 */
	@PostMapping("/login")
	public String loginPOST(HttpSession session, UserVO user, RedirectAttributes rttr) throws Exception {
		logger.info("로그인  진입");
		String rawPw = "";
		String encodePw = "";

		UserVO loginSuccessUser = userService.userLogin(user);

		if (loginSuccessUser != null) { // 일치하는 아이디 존재

			rawPw = user.getUserPw(); // 사용자가 제출한 비밀번호
			encodePw = loginSuccessUser.getUserPw(); // 데이터베이스에 저장한 인코딩된 비밀번호

			if (true == pwEncoder.matches(rawPw, encodePw)) { // 일치여부 판단
				loginSuccessUser.setUserPw(""); // 인코딩된 비밀번호 정보 지움
				session.setAttribute("loginId", loginSuccessUser.getUserId());
				session.setAttribute("loginNickname", loginSuccessUser.getNickName());
				session.setAttribute("loginUno", loginSuccessUser.getUno());
				session.setAttribute("loginActive", loginSuccessUser.getActive());
				session.setAttribute("loginUserStatus", loginSuccessUser.getUserStatus());
				session.setAttribute("loginLastLogin", loginSuccessUser.getLastLogin());
				session.setAttribute("loginLoc", loginSuccessUser.getUserLoc());
				return "redirect:/main"; // 메인페이지 이동
			} else {
				rttr.addFlashAttribute("result", 0);
				System.out.println("로그인 오류");
				return "redirect:/user/login"; // 로그인 페이지로 이동
			}

		} else { // 일지하는 아이디가 존재하지 않을 시 (로그인 실패)
			rttr.addFlashAttribute("result", 0);
			return "redirect:/user/login"; // 로그인 페이지로 이동
		}

	}

	/* 아이디 찾기 */
	@GetMapping("/find-id")
	public String findIdByNameAndEmailPageGET() {
		return "user/find-id"; // 아이디 찾기 페이지로 이동하는 뷰 이름
	}

	@PostMapping("/find-id")
	@ResponseBody
	public String findIdByNameAndEmailPOST(String name, String email) {
		String userId = userService.findUserIdByNameAndEmail(name, email);
		if (userId != null) {
			return userId;
		} else {
			return "not-found";
		}
	}

	/* 비밀번호 찾기 */
	@GetMapping("/forgot-password")
	public String showForgotPasswordPage() {
		return "user/forgot-password";
	}

	@PostMapping("/forgot-password")
	public String sendResetPasswordEmail(@RequestParam("userId") String userId, @RequestParam("email") String email,
			RedirectAttributes redirectAttributes) {

		UserVO user = userService.findUserByIdAndEmail(userId, email);

		if (user != null) {
			String newPassword = generateRandomPassword(); // 임시 비밀번호 생성
			String encryptedPassword = encodePassword(newPassword); // 비밀번호 암호화
			user.setUserPw(encryptedPassword);
			userService.updateUserPassword(user);

			String emailContent = "임시 비밀번호: " + newPassword; // 이메일 내용
			sendEmail(email, "비밀번호 초기화", emailContent);

			redirectAttributes.addFlashAttribute("successMessage", "임시 비밀번호가 이메일로 발송되었습니다.");
		} else {
			redirectAttributes.addFlashAttribute("errorMessage", "일치하는 사용자 정보가 없습니다.");
		}

		return "redirect:/user/login";
	}

	private void sendEmail(String to, String subject, String content) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(to);
		message.setSubject(subject);
		message.setText(content);
		mailSender.send(message);
	}

	private String generateRandomPassword() {
		// 임시 비밀번호 생성 로직 구현
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		StringBuilder newPassword = new StringBuilder();
		int length = 10; // 임시 비밀번호의 길이
		for (int i = 0; i < length; i++) {
			int index = (int) (Math.random() * characters.length());
			newPassword.append(characters.charAt(index));
		}
		return newPassword.toString();
	}

	private String encodePassword(String password) {
		// BCryptPasswordEncoder 사용하여 password를 암호화하는 로직
		return pwEncoder.encode(password);
	}

	/* Reverse Geocoder + NaverMaps API */
	@PostMapping("/getAddress")
	@ResponseBody
	public String getAddressFromCoordinates(HttpSession session, @RequestParam("latitude") String latitude,
			@RequestParam("longitude") String longitude) {

		String loginId = (String) session.getAttribute("loginId");
		String combinedAddress = userService.getAddressFromCoordinates(loginId, latitude, longitude);
		session.setAttribute("loginLocation", combinedAddress);

		return combinedAddress;
	}

	/* 메인페이지 로그아웃 */
	@GetMapping("/logout")
	public String logoutMainGET(HttpServletRequest request) throws Exception {
		logger.info("logoutMainGET 메서드 진입");

		HttpSession session = request.getSession();

		session.invalidate();

		return "redirect:/main";
	}
}
