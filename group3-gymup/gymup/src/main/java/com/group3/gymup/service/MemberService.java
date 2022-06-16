package com.group3.gymup.service;

import com.group3.gymup.entity.Member;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface MemberService {

  public Member getMember(String member_id);

}
