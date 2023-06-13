package com.srs.dto;

public class SentenceDTO {

    private Integer sentenceId;

    private Integer prisonerId;

    private Integer prisonId;

    private String startDate;

    private String endDate;

    private String assignDate;

    private String unassignDate;

    public SentenceDTO() {
    }

    public Integer getSentenceId() {
        return sentenceId;
    }

    public void setSentenceId(Integer sentenceId) {
        this.sentenceId = sentenceId;
    }

    public Integer getPrisonerId() {
        return prisonerId;
    }

    public void setPrisonerId(Integer prisonerId) {
        this.prisonerId = prisonerId;
    }

    public Integer getPrisonId() {
        return prisonId;
    }

    public void setPrisonId(Integer prisonId) {
        this.prisonId = prisonId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getAssignDate() {
        return assignDate;
    }

    public void setAssignDate(String assignDate) {
        this.assignDate = assignDate;
    }

    public String getUnassignDate() {
        return unassignDate;
    }

    public void setUnassignDate(String unassignDate) {
        this.unassignDate = unassignDate;
    }
}
