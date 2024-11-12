package com.lyj.securitydomo.service;

import com.lyj.securitydomo.domain.Post;
import com.lyj.securitydomo.domain.Request;
import com.lyj.securitydomo.domain.RequestStatus;
import com.lyj.securitydomo.domain.User;
import com.lyj.securitydomo.dto.RequestDTO;
import com.lyj.securitydomo.repository.PostRepository;
import com.lyj.securitydomo.repository.RequestRepository;
import com.lyj.securitydomo.repository.RequestStatusRepository;
import com.lyj.securitydomo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RequestServiceImpl implements RequestService {
    private final RequestRepository requestRepository;
    private final RequestStatusRepository requestStatusRepository;
    private final UserRepository userRepository; //User 저장소
    private final PostRepository postRepository;  //Post 저장소

    @Override
    public List<RequestDTO> getRequests(){
        return requestRepository.findAllRequestWithUserAndPostContent();
    }

    @Override
    public void saveRequest(Request request) {
        requestRepository.save(request);
    }

    @Override
    public void saveRequest(long postId, String title, String content){
        // 로그인한 사용자 정보 조회
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName(); // 로그인한 사용자의 이름
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 존재하지 않습니다."));

        // 게시물 조회
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시물이 존재하지 않습니다."));

        // "진행중" 상태 조회
        RequestStatus inProgressStatus = requestStatusRepository.findById(1L) // 1L은 "진행중" 상태 ID
                .orElseThrow(() -> new IllegalArgumentException("진행중 상태가 없습니다."));

        // Request 객체 생성 및 설정
        Request request = new Request();
        request.setUser(user);
        request.setPost(post);
        request.setTitle(title);
        request.setContent(content);
        request.setRequestStatus(inProgressStatus);

        // Request 저장
        requestRepository.save(request);
    }

    @Override
    public void deleteRequest(Long requestId) {
        requestRepository.deleteById(requestId);
    }

    @Override
    public List<RequestDTO> getRequestsByPostId(Long postId) {
        return requestRepository.findRequestsByPostId(postId);
    }





//    // 완료 상태로 업데이트
//    public void markRequestAsCompleted(Long requestId) {
//        Request request = requestRepository.findById(requestId)
//                .orElseThrow(() -> new IllegalArgumentException("Request not found with id: " + requestId));
//        RequestStatus completedStatus = requestStatusRepository.findById(2L)
//                .orElseThrow(() -> new IllegalArgumentException("완료 상태가 없습니다."));
//        request.setRequestStatus(completedStatus);
//        requestRepository.save(request);
//    }


}
