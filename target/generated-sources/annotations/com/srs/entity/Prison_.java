package com.srs.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Prison.class)
public abstract class Prison_ {

	public static volatile ListAttribute<Prison, Sentence> prisonSentenceList;
	public static volatile ListAttribute<Prison, Cell> prisonCellList;
	public static volatile SingularAttribute<Prison, String> city;
	public static volatile SingularAttribute<Prison, String> street;
	public static volatile SingularAttribute<Prison, String> postalCode;
	public static volatile SingularAttribute<Prison, Integer> prisonId;
	public static volatile SingularAttribute<Prison, String> voivodeship;
	public static volatile ListAttribute<Prison, Report> prisonReportList;

	public static final String PRISON_SENTENCE_LIST = "prisonSentenceList";
	public static final String PRISON_CELL_LIST = "prisonCellList";
	public static final String CITY = "city";
	public static final String STREET = "street";
	public static final String POSTAL_CODE = "postalCode";
	public static final String PRISON_ID = "prisonId";
	public static final String VOIVODESHIP = "voivodeship";
	public static final String PRISON_REPORT_LIST = "prisonReportList";

}

