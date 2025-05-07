package org.example.entity;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(User.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class User_ extends org.example.entity.Auditable_ {

	public static final String PASSWORD = "password";
	public static final String SESSIONS = "sessions";
	public static final String POST = "post";
	public static final String SURNAME = "surname";
	public static final String NAME = "name";
	public static final String IS_ADMIN = "isAdmin";
	public static final String DEPARTMENT = "department";
	public static final String USER_ID = "userId";
	public static final String EMAIL = "email";

	
	/**
	 * @see org.example.entity.User#password
	 **/
	public static volatile SingularAttribute<User, String> password;
	
	/**
	 * @see org.example.entity.User#sessions
	 **/
	public static volatile ListAttribute<User, Session> sessions;
	
	/**
	 * @see org.example.entity.User#post
	 **/
	public static volatile SingularAttribute<User, String> post;
	
	/**
	 * @see org.example.entity.User#surname
	 **/
	public static volatile SingularAttribute<User, String> surname;
	
	/**
	 * @see org.example.entity.User#name
	 **/
	public static volatile SingularAttribute<User, String> name;
	
	/**
	 * @see org.example.entity.User#isAdmin
	 **/
	public static volatile SingularAttribute<User, Boolean> isAdmin;
	
	/**
	 * @see org.example.entity.User#department
	 **/
	public static volatile SingularAttribute<User, String> department;
	
	/**
	 * @see org.example.entity.User
	 **/
	public static volatile EntityType<User> class_;
	
	/**
	 * @see org.example.entity.User#userId
	 **/
	public static volatile SingularAttribute<User, Long> userId;
	
	/**
	 * @see org.example.entity.User#email
	 **/
	public static volatile SingularAttribute<User, String> email;

}

