package com.group3.gymup.serviceImpl;

import com.group3.gymup.entity.Booking;
import com.group3.gymup.entity.Equipment;
import com.group3.gymup.mapper.EquipmentMapper;
import com.group3.gymup.service.EquipmentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
public class EquipmentServiceImpl implements EquipmentService {

  @Autowired
  private EquipmentMapper equipmentMapper;

  @Override
  public ResponseEntity<?> getAllEquipment(String memberId, Date date) {
    List<Equipment> equipment = equipmentMapper.getAvailableEquipmentByDate(memberId, date.toString());

    File htmlFile = new File("src/main/resources/templates/getEquipment.html");

    String htmlToString = "";
    try {

      htmlToString = org.apache.commons.io.FileUtils.readFileToString(htmlFile, "utf-8");
      String equipmentName = "";

      for (Equipment e : equipment) {
        equipmentName += "<tr>\n";
        equipmentName += "<td onclick=\"localStorage.setItem('equipment-id','" + e.getEquipmentId() + "')\">" +
                "<a href=\"" + "time" + "\">" + e.getEquipmentId() + "</a></td>\n";
        equipmentName += "<td>" + e.getEquipmentName() + "</td>\n";
        equipmentName += "<td>" + e.getMuscle() + "</td>\n";
        equipmentName += "</tr>\n";
      }

      htmlToString = htmlToString.replace("$sample", equipmentName);

    } catch (IOException e) {
      e.printStackTrace();
    }
    return new ResponseEntity(htmlToString, HttpStatus.OK);
  }

  @Override
  public Equipment getEquipment(String equipment_id) {
    Equipment retrievedEquipment = equipmentMapper.getEquipment(equipment_id);
    return retrievedEquipment;
  }

}
