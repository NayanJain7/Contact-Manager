package com.smartcontactmanager.nayan.Service;

import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.smartcontactmanager.nayan.message.ReCaptchResponseType;

@Service
public class ReCaptchaValidationService {
	
	@Value("GOOGLE_RECAPTCHA_ENDPOINT")
	private   String GOOGLE_RECAPTCHA_ENDPOINT;
    	
	@Value("RECAPTCHA_SECRET")
	private  String RECAPTCHA_SECRET;

	    public boolean validateCaptcha(String captchaResponse){
	        RestTemplate restTemplate = new RestTemplate();

	        MultiValueMap<String, String> requestMap = new LinkedMultiValueMap<>();
	        requestMap.add("secret", RECAPTCHA_SECRET);
	        requestMap.add("response", captchaResponse);

	        ReCaptchResponseType apiResponse = restTemplate.postForObject(GOOGLE_RECAPTCHA_ENDPOINT, requestMap, ReCaptchResponseType.class);
	        if(apiResponse == null){
	            return false;
	        }

	        return Boolean.TRUE.equals(apiResponse.isSuccess());
	    }

}
