package com.srs.entity;

import java.time.LocalDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Shift.class)
public abstract class Shift_ {

	public static volatile SetAttribute<Shift, User> shiftUserSet;
	public static volatile SingularAttribute<Shift, Integer> shiftId;
	public static volatile SingularAttribute<Shift, LocalDateTime> endDate;
	public static volatile ListAttribute<Shift, Report> shiftReportList;
	public static volatile SingularAttribute<Shift, LocalDateTime> startDate;

	public static final String SHIFT_USER_SET = "shiftUserSet";
	public static final String SHIFT_ID = "shiftId";
	public static final String END_DATE = "endDate";
	public static final String SHIFT_REPORT_LIST = "shiftReportList";
	public static final String START_DATE = "startDate";

}

