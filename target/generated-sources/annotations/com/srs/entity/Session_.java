package com.srs.entity;

import java.time.LocalDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Session.class)
public abstract class Session_ {

	public static volatile SingularAttribute<Session, String> ticket;
	public static volatile SingularAttribute<Session, LocalDateTime> lastActive;
	public static volatile SingularAttribute<Session, String> refreshTicket;
	public static volatile SingularAttribute<Session, Integer> sessionId;
	public static volatile SingularAttribute<Session, Integer> userId;
	public static volatile SingularAttribute<Session, User> user;

	public static final String TICKET = "ticket";
	public static final String LAST_ACTIVE = "lastActive";
	public static final String REFRESH_TICKET = "refreshTicket";
	public static final String SESSION_ID = "sessionId";
	public static final String USER_ID = "userId";
	public static final String USER = "user";

}

