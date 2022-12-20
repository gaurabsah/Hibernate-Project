package com.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Main {
	public static void main(String[] args) throws Exception {

		System.out.println("Project Started...");

		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory factory = cfg.buildSessionFactory();

		Employee employee = new Employee();
		employee.setId(777);
		employee.setName("Gaurab Sah");
		employee.setDesignation("Analyst/Software Engineer");
		employee.setDepartment("Java Full Stack");
		System.out.println(employee);

		System.out.println("-----------------------------");

		Employee employee1 = new Employee(122, "Bijay", "Analyst/Software Engineer", "Java Full Stack");
		System.out.println(employee1);

		System.out.println("-----------------------------");

		Session session = factory.openSession();

//		load
		Employee employee2 = (Employee) session.load(Employee.class, 1);
		System.out.println(employee2);

		System.out.println("-----------------------------");

//		get
		Employee emp = session.get(Employee.class, 7);
		System.out.println(emp.getName());
		System.out.println("-----------------------------");

		Transaction txn = session.beginTransaction();

		session.save(employee);
		session.save(employee1);

		txn.commit();
		session.close();
		factory.close();
	}

}
