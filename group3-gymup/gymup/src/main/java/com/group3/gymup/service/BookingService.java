package com.group3.gymup.service;

import com.group3.gymup.entity.Booking;
import com.group3.gymup.entity.Equipment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.ArrayList;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;

@Service
public interface BookingService {

    public List<Equipment> getAllEquipment();

    public ArrayList<List<Time>> getEquipmentScheduleByDate(Date date, Equipment equipment);

    public ResponseEntity<?> getBookingsByMemberId(String memberId);

    public ResponseEntity<?> getBooking(String bookingId);

    public ResponseEntity<Booking> addBooking(Date date, String startTime, String endTime, String equipment_id, String member_id);

    public Booking getLastBookingByMemberId(String memberId);

    public void deleteBooking(String bookingId);

}
