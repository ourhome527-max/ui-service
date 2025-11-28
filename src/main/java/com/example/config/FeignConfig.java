package com.example.config;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartFile;

import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;

@Configuration
public class FeignConfig {

	private final ObjectFactory<HttpMessageConverters> messageConverters;

	public FeignConfig(ObjectFactory<HttpMessageConverters> messageConverters) {
		this.messageConverters = messageConverters;
	}

	@Bean
	public Encoder feignFormEncoder() {
		// SpringEncoder를 감싸서 폼 인코더 생성
		return new SpringFormEncoder(new SpringEncoder(messageConverters));
	}
}