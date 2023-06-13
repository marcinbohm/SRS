package com.srs.entity;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Sentence.class)
public abstract class Sentence_ {

	public static volatile SingularAttribute<Sentence, LocalDate> endDate;
	public static volatile SingularAttribute<Sentence, LocalDate> assignDate;
	public static volatile SingularAttribute<Sentence, Prisoner> prisoner;
	public static volatile SingularAttribute<Sentence, LocalDate> unassignDate;
	public static volatile SingularAttribute<Sentence, Integer> sentenceId;
	public static volatile SingularAttribute<Sentence, Integer> prisonerId;
	public static volatile SingularAttribute<Sentence, Integer> prisonId;
	public static volatile SingularAttribute<Sentence, Prison> prison;
	public static volatile SingularAttribute<Sentence, LocalDate> startDate;
	public static volatile SetAttribute<Sentence, Cell> sentenceCellSet;

	public static final String END_DATE = "endDate";
	public static final String ASSIGN_DATE = "assignDate";
	public static final String PRISONER = "prisoner";
	public static final String UNASSIGN_DATE = "unassignDate";
	public static final String SENTENCE_ID = "sentenceId";
	public static final String PRISONER_ID = "prisonerId";
	public static final String PRISON_ID = "prisonId";
	public static final String PRISON = "prison";
	public static final String START_DATE = "startDate";
	public static final String SENTENCE_CELL_SET = "sentenceCellSet";

}

