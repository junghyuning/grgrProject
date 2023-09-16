package com.grgr.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.grgr.dto.Matzib;
import com.grgr.service.InfoService;
import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
@RequestMapping("/info")
public class InformationController {
	
	private final InfoService infoService;
	private final HttpSession session;
	
	/* 맛집정보 검색 */
    @GetMapping("/matzib")
    public String getMatzibList(Model model) {
       
    	
    	String query = (String)session.getAttribute("loginLocation")+"맛집";
        List<Matzib> matzibList = infoService.getMatzibList(query);
        model.addAttribute("matzibList", matzibList);
        return "info/matzibList"; 
    }
}