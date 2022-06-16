package com.group3.gymup.controller;

import com.group3.gymup.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

@RequestMapping("/")
@RestController
public class EquipmentController {

  @Autowired
  private final EquipmentService equipmentService;

  public EquipmentController(EquipmentService equipmentService) {
    this.equipmentService = equipmentService;
  }

  @GetMapping(value = "/availableEquipment/{member_id}/date={date}")
  public ResponseEntity<?> getAvailableEquipment(@PathVariable String member_id, @PathVariable String date) {
    Date newDate = Date.valueOf(date);
    return equipmentService.getAllEquipment(member_id, newDate);
  }

}
