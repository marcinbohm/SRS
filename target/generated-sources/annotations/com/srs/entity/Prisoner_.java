package com.srs.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Prisoner.class)
public abstract class Prisoner_ {

	public static volatile SingularAttribute<Prisoner, String> surname;
	public static volatile ListAttribute<Prisoner, Application> prisonerApplicationList;
	public static volatile SingularAttribute<Prisoner, String> kartaPobytuId;
	public static volatile SingularAttribute<Prisoner, String> name;
	public static volatile SingularAttribute<Prisoner, Integer> prisonerId;
	public static volatile SingularAttribute<Prisoner, String> pesel;
	public static volatile SingularAttribute<Prisoner, String> passportId;
	public static volatile SingularAttribute<Prisoner, Integer> status;

	public static final String SURNAME = "surname";
	public static final String PRISONER_APPLICATION_LIST = "prisonerApplicationList";
	public static final String KARTA_POBYTU_ID = "kartaPobytuId";
	public static final String NAME = "name";
	public static final String PRISONER_ID = "prisonerId";
	public static final String PESEL = "pesel";
	public static final String PASSPORT_ID = "passportId";
	public static final String STATUS = "status";

}

