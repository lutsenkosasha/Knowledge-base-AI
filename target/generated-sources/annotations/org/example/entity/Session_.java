package org.example.entity;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Session.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class Session_ extends org.example.entity.Auditable_ {

	public static final String MESSAGES = "messages";
	public static final String SESSION_ID = "sessionId";
	public static final String USER = "user";
	public static final String DIRECTORY = "directory";

	
	/**
	 * @see org.example.entity.Session#messages
	 **/
	public static volatile ListAttribute<Session, Message> messages;
	
	/**
	 * @see org.example.entity.Session#sessionId
	 **/
	public static volatile SingularAttribute<Session, Long> sessionId;
	
	/**
	 * @see org.example.entity.Session
	 **/
	public static volatile EntityType<Session> class_;
	
	/**
	 * @see org.example.entity.Session#user
	 **/
	public static volatile SingularAttribute<Session, User> user;
	
	/**
	 * @see org.example.entity.Session#directory
	 **/
	public static volatile SingularAttribute<Session, Directory> directory;

}

