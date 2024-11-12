package com.lyj.securitydomo;


import com.lyj.securitydomo.controller.RequestController;
import com.lyj.securitydomo.domain.Post;
import com.lyj.securitydomo.domain.Request;
import com.lyj.securitydomo.domain.RequestStatus;
import com.lyj.securitydomo.domain.User;
import com.lyj.securitydomo.repository.*;
import com.lyj.securitydomo.service.RequestService;
import groovy.util.logging.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Date;

@SpringBootTest
@Transactional
@Rollback(false)
@Log4j2
public class RequestControllerTest {

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private RequestController requestController;

    @Autowired
    private RequestService requestService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private RequestStatusRepository requestStatusRepository;


    @Test
    public void testApplyForm() {

    User user = userRepository.findById(1L).orElseGet(() -> {
        User newUser = new User();
        newUser.setUsername("testUser");
        newUser.setPassword("password");
        newUser.setEmail("abc@naver.com");
        newUser.setRole("USER");
//        newUser.setBirthDate(LocalDate.parse("2024-11-07"));
        return userRepository.save(newUser);
    });

    Post post = postRepository.findById(1L).orElseGet(() -> {
        Post newPost = new Post();
        newPost.setTitle("Test Title");
        newPost.setContentText("Test Content");
        return postRepository.save(newPost);
    });

        RequestStatus status = requestStatusRepository.findById(2L).orElseGet(() -> {
            RequestStatus newStatus = new RequestStatus();
            newStatus.setStatus("완료");
            return requestStatusRepository.save(newStatus);
        });

        Request request = new Request();
        request.setContent("테스트참여글9");
        request.setRegDate(new Date());
        request.setUser(user);
        request.setPost(post);
        request.setRequestStatus(status);
        requestService.saveRequest(request);

    }

}
