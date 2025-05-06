package org.example.entity;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.time.LocalDate;
import java.time.LocalTime;

@StaticMetamodel(Message.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class Message_ extends org.example.entity.Auditable_ {

	public static final String DATE = "date";
	public static final String SESSION = "session";
	public static final String MESSAGE_ID = "messageId";
	public static final String TEXT = "text";
	public static final String TIME = "time";

	
	/**
	 * @see org.example.entity.Message#date
	 **/
	public static volatile SingularAttribute<Message, LocalDate> date;
	
	/**
	 * @see org.example.entity.Message#session
	 **/
	public static volatile SingularAttribute<Message, Session> session;
	
	/**
	 * @see org.example.entity.Message#messageId
	 **/
	public static volatile SingularAttribute<Message, Long> messageId;
	
	/**
	 * @see org.example.entity.Message#text
	 **/
	public static volatile SingularAttribute<Message, String> text;
	
	/**
	 * @see org.example.entity.Message#time
	 **/
	public static volatile SingularAttribute<Message, LocalTime> time;
	
	/**
	 * @see org.example.entity.Message
	 **/
	public static volatile EntityType<Message> class_;

}

