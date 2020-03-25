package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {
    @Test
    public void testDistance(){
        Point p1 = new Point (1,3);
        Point p2 = new Point (2,4);
        Assert.assertEquals(p1.distance(p2), 1.4142135623730951);
    }
}
