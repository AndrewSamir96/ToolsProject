package com.redhat.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Runner.class)
public abstract class Runner_ {

	public static volatile SingularAttribute<Runner, String> runner_status;
	public static volatile SingularAttribute<Runner, Integer> deliveriesDone;
	public static volatile SingularAttribute<Runner, Double> delivery_fees;
	public static volatile SingularAttribute<Runner, String> name;
	public static volatile SingularAttribute<Runner, Orders> orders;
	public static volatile SingularAttribute<Runner, Integer> id;

}

