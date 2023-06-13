package com.srs.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Category.class)
public abstract class Category_ {

	public static volatile SingularAttribute<Category, String> code;
	public static volatile SingularAttribute<Category, String> description;
	public static volatile SingularAttribute<Category, String> helpDescription;
	public static volatile SingularAttribute<Category, Integer> categoryId;
	public static volatile ListAttribute<Category, Permission> categoryPermissionsList;

	public static final String CODE = "code";
	public static final String DESCRIPTION = "description";
	public static final String HELP_DESCRIPTION = "helpDescription";
	public static final String CATEGORY_ID = "categoryId";
	public static final String CATEGORY_PERMISSIONS_LIST = "categoryPermissionsList";

}

