package com.srs.repository.filters;

import java.time.LocalDate;
import java.util.List;

public class CellFilter {

    public List<Integer> cellIdList;

    public List<Integer> prisonIdList;

    public String segment;

    public String blok;

    public LocalDate assignDateTo;

    public LocalDate assignDateFrom;

    public LocalDate unassignDateTo;

    public LocalDate unassignDateFrom;
}
