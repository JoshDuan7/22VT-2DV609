package com.group3.gymup.controller;

import com.group3.gymup.entity.Booking;
import com.group3.gymup.service.BookingService;
import com.group3.gymup.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Date;

@Controller
@RequestMapping("/")
public class PageController {
    @Autowired
    MemberService memberService;

    @Autowired
    BookingService bookingService;

    @RequestMapping("doBookings")
    public String getBookings() {
        return "allBookings";
    }

    @PostMapping("/index")
    public ModelAndView getIndex(String userId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/index");
        modelAndView.addObject("userId", userId);
        return modelAndView;
    }

    @RequestMapping("bookCalendar")
    public String getCalendar() {
        return "calendarBackup";
    }

    @RequestMapping("main")
    public String getClick() {return "mainPage";}

    @RequestMapping("memberBookings")
    public String getMemberBookings() {
        return "getMemberBookings";
    }

    @RequestMapping("/availableEquipment/{}/time")
    public String getTimeByRedirect() {
        return "time";
    }

    @GetMapping(value = "/memberBookings/{booking_id}")
    public String deleteBooking(@PathVariable String booking_id) {
        bookingService.deleteBooking(booking_id);
        return "redirect:/doBookings";
    }

    @RequestMapping("booking")
    public String getBookingId() {
        return "getBooking";
    }

    @GetMapping(value="/availableEquipment/date={date}/")
    public String getAvailableTimes() {
        return "getEquipment";
    }

    @GetMapping(value="/pickTime")
    public String getTimes() {
        return "time";
    }

    @GetMapping(value="/confirmation")
    public String getConfirmation() {
        return "confirmation";
    }

    @PostMapping("/mainLogin")
    public ModelAndView main(String userId, String password) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            if (memberService.getMember(userId).getPassword().equals(password)) {
                modelAndView.setViewName("/index");
                modelAndView.addObject("userId", userId);
            }
        }
        catch(Exception e) {
            modelAndView.setViewName("/loginError");
        }
        return modelAndView;
    }

    @RequestMapping("login")
    public String login() {
        return "legacyLogin";
    }

    @PostMapping("/updateBookingToDatabase")
    public ModelAndView update(String memberId, String equipmentId, String date, String startTime, String endTime) {
        ModelAndView modelAndView = new ModelAndView();
        bookingService.addBooking(Date.valueOf(date), startTime, endTime, equipmentId, memberId);
        Booking booking = bookingService.getLastBookingByMemberId(memberId);
        modelAndView.setViewName("/showBooking");
        modelAndView.addObject("bookingId", booking.getBookingId());
        modelAndView.addObject("date", booking.getDate());
        modelAndView.addObject("equipmentId", booking.getEquipmentId());
        modelAndView.addObject("memberId", booking.getMemberId());
        modelAndView.addObject("startTime", booking.getStartTime());
        modelAndView.addObject("endTime", booking.getEndTime());
        return modelAndView;
    }
}
