package com.srs.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Role.class)
public abstract class Role_ {

	public static volatile SingularAttribute<Role, Integer> roleId;
	public static volatile SingularAttribute<Role, String> roleCode;
	public static volatile SingularAttribute<Role, String> name;
	public static volatile SingularAttribute<Role, String> description;
	public static volatile SingularAttribute<Role, Boolean> active;
	public static volatile SetAttribute<Role, User> roleUserSet;
	public static volatile ListAttribute<Role, Permission> classPermissionList;
	public static volatile SingularAttribute<Role, Boolean> adminClass;

	public static final String ROLE_ID = "roleId";
	public static final String ROLE_CODE = "roleCode";
	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";
	public static final String ACTIVE = "active";
	public static final String ROLE_USER_SET = "roleUserSet";
	public static final String CLASS_PERMISSION_LIST = "classPermissionList";
	public static final String ADMIN_CLASS = "adminClass";

}

