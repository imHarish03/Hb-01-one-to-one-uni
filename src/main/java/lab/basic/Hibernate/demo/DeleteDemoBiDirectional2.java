package lab.basic.Hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import lab.basic.Hibernate.demo.entity.Instructor;
import lab.basic.Hibernate.demo.entity.InstructorDetail;

public class DeleteDemoBiDirectional2 {
	public static void main(String[] args) {
		SessionFactory factory = new Configuration()
				// Hibernate will load default file
				.configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).buildSessionFactory();

		try {
			Session session = factory.getCurrentSession();

			int id = 5;

			// Start transaction
			session.beginTransaction();
			InstructorDetail insDetail = (InstructorDetail) session.get(InstructorDetail.class, id);

			// break bidirectional link
			insDetail.getInstructor().setInstructorDetail(null);

			// remove th object reference
			session.delete(insDetail);
			session.getTransaction().commit();

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			factory.close();
		}
	}
}
