package com.srs.dto;

public class CellDTO {

    private Integer cellId;

    private Integer prisonId;

    private String segment;

    private String blok;

    private String assignDate;

    private String unassignDate;

    public CellDTO() {
    }

    public Integer getCellId() {
        return cellId;
    }

    public void setCellId(Integer cellId) {
        this.cellId = cellId;
    }

    public Integer getPrisonId() {
        return prisonId;
    }

    public void setPrisonId(Integer prisonId) {
        this.prisonId = prisonId;
    }

    public String getSegment() {
        return segment;
    }

    public void setSegment(String segment) {
        this.segment = segment;
    }

    public String getBlok() {
        return blok;
    }

    public void setBlok(String blok) {
        this.blok = blok;
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
