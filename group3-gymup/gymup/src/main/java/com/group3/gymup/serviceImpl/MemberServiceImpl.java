package com.group3.gymup.serviceImpl;

import com.group3.gymup.entity.Member;
import com.group3.gymup.mapper.MemberMapper;
import com.group3.gymup.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

  @Autowired
  private MemberMapper memberMapper;

  @Override
  public Member getMember(String member_id) {
    return memberMapper.getMember(member_id);
  }
}
