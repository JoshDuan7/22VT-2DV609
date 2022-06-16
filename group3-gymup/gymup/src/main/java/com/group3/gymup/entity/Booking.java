package com.group3.gymup.entity;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

//Put User class for spring container to manage
//@Controller
//@Service
//@Repository	//Dao

@Component
@Getter
@Setter
@Accessors(chain = true)
@ToString
@Data
@TableName(value = "bookings")
public class Booking implements Serializable {
    @TableId(value = "booking_id",type = IdType.AUTO)
    private String bookingId;

    private String memberId;
    private String equipmentId;
    private Date date; //date and time

    private Time startTime;
    private Time endTime;

}