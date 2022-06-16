package com.group3.gymup.service;

import com.group3.gymup.entity.Equipment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
public interface EquipmentService {

  ResponseEntity<?> getAllEquipment(String memberId, Date date);

  Equipment getEquipment(String equipment_id);

}
