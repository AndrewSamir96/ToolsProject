package com.redhat.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(User.class)
public abstract class User_ {

	public static volatile SingularAttribute<User, String> role;
	public static volatile SetAttribute<User, Restaurant> restaurant;
	public static volatile SingularAttribute<User, String> name;
	public static volatile SetAttribute<User, Orders> orders;
	public static volatile SingularAttribute<User, Integer> id;

}

