package com.web.config;

import com.web.domain.User;
import com.web.repository.UserRepository;
import com.web.resolver.UserArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.LocalDateTime;
import java.util.List;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Bean
    public CommonsMultipartResolver multipartResolver(){
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
        commonsMultipartResolver.setDefaultEncoding("UTF-8");
//        commonsMultipartResolver.setMaxUploadSizePerFile(5 * 1024 * 1024); //5 * 1024 * 1024 (5mb)
        return commonsMultipartResolver;
    }

    @Autowired
    private UserArgumentResolver userArgumentResolver;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(userArgumentResolver);
    }

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Bean
    public CommandLineRunner runner(UserRepository userRepository) {
        return (args) -> {
            User user = userRepository.save(User.builder()
                    .name("havi")
                    .password(passwordEncoder.encode("test"))
                    .email("havi@gmail.com")
                    .createdDate(LocalDateTime.now())
                    .build());

        };
    }

}
