package com.srs.dto;

public class ApplicationDTO {

    private Integer applicationId;

    private Integer prisonerId;

    private String additionalInformation;

    private Integer typeOfApplication;

    private Integer userId;

    private String applyDate;

    private String reviewDate;

    public ApplicationDTO() {
    }

    public Integer getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Integer applicationId) {
        this.applicationId = applicationId;
    }

    public Integer getPrisonerId() {
        return prisonerId;
    }

    public void setPrisonerId(Integer prisonerId) {
        this.prisonerId = prisonerId;
    }

    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    public Integer getTypeOfApplication() {
        return typeOfApplication;
    }

    public void setTypeOfApplication(Integer typeOfApplication) {
        this.typeOfApplication = typeOfApplication;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(String applyDate) {
        this.applyDate = applyDate;
    }

    public String getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(String reviewDate) {
        this.reviewDate = reviewDate;
    }
}
