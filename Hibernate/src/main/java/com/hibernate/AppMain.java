package com.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;

public class AppMain {

	@SuppressWarnings({ "deprecation", "unchecked" })
	public static void main(String[] args) {
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");

		// Session object in hibernate to start the db transaction.
		Session s = config.buildSessionFactory().openSession();

		/***** READING ENTITY WITH SINGLE PROJECTION OBJECT. *****/

		System.out.println("\n===== Reading entity with single projection object. =====\n");

		Criteria c1 = s.createCriteria(Employee.class);

		// Single column.
		Projection pro1 = Projections.property("name");

		// Adding the projection object to the criteria object.
		c1.setProjection(pro1);

		List<String> singlecol = c1.list();

		System.out.println(singlecol);

		/***** READING ENTITY WITH MULTIPLE PROJECTION OBJECTS. *****/

		System.out.println("\n===== Reading entity with multiple projection objects. =====\n");

		Criteria c2 = s.createCriteria(Employee.class);

		// Multiple columns.
		Projection pro2 = Projections.property("name");
		Projection pro3 = Projections.property("designation");
		Projection pro4 = Projections.property("department");

		ProjectionList pList = Projections.projectionList();
		pList.add(pro2);
		pList.add(pro3);
		pList.add(pro4);

		// Adding the projection list object to the criteria object.
		c2.setProjection(pList);

		List<Object[]> multicol = c2.list();

		// Used lambda expression technique of jdk1.8 to display the result list.
		multicol.forEach((o) -> {
			System.out.println("Name: " + o[0] + ", Designation: " + o[1] + ", Department: " + o[2]);
		});

		/***** AGGREGATION USING PROJECTIONS. *****/

		System.out.println("\n===== Aggregation using projections. =====\n");

		Criteria c3 = s.createCriteria(Employee.class);

		// Adding the 'rowCount()' to the criteria object.
		c3.setProjection(Projections.rowCount());

		List<Long> total = c3.list();

		System.out.println("Total records are: " + total);

		/***** GROUPING RECORDS USING PROJECTIONS. *****/

		System.out.println("\n===== Grouping records using projections. =====\n");

		Criteria c4 = s.createCriteria(Employee.class);

		// Adding the 'groupProperty(property_name)' to the criteria object.
		c4.setProjection(Projections.groupProperty("department"));

		List<Employee> results = c4.list();

		System.out.println(results);

		// Closing the session object.
		s.close();
	}

}
