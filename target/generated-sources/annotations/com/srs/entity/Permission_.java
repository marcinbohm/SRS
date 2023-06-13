package com.srs.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Permission.class)
public abstract class Permission_ {

	public static volatile SingularAttribute<Permission, Integer> permissionId;
	public static volatile SingularAttribute<Permission, Boolean> allowDelete;
	public static volatile SingularAttribute<Permission, Integer> classId;
	public static volatile SingularAttribute<Permission, Role> permissionRole;
	public static volatile SingularAttribute<Permission, Boolean> allowRead;
	public static volatile SingularAttribute<Permission, Boolean> allowAdd;
	public static volatile SingularAttribute<Permission, Boolean> allowModify;
	public static volatile SingularAttribute<Permission, Category> category;
	public static volatile SingularAttribute<Permission, Integer> categoryId;

	public static final String PERMISSION_ID = "permissionId";
	public static final String ALLOW_DELETE = "allowDelete";
	public static final String CLASS_ID = "classId";
	public static final String PERMISSION_ROLE = "permissionRole";
	public static final String ALLOW_READ = "allowRead";
	public static final String ALLOW_ADD = "allowAdd";
	public static final String ALLOW_MODIFY = "allowModify";
	public static final String CATEGORY = "category";
	public static final String CATEGORY_ID = "categoryId";

}

