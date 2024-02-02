package com.devgrp.fitnesswebapp.util;

import com.devgrp.fitnesswebapp.dto.IssueDTO;
import com.devgrp.fitnesswebapp.entity.types.IssueType;
import lombok.Data;

@Data
public class UpdateEmailIssue {
    private String userEmail;
    private IssueType type;
    private IssueDTO issueDTO;
}
