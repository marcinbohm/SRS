package com.srs.entity;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Application.class)
public abstract class Application_ {

	public static volatile SingularAttribute<Application, String> additionalInformation;
	public static volatile SingularAttribute<Application, Integer> typeOfApplication;
	public static volatile SingularAttribute<Application, ApplicationType> applicationType;
	public static volatile SingularAttribute<Application, LocalDate> reviewDate;
	public static volatile SingularAttribute<Application, Prisoner> prisoner;
	public static volatile SingularAttribute<Application, Integer> prisonerId;
	public static volatile SingularAttribute<Application, Integer> applicationId;
	public static volatile SingularAttribute<Application, LocalDate> applyDate;
	public static volatile SingularAttribute<Application, Integer> userId;
	public static volatile SingularAttribute<Application, User> user;

	public static final String ADDITIONAL_INFORMATION = "additionalInformation";
	public static final String TYPE_OF_APPLICATION = "typeOfApplication";
	public static final String APPLICATION_TYPE = "applicationType";
	public static final String REVIEW_DATE = "reviewDate";
	public static final String PRISONER = "prisoner";
	public static final String PRISONER_ID = "prisonerId";
	public static final String APPLICATION_ID = "applicationId";
	public static final String APPLY_DATE = "applyDate";
	public static final String USER_ID = "userId";
	public static final String USER = "user";

}

