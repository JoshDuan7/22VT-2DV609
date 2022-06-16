package com.group3.gymup.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.group3.gymup.entity.Booking;
import com.group3.gymup.entity.Equipment;
import com.group3.gymup.entity.Member;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public interface BookingMapper extends BaseMapper<Booking> {
    @Select("SELECT start_time FROM bookings WHERE date = #{date} and equipment_id = #{equipId}")
    List<Time> getEquipmentStartTimeByDate(String date, String equipId);

    @Select("SELECT end_time FROM bookings WHERE date = #{date} and equipment_id = #{equipId}")
    List<Time> getEquipmentEndTimeByDate(String date, String equipId);

    @Insert("INSERT INTO gym.bookings VALUES (null, #{date}, #{start_time}, #{end_time}, #{member_id}, #{equipment_id})")
    void addBooking(Date date, String start_time, String end_time, String member_id, String equipment_id);

    @Select("SELECT * FROM gym.bookings WHERE booking_id = #{booking_id}")
    Booking getBookingByBookingId(String bookingId);

    @Select("SELECT * FROM gym.bookings WHERE member_id = #{member_id}")
    List<Booking> getBookingsByMemberId(String memberID);

    @Select("SELECT * FROM bookings WHERE member_id = #{member_id} ORDER BY booking_id DESC LIMIT 1;")
    Booking getLastBookingByMemberId(String memberID);

    @Delete("DELETE FROM gym.bookings WHERE booking_id = #{booking_id};")
    void deleteBooking(String bookingId);
}
