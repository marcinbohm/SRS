package com.srs.entity;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Cell.class)
public abstract class Cell_ {

	public static volatile SetAttribute<Cell, Sentence> cellSentenceSet;
	public static volatile SingularAttribute<Cell, LocalDate> assignDate;
	public static volatile SingularAttribute<Cell, String> segment;
	public static volatile SingularAttribute<Cell, String> blok;
	public static volatile SingularAttribute<Cell, LocalDate> unassignDate;
	public static volatile SingularAttribute<Cell, Integer> prisonId;
	public static volatile SingularAttribute<Cell, Integer> cellId;
	public static volatile SingularAttribute<Cell, Prison> prison;

	public static final String CELL_SENTENCE_SET = "cellSentenceSet";
	public static final String ASSIGN_DATE = "assignDate";
	public static final String SEGMENT = "segment";
	public static final String BLOK = "blok";
	public static final String UNASSIGN_DATE = "unassignDate";
	public static final String PRISON_ID = "prisonId";
	public static final String CELL_ID = "cellId";
	public static final String PRISON = "prison";

}

