package ru.stqa.pft.sandbox;

/**
 * Created by Светлана on 13.05.2017.
 */
public class Point {
    public double x;
    public double y;


    public Point(double x, double y) {
        this.x = x;
        this.y = y;

    }

    double distance(Point a) {
        return Math.sqrt( Math.pow(x-a.x, 2) + Math.pow(y-a.y, 2) );

    }
}
