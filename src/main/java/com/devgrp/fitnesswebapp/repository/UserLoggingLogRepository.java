package com.devgrp.fitnesswebapp.repository;

import com.devgrp.fitnesswebapp.entity.UserLoggingLog;
import com.devgrp.fitnesswebapp.entity.compositekeys.UserLoggingLogKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLoggingLogRepository extends JpaRepository<UserLoggingLog, UserLoggingLogKey> {}
