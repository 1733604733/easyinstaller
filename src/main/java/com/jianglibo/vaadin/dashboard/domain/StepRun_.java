package com.jianglibo.vaadin.dashboard.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-08-30T15:07:38.828+0800")
@StaticMetamodel(StepRun.class)
public class StepRun_ extends BaseEntity_ {
	public static volatile SingularAttribute<StepRun, InstallStep> installStep;
	public static volatile SingularAttribute<StepRun, JschExecuteResult> result;
	public static volatile SingularAttribute<StepRun, Integer> order;
	public static volatile SingularAttribute<StepRun, String> name;
}
