package com.srs.dto;

public class RoleDTO {

    private Integer roleId;

    private String roleCode;

    private String name;

    private String description;

    private Boolean adminClass;

    private Boolean active;

    public RoleDTO() {
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getAdminClass() {
        return adminClass;
    }

    public void setAdminClass(Boolean adminClass) {
        this.adminClass = adminClass;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
