package com.lyj.securitydomo.repository;

import com.lyj.securitydomo.domain.Request;
import com.lyj.securitydomo.dto.RequestDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {

    // RequestDTO를 반환하도록 명시적인 쿼리를 작성
    @Query("SELECT new com.lyj.securitydomo.dto.RequestDTO(r.requestId, r.post.postId, u.userId, r.title, r.content, u.username, p.contentText, r.regDate, r.requestStatus.requestStatusId, rs.status) " +
            "FROM Request r " +
            "JOIN r.user u " +
            "JOIN r.post p " +
            "JOIN r.requestStatus rs")
    List<RequestDTO> findAllRequestWithUserAndPostContent();

    // postId를 기준으로 RequestDTO를 반환하는 명시적인 쿼리를 작성
    @Query("SELECT new com.lyj.securitydomo.dto.RequestDTO(r.requestId, r.post.postId, u.userId, r.title, r.content, u.username, p.contentText, r.regDate, r.requestStatus.requestStatusId, rs.status) " +
            "FROM Request r " +
            "JOIN r.user u " +
            "JOIN r.post p " +
            "JOIN r.requestStatus rs " +
            "WHERE r.post.postId = :postId")
    List<RequestDTO> findRequestsByPostId(@Param("postId") Long postId);
}
