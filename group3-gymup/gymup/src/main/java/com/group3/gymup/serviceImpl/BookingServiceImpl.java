package com.group3.gymup.serviceImpl;

import com.group3.gymup.entity.Booking;
import com.group3.gymup.entity.Equipment;
import com.group3.gymup.mapper.BookingMapper;
import com.group3.gymup.mapper.EquipmentMapper;
import com.group3.gymup.mapper.MemberMapper;
import com.group3.gymup.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.IOException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService{

    @Autowired
    private BookingMapper bookingMapper;

    @Autowired
    private EquipmentMapper equipmentMapper;

    @Autowired
    private MemberMapper memberMapper;


    @Override
    public List<Equipment> getAllEquipment() {
        return equipmentMapper.selectList(null);
    }

    @Override
    public ArrayList<List<Time>> getEquipmentScheduleByDate(Date date, Equipment equipment) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(date);
        String equipId = equipment.getEquipmentId();
        List<Time> startLst = bookingMapper.getEquipmentStartTimeByDate(dateString, equipId);
        List<Time> endLst = bookingMapper.getEquipmentEndTimeByDate(dateString, equipId);
        ArrayList<List<Time>> lst = new ArrayList<List<Time>>();
        for (int i = 0; i < startLst.size(); i++) {
            List<Time> schedule = new ArrayList<Time>();
            schedule.add(startLst.get(i));
            schedule.add(endLst.get(i));
            lst.add(schedule);
        }
        return lst;
    }

    @Override
    public ResponseEntity<?> getBookingsByMemberId(String memberId) {
        List<Booking> bookings = bookingMapper.getBookingsByMemberId(memberId);
        List<Equipment> equipment = getAllEquipment();

        File htmlFile = new File("src/main/resources/templates/getMemberBookings.html");

        String htmlToString = "";
        try {

            htmlToString = org.apache.commons.io.FileUtils.readFileToString(htmlFile, "utf-8");
            String equipmentName = "";


                for (Booking b : bookings) {
                    equipmentName += "<tr>\n";
                    equipmentName += "<td>" + b.getBookingId() + "</td>\n";

                    for (Equipment e : equipment) {
                        if (b.getEquipmentId().equalsIgnoreCase(e.getEquipmentId())) {
                            equipmentName += "<td>" + e.getEquipmentName() + "</td>\n";
                            break;
                        }
                    }

                    equipmentName += "<td>" + b.getEquipmentId() + "</td>\n";
                    equipmentName += "<td>" + b.getDate().toString() + "</td>\n";
                    equipmentName += "<td>" + b.getStartTime().toString() + "</td>\n";
                    equipmentName += "<td>" + b.getEndTime().toString() + "</td>\n";

                    equipmentName += "<td onclick=\"localStorage.setItem('booking-id','" + b.getBookingId() + "')\">" +
                            "<a href = \"" + b.getBookingId() + "\">Delete</a>";
                    equipmentName += "<a href=\"#\" tabindex=\"-1\" class=\"row-link\"></a>";
                    equipmentName += "</td>";

                    equipmentName += "</tr>\n";
                }

            htmlToString = htmlToString.replace("$sample", equipmentName);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity(htmlToString, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getBooking(String bookingId) {
        Booking bookingRetrieved = bookingMapper.getBookingByBookingId(bookingId);
        return new ResponseEntity(bookingRetrieved, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Booking> addBooking(Date date, String startTime, String endTime, String equipment_id, String member_id) {
        Time time = Time.valueOf(timeConversion(startTime) + ":00");
        Time secondTime = Time.valueOf(timeConversion(endTime) + ":00");

        Booking nb = new Booking();
        nb.setDate(date).setStartTime(time).setEndTime(secondTime).setEquipmentId(equipment_id).setMemberId(member_id);

        bookingMapper.addBooking(nb.getDate(), time.toString(), secondTime.toString(), nb.getMemberId(), nb.getEquipmentId());

        return new ResponseEntity<>(nb, HttpStatus.CREATED);
    }

    @Override
    public Booking getLastBookingByMemberId(String memberId) {
        return bookingMapper.getLastBookingByMemberId(memberId);
    }

    @Override
    public void deleteBooking(String bookingId) {
        bookingMapper.deleteBooking(bookingId);
    }

    public static String timeConversion(String s) {
        int hour = Integer.parseInt(s.substring(0, 2)) % 12;
        String upperCases = s.toUpperCase();

        if (upperCases.endsWith(" PM")) {
            hour += 12;
        } else {
            String formattedTime = String.format("%02d", hour) + upperCases.substring(2, 8);
            String newStr = formattedTime.replace(" AM", "");
            return newStr;
        }

        String formattedTime = String.format("%02d", hour) + upperCases.substring(2, 8);
        String newStr = formattedTime.replace(" PM", "");
        return newStr;
    }
}
