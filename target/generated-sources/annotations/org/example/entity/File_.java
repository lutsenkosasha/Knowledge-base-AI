package org.example.entity;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(File.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class File_ extends org.example.entity.Auditable_ {

	public static final String FILE_NAME = "fileName";
	public static final String FILE_SIZE = "fileSize";
	public static final String DIRECTORY = "directory";
	public static final String FILE_ID = "fileId";

	
	/**
	 * @see org.example.entity.File#fileName
	 **/
	public static volatile SingularAttribute<File, String> fileName;
	
	/**
	 * @see org.example.entity.File#fileSize
	 **/
	public static volatile SingularAttribute<File, Long> fileSize;
	
	/**
	 * @see org.example.entity.File
	 **/
	public static volatile EntityType<File> class_;
	
	/**
	 * @see org.example.entity.File#directory
	 **/
	public static volatile SingularAttribute<File, Directory> directory;
	
	/**
	 * @see org.example.entity.File#fileId
	 **/
	public static volatile SingularAttribute<File, Long> fileId;

}

