package org.example.entity;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.time.LocalDateTime;

@StaticMetamodel(AuditLog.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class AuditLog_ {

	public static final String ENTITY_NAME = "entityName";
	public static final String DETAILS = "details";
	public static final String ID = "id";
	public static final String OPERATION = "operation";
	public static final String TIMESTAMP = "timestamp";

	
	/**
	 * @see org.example.entity.AuditLog#entityName
	 **/
	public static volatile SingularAttribute<AuditLog, String> entityName;
	
	/**
	 * @see org.example.entity.AuditLog#details
	 **/
	public static volatile SingularAttribute<AuditLog, String> details;
	
	/**
	 * @see org.example.entity.AuditLog#id
	 **/
	public static volatile SingularAttribute<AuditLog, Long> id;
	
	/**
	 * @see org.example.entity.AuditLog
	 **/
	public static volatile EntityType<AuditLog> class_;
	
	/**
	 * @see org.example.entity.AuditLog#operation
	 **/
	public static volatile SingularAttribute<AuditLog, String> operation;
	
	/**
	 * @see org.example.entity.AuditLog#timestamp
	 **/
	public static volatile SingularAttribute<AuditLog, LocalDateTime> timestamp;

}

