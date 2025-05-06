package org.example.entity;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(KnowledgeBase.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class KnowledgeBase_ extends org.example.entity.Auditable_ {

	public static final String KNOWLEDGE_BASE_ID = "knowledgeBaseId";
	public static final String DIRECTORY = "directory";

	
	/**
	 * @see org.example.entity.KnowledgeBase#knowledgeBaseId
	 **/
	public static volatile SingularAttribute<KnowledgeBase, Long> knowledgeBaseId;
	
	/**
	 * @see org.example.entity.KnowledgeBase
	 **/
	public static volatile EntityType<KnowledgeBase> class_;
	
	/**
	 * @see org.example.entity.KnowledgeBase#directory
	 **/
	public static volatile SingularAttribute<KnowledgeBase, Directory> directory;

}

