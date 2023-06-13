package com.srs.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Department.class)
public abstract class Department_ {

	public static volatile ListAttribute<Department, User> departmentUserList;
	public static volatile SingularAttribute<Department, Integer> departmentId;
	public static volatile SingularAttribute<Department, String> name;
	public static volatile SingularAttribute<Department, String> description;

	public static final String DEPARTMENT_USER_LIST = "departmentUserList";
	public static final String DEPARTMENT_ID = "departmentId";
	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";

}

