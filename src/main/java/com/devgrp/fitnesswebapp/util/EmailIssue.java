package com.devgrp.fitnesswebapp.util;

import com.devgrp.fitnesswebapp.dto.IssueDTO;
import lombok.Data;

@Data
public class EmailIssue {
    private String userEmail;
    private IssueDTO issueDTO;
}
