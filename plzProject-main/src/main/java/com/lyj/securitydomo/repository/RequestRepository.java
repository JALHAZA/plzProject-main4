package com.lyj.securitydomo.repository;

import com.lyj.securitydomo.domain.Request;
import com.lyj.securitydomo.dto.RequestDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
    @Query("SELECT new com.lyj.securitydomo.dto.RequestDTO(r.requestId, r.post.postId, u.userId, r.title, r.content, u.username, p.contentText, r.regDate, r.requestStatus.requestStatusId, rs.status) " +
            "FROM Request r " +
            "JOIN r.user u " +
            "JOIN r.post p " +
            "JOIN r.requestStatus rs")
    List<RequestDTO> findAllRequestWithUserAndPostContent();
}
