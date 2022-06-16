package com.group3.gymup.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.group3.gymup.entity.Equipment;
import com.group3.gymup.entity.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface EquipmentMapper extends BaseMapper<Equipment> {

//    @Select("Select #{equipment_name} from equipment")

  @Select("SELECT * FROM gym.equipment WHERE equipment_id NOT IN(SELECT equipment_id FROM gym.bookings WHERE date = #{date} AND member_id = #{member_id});")
  List<Equipment> getAvailableEquipmentByDate(String member_id, String date);

  @Select("SELECT * FROM gym.equipment WHERE equipment_id = #{equipment_id}")
  Equipment getEquipment(String equipment_id);

  //List<Time> getEquipmentStartTimeByDate(String date, String equipId);

  //List<Equipment> equipList = userMapper.selectList(null);
}
