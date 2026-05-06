package com.backend14.week6.role;

import com.backend14.week6.policy.SubmissionPolicy;

public abstract class Role {
    private String name;
    private String major;
    private int generation;
    private String part;

    protected Role(String name, String major, int generation, String part) {
        this.name = name;
        this.major = major;
        this.generation = generation;
        this.part = part;
    }

    public final String getName() {
        return name;
    }

    public final String getMajor() {
        return major;
    }

    public final int getGeneration() {
        return generation;
    }

    public final String getPart() {
        return part;
    }

    public abstract SubmissionPolicy submissionPolicy();

    public boolean canSubmitAssignment() {
        return submissionPolicy().canSubmit();
    }

    public abstract String roleName();

    public abstract String getInfo();
}
