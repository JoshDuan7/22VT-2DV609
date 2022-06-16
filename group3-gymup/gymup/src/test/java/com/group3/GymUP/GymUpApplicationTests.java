package com.group3.GymUP;

import com.group3.gymup.entity.Booking;
import com.group3.gymup.entity.Equipment;
import com.group3.gymup.entity.Member;
import com.group3.gymup.mapper.BookingMapper;
import com.group3.gymup.mapper.EquipmentMapper;
import com.group3.gymup.service.BookingService;
import com.group3.gymup.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;


import java.sql.Time;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class GymUpApplicationTests {
	@Autowired
	private EquipmentMapper equipmentMapper;

	@Autowired
	private BookingMapper bookingMapper;

	@Autowired
	BookingService bookingService;

	@Autowired
	MemberService memberService;

	@Test
	void contextLoads() {
	}

	@Test
	void testMapper() {
		System.out.println(("----- selectAll method test ------"));
		List<Equipment> equipList = equipmentMapper.selectList(null);
		assertThat(equipList.size()).isEqualTo(3);
		equipList.forEach(System.out::println);
	}

	@Test
	void testBookingMapper() {
		Booking booking = bookingMapper.selectById("1");
		System.out.println(booking);
	}

	@Test
	void testEquipmentService() {

		List<Equipment> equipList = bookingService.getAllEquipment();
		assertThat(equipList.size()).isEqualTo(3);
		equipList.forEach(System.out::println);
	}

	@Test
	void testMemberBookings() {
		//ResponseEntity<?> bookings = bookingService.getBookingsByMemberId("sara001");
	}

	@Test
	void testBookingService() {
		Equipment e = new Equipment();
		e.setEquipmentId("adbr002");
		Date d = new Date(122, Calendar.JUNE,5);
		ArrayList<List<Time>> dLst = bookingService.getEquipmentScheduleByDate((java.sql.Date) d, e);
		System.out.println(dLst);
	}

	@Test
	void testMemberService() {
		String userid = "user";
		String password = "user";
		Member m = memberService.getMember(userid);
		if (m.getPassword().equals(password)) {
			System.out.println("yes");
		} else {
			System.out.println("no");
		}
	}

}
