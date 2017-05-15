package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;
/**
 * Created by Светлана on 14.05.2017.
 */
public class PointTest {
    @Test
    public void testDistance1() {
        Point p1 = new Point(0,0);
        Point p2 = new Point(0,0);
        Assert.assertEquals(p1.distance(p2), 0.0);
    }

    @Test
    public void testDistance2() {
        Point p1 = new Point(0,0);
        Point p2 = new Point(10,10);
        Assert.assertEquals(String.format("%.2f",p1.distance(p2)), "14,14");
    }

    @Test
    public void testDistance3() {
        Point p1 = new Point(0.5,0.7);
        Point p2 = new Point(0,0);
        Assert.assertEquals(p1.distance(p2), 0.8602325267042626);
    }

    @Test
    public void testDistance4() {
        Point p1 = new Point(-5,-10);
        Point p2 = new Point(-99.15,-99.6);
        Assert.assertEquals(String.format("%.2f",p1.distance(p2)), "129,97");
    }

    @Test
    public void testDistance5() {
        Point p1 = new Point(-5,-10);
        Point p2 = new Point(99.15,99.6);
        Assert.assertEquals(String.format("%.2f",p1.distance(p2)), "151,19");
    }
}
