package com.devgrp.fitnesswebapp.util;

import com.devgrp.fitnesswebapp.entity.types.GoalType;
import com.devgrp.fitnesswebapp.entity.types.IssueType;
import lombok.Data;

@Data
public class IssueDelete {
    private String useremail;
    private IssueType issueType;
}
