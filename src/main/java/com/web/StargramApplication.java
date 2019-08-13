package com.web;

import com.web.domain.User;
import com.web.property.FileUploadProperties;
import com.web.repository.UserRepository;
import com.web.resolver.UserArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.LocalDateTime;
import java.util.List;


@EnableConfigurationProperties({
		FileUploadProperties.class
})
@SpringBootApplication
public class StargramApplication implements WebMvcConfigurer {

	@Autowired
	private UserArgumentResolver userArgumentResolver;

	public static void main(String[] args) {
		SpringApplication.run(StargramApplication.class, args);
	}

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(userArgumentResolver);
	}

	@Autowired
	private PasswordEncoder passwordEncoder;


//	@Bean
//	public CommandLineRunner runner(UserRepository userRepository) {
//		return (args) -> {
//			User user = userRepository.save(User.builder()
//					.name("havi")
//					.password(passwordEncoder.encode("test"))
//					.email("havi@gmail.com")
//					.createdDate(LocalDateTime.now())
//					.build());
//
//		};
//	}
}