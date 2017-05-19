package ru.stqa.pft.sandbox;

/**
 * Created by Светлана on 14.05.2017.
 */
public class PointDistance {

    public static void main(String args[]) {

        Point p1 = new Point(0, 0);
        Point p2 = new Point(13, 18);
        Point p3 = new Point(2.5, 4.5);
        Point p4 = new Point(7, 8);
        Point p5 = new Point(0.5,0.7);
        Point p6 = new Point(0,0);
        /*p1.x=0;
        p2.x=10;
        p1.y=0;
        p2.y=20;*/

        System.out.println("Расстояние между точками p1 и p2 = " + p1.distance(p2));
        System.out.println("Расстояние между точками p3 и p4 = " + p3.distance(p4));
        System.out.println("Расстояние между точками p2 и p4 = " + p2.distance(p4));
        System.out.println("Расстояние между точками p5 и p6 = " + p5.distance(p6));
        /*public static double distance(Point p1, Point p2) {
            return Math.sqrt((p2.x-p1.x)*(p2.x-p1.x) + (p2.y-p1.y)*(p2.y-p1.y));
        }*/

    }
}
