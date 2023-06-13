package com.srs.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Report.class)
public abstract class Report_ {

	public static volatile SingularAttribute<Report, Integer> shiftId;
	public static volatile SingularAttribute<Report, Integer> reportId;
	public static volatile SingularAttribute<Report, String> segment;
	public static volatile SingularAttribute<Report, Shift> shift;
	public static volatile SingularAttribute<Report, String> blok;
	public static volatile SingularAttribute<Report, Integer> prisonId;
	public static volatile SingularAttribute<Report, String> description;
	public static volatile SingularAttribute<Report, Priority> priority;
	public static volatile SingularAttribute<Report, Prison> prison;
	public static volatile SingularAttribute<Report, Integer> priorityId;

	public static final String SHIFT_ID = "shiftId";
	public static final String REPORT_ID = "reportId";
	public static final String SEGMENT = "segment";
	public static final String SHIFT = "shift";
	public static final String BLOK = "blok";
	public static final String PRISON_ID = "prisonId";
	public static final String DESCRIPTION = "description";
	public static final String PRIORITY = "priority";
	public static final String PRISON = "prison";
	public static final String PRIORITY_ID = "priorityId";

}

