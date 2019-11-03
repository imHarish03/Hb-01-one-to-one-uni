package lab.basic.Hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import lab.basic.Hibernate.demo.entity.Instructor;
import lab.basic.Hibernate.demo.entity.InstructorDetail;

public class GetDemoBiDirectional {
	public static void main(String[] args) {
		SessionFactory factory = new Configuration()
				// Hibernate will load default file
				.configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).buildSessionFactory();

		try {
			Session session = factory.getCurrentSession();

			int id = 3;

			// Start transaction
			session.beginTransaction();
			InstructorDetail insDetail = (InstructorDetail) session.get(InstructorDetail.class, id);

			System.out.println(insDetail.getHobby());
			System.out.println(insDetail.getInstructor().getFirstName());

			session.getTransaction().commit();

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			factory.close();
		}
	}
}
