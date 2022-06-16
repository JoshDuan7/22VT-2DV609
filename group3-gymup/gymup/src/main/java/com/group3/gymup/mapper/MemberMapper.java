package com.group3.gymup.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.group3.gymup.entity.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MemberMapper extends BaseMapper<Member> {

  @Select("SELECT * FROM gym.members WHERE member_id = #{member_id}")
  Member getMember(String memberID);

}
