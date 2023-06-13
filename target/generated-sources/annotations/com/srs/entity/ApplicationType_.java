package com.srs.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ApplicationType.class)
public abstract class ApplicationType_ {

	public static volatile SingularAttribute<ApplicationType, Integer> applicationTypeId;
	public static volatile SingularAttribute<ApplicationType, String> name;
	public static volatile SingularAttribute<ApplicationType, String> description;
	public static volatile ListAttribute<ApplicationType, Application> typeOfApplicationApplicationList;

	public static final String APPLICATION_TYPE_ID = "applicationTypeId";
	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";
	public static final String TYPE_OF_APPLICATION_APPLICATION_LIST = "typeOfApplicationApplicationList";

}

