/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring.Springweb.DTO;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppointmentResponse {
    private Integer id;
    private String contactName;
    private String contactEmail;
    private String contactPhone;
    private String status;
    private String notes;
    private LocalDateTime startAt;
    private LocalDateTime endAt;

    private String serviceName;
    private String staffName;   // null nếu admin chưa gán
    private String roomName;    // null nếu admin chưa gán
}
