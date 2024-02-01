package com.devgrp.fitnesswebapp.service;

import com.devgrp.fitnesswebapp.dto.NotificationDTO;
import com.devgrp.fitnesswebapp.dto.NotificationGetDTO;
import com.devgrp.fitnesswebapp.entity.Notification;
import com.devgrp.fitnesswebapp.repository.NotificationRepository;
import com.devgrp.fitnesswebapp.util.VarList;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class NotificationService {
    private  NotificationRepository notificationRepository;
    private ModelMapper modelMapper;
    public String addNotification(NotificationDTO notificationDTO){
        try{
            notificationRepository.save(modelMapper.map(notificationDTO, Notification.class));
            return VarList.RSP_SUCCESS;
        }
        catch (Exception ex){
            return VarList.RSP_ERROR;
        }
    }

    public String updateNotification(NotificationGetDTO notificationGetDTO){
        if(notificationRepository.existsById(notificationGetDTO.getId())){
            notificationRepository.save(modelMapper.map(notificationGetDTO,Notification.class));
            return VarList.RSP_SUCCESS;
        }
        else
            return VarList.RSP_NO_DATA_FOUND;
    }

}
