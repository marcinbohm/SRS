package com.srs.entity;

import org.springframework.boot.actuate.audit.listener.AuditListener;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "user", schema = "public")
@EntityListeners(AuditListener.class)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_generator")
    @SequenceGenerator(name="user_generator", sequenceName = "user_user_id_seq", allocationSize=1)
    @Column(name = "user_id")
    private Integer userId;

    @NotNull
    @Column(name = "firstname")
    private String firstname;

    @NotNull
    @Column(name = "lastname")
    private String lastname;

    @NotBlank(message = "Login cannot be blank!")
    @Column(name = "login")
    private String login;

    @NotNull
    @Column(name = "password")
    private String password;

    @Column(name = "email")
    @Pattern(regexp="^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$")
    private String email;

    @NotNull
    @Column(name = "active")
    private Boolean active;

    @NotNull
    @Column(name = "blocked")
    private Boolean blocked;

    @Column(name = "expire_account_date")
    private LocalDate expireAccountDate;

    @Column(name = "expire_password_date")
    private LocalDate expirePasswordDate;

    @Column(name = "last_login_time")
    private LocalDateTime lastLoginTime;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Positive
    @Column(name = "created_by")
    private Integer createdBy;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Positive
    @Column(name = "updated_by")
    private Integer updatedBy;

    @NotNull
    @Column(name = "usertype")
    private Integer userType;

    @Column(name = "supervisor_id")
    private Integer supervisorId;

    @PrePersist
    public void prePersist() {
        if(prisonId == null)
            prisonId = 1;
    }

    @Column(name = "prison_id")
    private Integer prisonId;

    @Size(min = 1, max = 1)
    @Column(name = "gender")
    private String gender;

    @Past
    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Pattern(regexp = "/^[0-9]{2}([02468]1|[13579][012])(0[1-9]|1[0-9]|2[0-9]|3[01])[0-9]{5}$/gm")
    @Size(min = 11, max = 11)
    @Column(name = "pesel")
    private String pesel;


    @Size(max = 45)
    @Column(name = "card_status")
    private String cardStatus;

    @NotNull
    @Column(name = "department_id")
    private Integer departmentId;

    @Column(name = "status")
    private Integer status;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_role",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "role_id") }
    )
    private Set<Role> userRoleSet;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Session> userSessionsList;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_shift",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "shift_id") }
    )
    private Set<Shift> userShiftSet;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Application> userApplicationList;

    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "department_id",
            insertable = false,
            updatable = false
    )
    private Department department;

    public Integer getDepartmentId() {
        return departmentId;
    }

    public String getCardStatus() {
        return cardStatus;
    }

    public String getPesel() {
        return pesel;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getGender() {
        return gender;
    }


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getBlocked() {
        return blocked;
    }

    public void setBlocked(Boolean blocked) {
        this.blocked = blocked;
    }

    public LocalDate getExpireAccountDate() {
        return expireAccountDate;
    }

    public void setExpireAccountDate(LocalDate expireAccountDate) {
        this.expireAccountDate = expireAccountDate;
    }

    public LocalDate getExpirePasswordDate() {
        return expirePasswordDate;
    }

    public void setExpirePasswordDate(LocalDate expirePasswordDate) {
        this.expirePasswordDate = expirePasswordDate;
    }

    public LocalDateTime getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(LocalDateTime lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Integer updatedBy) {
        this.updatedBy = updatedBy;
    }

    public List<Session> getUserSessionsList() {
        return userSessionsList;
    }

    public void setUserSessionsList(List<Session> userSessionsList) {
        this.userSessionsList = userSessionsList;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Integer getSupervisorId() {
        return supervisorId;
    }

    public void setSupervisorId(Integer supervisorId) {
        this.supervisorId = supervisorId;
    }

    public Integer getPrisonId() {
        return prisonId;
    }

    public void setPrisonId(Integer prisonId) {
        this.prisonId = prisonId;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public void setCardStatus(String cardStatus) {
        this.cardStatus = cardStatus;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public Set<Role> getUserRoleSet() {
        return userRoleSet;
    }

    public void setUserRoleSet(Set<Role> userRoleSet) {
        this.userRoleSet = userRoleSet;
    }

    public Set<Shift> getUserShiftSet() {
        return userShiftSet;
    }

    public void setUserShiftSet(Set<Shift> userShiftSet) {
        this.userShiftSet = userShiftSet;
    }

    public List<Application> getUserApplicationList() {
        return userApplicationList;
    }

    public void setUserApplicationList(List<Application> userApplicationList) {
        this.userApplicationList = userApplicationList;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Transient
    private String localId = null;

    private void lazyInitLocalId() {
        if (this.localId == null) {
            if (this.getUserId() != null) {
                this.localId = User.class.getSimpleName() + this.getUserId();
            } else {
                this.localId = UUID.randomUUID().toString();
            }
        }

    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (other != null && this.getClass() == other.getClass()) {
            User that = (User)other;
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
