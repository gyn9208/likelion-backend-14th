package com.backend14.week6.policy;

public class StaffSubmissionPolicy implements SubmissionPolicy {

    @Override
    public boolean canSubmit() {
        return false;
    }
}
