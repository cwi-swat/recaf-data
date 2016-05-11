package manual;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import generated.Course;
import generated.Student;

public class BidirectionalTest {

	@Test
	public void test1() {
		Course course = Course.New();
		course.name("SICP");
		Student s1 = Student.New();
		s1.birthYear(1990);
		s1.name("Juan");
		course.students(new ArrayList<>());
		course.students().add(s1);
		assertEquals(Integer.valueOf(1990), course.students().iterator().next().birthYear());
		assertEquals(course.name(), course.students().iterator().next().course().name());
	}

}
