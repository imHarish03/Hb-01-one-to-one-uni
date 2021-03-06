package lab.basic.Hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import lab.basic.Hibernate.demo.entity.Instructor;
import lab.basic.Hibernate.demo.entity.InstructorDetail;

public class CreateDemo {
	public static void main(String[] args) {
		SessionFactory factory = new Configuration()
				// Hibernate will load default file
				.configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).buildSessionFactory();

		try {
			Session session = factory.getCurrentSession();
			// Create the Object
			Instructor ins = new Instructor("Yogi", "raj", "yogi@gmail.com");
			InstructorDetail detail = new InstructorDetail("yogi", "Playing Games with girls");
			ins.setInstructorDetail(detail);

			// Start transaction
			session.beginTransaction();
			session.save(ins);

			session.getTransaction().commit();

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			factory.close();
		}
	}
}
