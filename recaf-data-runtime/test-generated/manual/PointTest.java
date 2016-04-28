package manual;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import generated.Point;

public class PointTest {

	@Test
	public void test1() {
		Point point = Point.New();
		point.x(3);
		point.y(4);
		System.out.println(point.distance());
		assertEquals(Integer.valueOf(5), point.distance());

	}

}
