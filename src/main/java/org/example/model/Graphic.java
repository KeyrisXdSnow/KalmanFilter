package org.example.model;

import javafx.geometry.Point2D;

import java.util.List;

public interface Graphic {
    List<Point2D> generatePoints(int[] xArray, int a, int b, double stepAmount);
}
