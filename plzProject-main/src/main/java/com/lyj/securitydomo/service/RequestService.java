package com.lyj.securitydomo.service;

import com.lyj.securitydomo.domain.Request;
import com.lyj.securitydomo.dto.RequestDTO;

import java.util.List;

public interface RequestService {
    List<RequestDTO> getRequests();

    void saveRequest(Request request);

    void saveRequest(long postId, String title, String content);
}
