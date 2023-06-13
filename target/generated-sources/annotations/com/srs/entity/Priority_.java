package com.srs.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Priority.class)
public abstract class Priority_ {

	public static volatile ListAttribute<Priority, Report> priorityReportList;
	public static volatile SingularAttribute<Priority, String> description;
	public static volatile SingularAttribute<Priority, String> type;
	public static volatile SingularAttribute<Priority, Integer> priorityId;

	public static final String PRIORITY_REPORT_LIST = "priorityReportList";
	public static final String DESCRIPTION = "description";
	public static final String TYPE = "type";
	public static final String PRIORITY_ID = "priorityId";

}

