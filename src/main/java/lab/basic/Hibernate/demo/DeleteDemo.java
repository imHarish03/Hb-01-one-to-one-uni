package lab.basic.Hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import lab.basic.Hibernate.demo.entity.Instructor;
import lab.basic.Hibernate.demo.entity.InstructorDetail;

public class DeleteDemo {
	public static void main(String[] args) {
		SessionFactory factory = new Configuration()
				// Hibernate will load default file
				.configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).buildSessionFactory();

		try {
			Session session = factory.getCurrentSession();

			// Start transaction
			session.beginTransaction();
			int id = 1;
			Instructor ins = (Instructor) session.get(Instructor.class, id);

			if (ins != null) {
				session.delete(ins);
			}

			session.getTransaction().commit();

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			factory.close();
		}
	}
}
