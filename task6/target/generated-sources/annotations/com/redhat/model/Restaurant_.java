package com.redhat.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Restaurant.class)
public abstract class Restaurant_ {

	public static volatile SingularAttribute<Restaurant, Integer> totalCanceled;
	public static volatile SingularAttribute<Restaurant, Integer> totalCompleted;
	public static volatile SingularAttribute<Restaurant, String> name;
	public static volatile SetAttribute<Restaurant, Orders> orders;
	public static volatile SingularAttribute<Restaurant, Integer> id;
	public static volatile SingularAttribute<Restaurant, User> user;
	public static volatile SetAttribute<Restaurant, Meal> meals;

}

