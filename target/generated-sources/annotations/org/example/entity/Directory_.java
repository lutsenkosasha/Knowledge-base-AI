package org.example.entity;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Directory.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class Directory_ extends org.example.entity.Auditable_ {

	public static final String DIRECTORY_SIZE = "directorySize";
	public static final String DIRECTORY_ID = "directoryId";
	public static final String DEPARTMENT = "department";
	public static final String DIRECTORY_NAME = "directoryName";
	public static final String COUNT_FILES = "countFiles";

	
	/**
	 * @see org.example.entity.Directory#directorySize
	 **/
	public static volatile SingularAttribute<Directory, Long> directorySize;
	
	/**
	 * @see org.example.entity.Directory#directoryId
	 **/
	public static volatile SingularAttribute<Directory, Long> directoryId;
	
	/**
	 * @see org.example.entity.Directory#department
	 **/
	public static volatile SingularAttribute<Directory, String> department;
	
	/**
	 * @see org.example.entity.Directory
	 **/
	public static volatile EntityType<Directory> class_;
	
	/**
	 * @see org.example.entity.Directory#directoryName
	 **/
	public static volatile SingularAttribute<Directory, String> directoryName;
	
	/**
	 * @see org.example.entity.Directory#countFiles
	 **/
	public static volatile SingularAttribute<Directory, Integer> countFiles;

}

