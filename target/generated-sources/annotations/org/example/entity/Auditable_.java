package org.example.entity;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.MappedSuperclassType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.time.LocalDateTime;

@StaticMetamodel(Auditable.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class Auditable_ {

	public static final String CREATED_DATE = "createdDate";
	public static final String MODIFIED_DATE = "modifiedDate";

	
	/**
	 * @see org.example.entity.Auditable#createdDate
	 **/
	public static volatile SingularAttribute<Auditable, LocalDateTime> createdDate;
	
	/**
	 * @see org.example.entity.Auditable#modifiedDate
	 **/
	public static volatile SingularAttribute<Auditable, LocalDateTime> modifiedDate;
	
	/**
	 * @see org.example.entity.Auditable
	 **/
	public static volatile MappedSuperclassType<Auditable> class_;

}

