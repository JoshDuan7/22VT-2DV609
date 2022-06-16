package com.group3.gymup.controller;

import com.group3.gymup.entity.Booking;
import com.group3.gymup.entity.Equipment;
import com.group3.gymup.entity.Member;
import com.group3.gymup.service.BookingService;
import com.group3.gymup.service.EquipmentService;
import com.group3.gymup.service.MemberService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.Date;

@RequestMapping("/")
@RestController
public class BookingController {

    private final BookingService bookingService;
    private final EquipmentService equipmentService;
    private final MemberService memberService;

    public BookingController(BookingService bookingService, EquipmentService equipmentService,
                             MemberService memberService) {
        this.bookingService = bookingService;
        this.equipmentService = equipmentService;
        this.memberService = memberService;
    }

    @GetMapping(value = "/memberBookings/memberid={member_id}")
    public ResponseEntity<?> getMemberId(@PathVariable String member_id) {
        return bookingService.getBookingsByMemberId(member_id);
    }

    @GetMapping(value = "/getBooking/bookingid={booking_id}")
    public ResponseEntity<?> getBookingId(@PathVariable String booking_id) {
        return bookingService.getBooking(booking_id);
    }

    @GetMapping(value="/availableEquipment/date={date}/equipmentid={equipment_id}/start-time={stime}/end-time={etime}/memberid={member_id}")
    public ResponseEntity<Booking> createBooking(@PathVariable String date, String equipment_id, String stime, String etime, String member_id) {

        Equipment equipmentBooked = equipmentService.getEquipment(equipment_id);
        Member chosenMember = memberService.getMember(member_id);
        Date chosenDate = Date.valueOf(date);
        return bookingService.addBooking(chosenDate, stime, etime, chosenMember.getMemberId(), equipmentBooked.getEquipmentId());

    }

}
