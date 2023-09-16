package com.grgr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.grgr.dto.Matzib;
import com.grgr.dto.MatzibResponse;

import lombok.RequiredArgsConstructor;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "https://dapi.kakao.com/v2/local/search/keyword.json")
@Service
@RequiredArgsConstructor
public class InfoServiceImpl implements InfoService {

	
	@Override
	public List<Matzib> getMatzibList(String query) {
	    RestTemplate restTemplate = new RestTemplate();
	    List<Matzib> matzibList = new ArrayList<>();

	    
	    for (int page = 1; page <=4; page++) {
	        // Kakao API 호출 URI 생성
	        URI uri = UriComponentsBuilder.fromUriString("https://dapi.kakao.com/v2/local/search/keyword.json")
	                .queryParam("query", query)
	                .queryParam("size", 15)
	                .queryParam("sort", "accuracy")
	                .queryParam("category_group_code", "FD6")
	                .queryParam("page", page)
	                .encode()
	                .build()
	                .toUri();

	        // Kakao API 헤더 설정
	        String kakaoApiKey = "0fb3ecb8e0027e1631f8bac23640202b";
	        HttpHeaders headers = new HttpHeaders();
	        headers.set("Authorization", "KakaoAK " + kakaoApiKey); // API 키 설정
	        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

	        // Kakao API 호출
	        RequestEntity<Void> requestEntity = new RequestEntity<>(headers, HttpMethod.GET, uri);
	        ResponseEntity<MatzibResponse> responseEntity = restTemplate.exchange(requestEntity, MatzibResponse.class);
	        MatzibResponse matzibResponse = responseEntity.getBody();

	        if (matzibResponse != null) {
	            matzibList.addAll(matzibResponse.getDocuments());
	        }
	    }

	    return matzibList;
	}

}