package org.example.model;

import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Sinusoid implements Graphic{

    public Sinusoid() {
    }

    public List<Point2D> generatePoints(int[] xArray, int a, int b, double stepAmount){

        var point2DList = new ArrayList<Point2D>();

        double c = 2 *Math.PI*(1/255.0);
        Function<Integer,Double> function = (_x) -> (a + b * Math.sin(_x*c));
        for (int i=0; i<stepAmount+1; i++) {
            point2DList.add(new Point2D(i, function.apply(xArray[i])));
        }
        return  point2DList;
    }

    public List<Point2D> generateCurvePoints(int[] xArray,double stepAmount) {
        var point2DList = new ArrayList<Point2D>();

        for (int x=0; x<stepAmount+1; x++) {
            point2DList.add(new Point2D(x, xArray[x]));
        }
        return  point2DList;
    }
}
