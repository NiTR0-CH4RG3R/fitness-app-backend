package com.devgrp.fitnesswebapp.repository;

import com.devgrp.fitnesswebapp.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Integer> {}
