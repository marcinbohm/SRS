package com.srs.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(User.class)
public abstract class User_ {

	public static volatile SingularAttribute<User, String> firstname;
	public static volatile SetAttribute<User, Shift> userShiftSet;
	public static volatile SingularAttribute<User, String> gender;
	public static volatile ListAttribute<User, Session> userSessionsList;
	public static volatile SingularAttribute<User, Integer> departmentId;
	public static volatile SingularAttribute<User, String> login;
	public static volatile SingularAttribute<User, LocalDate> expireAccountDate;
	public static volatile SingularAttribute<User, LocalDateTime> createdAt;
	public static volatile SingularAttribute<User, String> password;
	public static volatile SetAttribute<User, Role> userRoleSet;
	public static volatile SingularAttribute<User, Boolean> blocked;
	public static volatile ListAttribute<User, Application> userApplicationList;
	public static volatile SingularAttribute<User, String> pesel;
	public static volatile SingularAttribute<User, Department> department;
	public static volatile SingularAttribute<User, String> email;
	public static volatile SingularAttribute<User, String> cardStatus;
	public static volatile SingularAttribute<User, LocalDateTime> updatedAt;
	public static volatile SingularAttribute<User, Integer> updatedBy;
	public static volatile SingularAttribute<User, Boolean> active;
	public static volatile SingularAttribute<User, LocalDate> expirePasswordDate;
	public static volatile SingularAttribute<User, Integer> supervisorId;
	public static volatile SingularAttribute<User, Integer> userId;
	public static volatile SingularAttribute<User, LocalDate> birthDate;
	public static volatile SingularAttribute<User, String> lastname;
	public static volatile SingularAttribute<User, LocalDateTime> lastLoginTime;
	public static volatile SingularAttribute<User, Integer> createdBy;
	public static volatile SingularAttribute<User, Integer> prisonId;
	public static volatile SingularAttribute<User, Integer> userType;
	public static volatile SingularAttribute<User, Integer> status;

	public static final String FIRSTNAME = "firstname";
	public static final String USER_SHIFT_SET = "userShiftSet";
	public static final String GENDER = "gender";
	public static final String USER_SESSIONS_LIST = "userSessionsList";
	public static final String DEPARTMENT_ID = "departmentId";
	public static final String LOGIN = "login";
	public static final String EXPIRE_ACCOUNT_DATE = "expireAccountDate";
	public static final String CREATED_AT = "createdAt";
	public static final String PASSWORD = "password";
	public static final String USER_ROLE_SET = "userRoleSet";
	public static final String BLOCKED = "blocked";
	public static final String USER_APPLICATION_LIST = "userApplicationList";
	public static final String PESEL = "pesel";
	public static final String DEPARTMENT = "department";
	public static final String EMAIL = "email";
	public static final String CARD_STATUS = "cardStatus";
	public static final String UPDATED_AT = "updatedAt";
	public static final String UPDATED_BY = "updatedBy";
	public static final String ACTIVE = "active";
	public static final String EXPIRE_PASSWORD_DATE = "expirePasswordDate";
	public static final String SUPERVISOR_ID = "supervisorId";
	public static final String USER_ID = "userId";
	public static final String BIRTH_DATE = "birthDate";
	public static final String LASTNAME = "lastname";
	public static final String LAST_LOGIN_TIME = "lastLoginTime";
	public static final String CREATED_BY = "createdBy";
	public static final String PRISON_ID = "prisonId";
	public static final String USER_TYPE = "userType";
	public static final String STATUS = "status";

}

