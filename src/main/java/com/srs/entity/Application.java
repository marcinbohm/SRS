package com.srs.entity;

import org.springframework.boot.actuate.audit.listener.AuditListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "application")
@EntityListeners(AuditListener.class)
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "application_generator")
    @SequenceGenerator(name="application_generator", sequenceName = "application_application_id_seq", allocationSize=1)
    @Column(name = "application_id")
    private Integer applicationId;

    @Column(name = "prisoner_id")
    private Integer prisonerId;

    @Size(min = 1, max = 400)
    @Column(name = "additional_information")
    private String additionalInformation;

    @Column(name = "type_of_application")
    private Integer typeOfApplication;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "apply_date")
    private LocalDate applyDate;

    @Column(name = "review_date")
    private LocalDate reviewDate;

    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "prisoner_id",
            insertable = false,
            updatable = false
    )
    private Prisoner prisoner;

    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "user_id",
            insertable = false,
            updatable = false
    )
    private User user;

    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "type_of_application",
            insertable = false,
            updatable = false
    )
    private ApplicationType applicationType;

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

    public LocalDate getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(LocalDate applyDate) {
        this.applyDate = applyDate;
    }

    public LocalDate getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(LocalDate reviewDate) {
        this.reviewDate = reviewDate;
    }

    public Prisoner getPrisoner() {
        return prisoner;
    }

    public void setPrisoner(Prisoner prisoner) {
        this.prisoner = prisoner;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ApplicationType getApplicationType() {
        return applicationType;
    }

    public void setApplicationType(ApplicationType applicationType) {
        this.applicationType = applicationType;
    }

    @Transient
    private String localId = null;

    private void lazyInitLocalId() {
        if (this.localId == null) {
            if (this.getApplicationId() != null) {
                this.localId = Application.class.getSimpleName() + this.getApplicationId();
            } else {
                this.localId = UUID.randomUUID().toString();
            }
        }

    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (other != null && this.getClass() == other.getClass()) {
            Application that = (Application)other;
            this.lazyInitLocalId();
            that.lazyInitLocalId();
            return this.localId.equals(that.localId);
        } else {
            return false;
        }
    }

    public int hashCode() {
        this.lazyInitLocalId();
        return this.localId.hashCode();
    }

    public Object fieldGet(Field f) throws IllegalAccessException {
        return f.get(this);
    }
}
