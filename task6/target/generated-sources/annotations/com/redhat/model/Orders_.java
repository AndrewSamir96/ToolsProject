package com.redhat.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Orders.class)
public abstract class Orders_ {

	public static volatile SingularAttribute<Orders, String> order_status;
	public static volatile SingularAttribute<Orders, Double> total_price;
	public static volatile SingularAttribute<Orders, Restaurant> restaurant;
	public static volatile SingularAttribute<Orders, Integer> id;
	public static volatile SingularAttribute<Orders, Runner> runner;
	public static volatile SingularAttribute<Orders, User> user;
	public static volatile SetAttribute<Orders, Meal> meals;

}

