/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P2.turtle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import P2.turtle.Point;

public class TurtleSoup {

	/**
	 * Draw a square.
	 *
	 * @param turtle     the turtle context
	 * @param sideLength length of each side
	 */
	public static void drawSquare(Turtle turtle, int sideLength) {
		for (int i = 0; i < 4; ++i) {
			turtle.forward(sideLength);
			turtle.turn(90);
		}
	}

	/**
	 * Determine inside angles of a regular polygon.
	 * <p>
	 * There is a simple formula for calculating the inside angles of a polygon; you
	 * should derive it and use it here.
	 *
	 * @param sides number of sides, where sides must be > 2
	 * @return angle in degrees, where 0 <= angle < 360
	 */
	public static double calculateRegularPolygonAngle(int sides) {
		if (sides <= 2)
			throw new RuntimeException("The sides must be > 2.");
		// 内角 = 180 - 外角
		return 180 - 360.0 / sides;
	}

	/**
	 * Determine number of sides given the size of interior angles of a regular
	 * polygon.
	 * <p>
	 * There is a simple formula for this; you should derive it and use it here.
	 * Make sure you *properly round* the answer before you return it (see
	 * java.lang.Math). HINT: it is easier if you think about the exterior angles.
	 *
	 * @param angle size of interior angles in degrees, where 0 < angle < 180
	 * @return the integer number of sides
	 */
	public static int calculatePolygonSidesFromAngle(double angle) {
		if (angle <= 0 || angle > 180)
			throw new RuntimeException("The angle must bestween 0 and 180(excluded 0 and 180).");
		return (int) Math.round(360 / (180 - angle));
	}

	/**
	 * Given the number of sides, draw a regular polygon.
	 * <p>
	 * (0,0) is the lower-left corner of the polygon; use only right-hand turns to
	 * draw.
	 *
	 * @param turtle     the turtle context
	 * @param sides      number of sides of the polygon to draw
	 * @param sideLength length of each side
	 */
	public static void drawRegularPolygon(Turtle turtle, int sides, int sideLength) {
		double angle = 180 - calculateRegularPolygonAngle(sides); // 旋转角度 = 外角
		for (int i = 0; i < sides; ++i) {
			turtle.forward(sideLength);
			turtle.turn(angle);
		}
	}

	/**
	 * Given the current direction, current location, and a target location,
	 * calculate the Bearing towards the target point.
	 * <p>
	 * The return value is the angle input to turn() that would point the turtle in
	 * the direction of the target point (targetX,targetY), given that the turtle is
	 * already at the point (currentX,currentY) and is facing at angle
	 * currentBearing. The angle must be expressed in degrees, where 0 <= angle <
	 * 360.
	 * <p>
	 * HINT: look at http://en.wikipedia.org/wiki/Atan2 and Java's math libraries
	 *
	 * @param currentBearing current direction as clockwise from north
	 * @param currentX       current location x-coordinate
	 * @param currentY       current location y-coordinate
	 * @param targetX        target point x-coordinate
	 * @param targetY        target point y-coordinate
	 * @return adjustment to Bearing (right turn amount) to get to target point,
	 *         must be 0 <= angle < 360
	 */
	public static double calculateBearingToPoint(double currentBearing, int currentX, int currentY, int targetX,
			int targetY) {
		// 计算target和current与x轴之间的角度，并转换为角度制
		double deltaAngle = Math.atan2(targetX - currentX, targetY - currentY) * 180 / Math.PI;
		deltaAngle -= currentBearing;
		if (deltaAngle < 0)
			deltaAngle += 360;
		return deltaAngle;
	}

	/**
	 * Given a sequence of points, calculate the Bearing adjustments needed to get
	 * from each point to the next.
	 * <p>
	 * Assumes that the turtle starts at the first point given, facing up (i.e. 0
	 * degrees). For each subsequent point, assumes that the turtle is still facing
	 * in the direction it was facing when it moved to the previous point. You
	 * should use calculateBearingToPoint() to implement this function.
	 *
	 * @param xCoords list of x-coordinates (must be same length as yCoords)
	 * @param yCoords list of y-coordinates (must be same length as xCoords)
	 * @return list of Bearing adjustments between points, of size 0 if (# of
	 *         points) == 0, otherwise of size (# of points) - 1
	 */
	public static List<Double> calculateBearings(List<Integer> xCoords, List<Integer> yCoords) {
		int n = xCoords.size();
		if (yCoords.size() != n)
			throw new RuntimeException("The lengths of xCoords and yCoords must be same.");
		if (n < 2)
			return new ArrayList<>();

		List<Double> bearingList = new ArrayList<>();
		double currentBearing = 0;
		for (int i = 1; i < n; ++i) {
			double deltaAngle = calculateBearingToPoint(currentBearing, xCoords.get(i - 1), yCoords.get(i - 1),
					xCoords.get(i), yCoords.get(i));
			bearingList.add(deltaAngle);
			currentBearing += deltaAngle;
		}
		return bearingList;
	}

	/**
	 * Given a set of points, compute the convex hull, the smallest convex set that
	 * contains all the points in a set of input points. The gift-wrapping algorithm
	 * is one simple approach to this problem, and there are other algorithms too.
	 *
	 * @param points a set of points with xCoords and yCoords. It might be empty,
	 *               contain only 1 point, two points or more.
	 * @return minimal subset of the input points that form the vertices of the
	 *         perimeter of the convex hull
	 */
	public static Set<Point> convexHull(Set<Point> points) {
		// 使用Monotone chain, a.k.a. Andrew's algorithm求凸包
		int n = points.size();
		// 点数为0, 1, 2
		if (n < 3)
			return points;
		Point[] pointsArray = points.toArray(new Point[0]);

		// 按照xy座标排序
		Arrays.sort(pointsArray, (a, b) -> (a.x() == b.x() ? Double.compare(a.y(), b.y()) : Double.compare(a.x(), b.x())));

		int top = 0;
		int[] stack = new int[n + 1];
		boolean[] used = new boolean[n];

		// 求上凸包
		stack[top] = 0;
		++top;
		for (int i = 1; i < n; ++i) {
			while (top >= 2 && crossProduct(pointsArray[stack[top - 2]], pointsArray[stack[top - 1]],
					pointsArray[stack[top - 1]], pointsArray[i]) >= 0) {
				// 存在凹陷
				--top;
				used[stack[top]] = false;
			}
			used[i] = true;
			stack[top] = i;
			++top;
		}

		// 求下凸包
		int tmp = top;
		for (int i = n - 2; i >= 0; --i) {
			if (used[i])
				continue;
			while (top > tmp && crossProduct(pointsArray[stack[top - 2]], pointsArray[stack[top - 1]],
					pointsArray[stack[top - 1]], pointsArray[i]) >= 0) {
				// 存在凹陷
				--top;
				used[stack[top]] = false;
			}
			used[i] = true;
			stack[top] = i;
			++top;
		}

		// 删除共线点
		used[0] = true;
		--top;
		if (top >= 3) {
			for (int i = 0; i < top; ++i) {
				int pre = stack[i == 0 ? top - 1 : i - 1];
				int nxt = stack[i == top - 1 ? 0 : i + 1];
				int cur = stack[i];
				// 三点共线
				if (crossProduct(pointsArray[pre], pointsArray[cur], pointsArray[cur], pointsArray[nxt]) == 0)
					used[cur] = false;
			}
		}

		Set<Point> convexHullSet = new HashSet<>();
		for (int i = 0; i < top; ++i)
			if (used[stack[i]])
				convexHullSet.add(pointsArray[stack[i]]);
		if (convexHullSet.isEmpty()) {
			// 所有点共线
			convexHullSet.add(pointsArray[0]);
			convexHullSet.add(pointsArray[n - 1]);
		}
		return convexHullSet;
	}

	/**
	 * Calculate the cross product of two vector.
	 *
	 * @param aBegin, aEnd, the begin and end points of vector a.
	 * @param bBegin, bEnd, the begin and end points of vector b.
	 * @return the cross product of vector a and vector b.
	 */
	private static double crossProduct(Point aBegin, Point aEnd, Point bBegin, Point bEnd) {
		double x1 = aEnd.x() - aBegin.x(), y1 = aEnd.y() - aBegin.y();
		double x2 = bEnd.x() - bBegin.x(), y2 = bEnd.y() - bBegin.y();
		return x1 * y2 - x2 * y1;
	}

	/**
	 * Draw your personal, custom art.
	 * <p>
	 * Many interesting images can be drawn using the simple implementation of a
	 * turtle. For this function, draw something interesting; the complexity can be
	 * as little or as much as you want.
	 *
	 * @param turtle the turtle context
	 */
	public static void drawPersonalArt(Turtle turtle) {
		Random random = new Random();
		for (int step = 0; step < 72; ++step) {
			turtle.turn(1);
			for (int i = 0; i < step; ++i) {
				// 随机着色
				switch (random.nextInt(4)) {
					case 0:
						turtle.color(PenColor.RED);
						break;
					case 1:
						turtle.color(PenColor.BLUE);
						break;
					case 2:
						turtle.color(PenColor.CYAN);
						break;
					default:
						turtle.color(PenColor.GRAY);
				}
				turtle.forward(i);
				turtle.turn(10);
			}
			turtle.turn(90);
			turtle.forward(step);
			turtle.turn(90);
			// 返回到原点附件
			for (int i = step - 1; i >= 0; --i) {
				// 随机着色
				switch (random.nextInt(4)) {
					case 0:
						turtle.color(PenColor.RED);
						break;
					case 1:
						turtle.color(PenColor.BLUE);
						break;
					case 2:
						turtle.color(PenColor.CYAN);
						break;
					default:
						turtle.color(PenColor.GRAY);
				}
				turtle.forward(i);
				turtle.turn(-10);
			}
		}
	}

	/**
	 * Main method.
	 * <p>
	 * This is the method that runs when you run "java TurtleSoup".
	 *
	 * @param args unused
	 */
	public static void main(String args[]) {
		DrawableTurtle turtle = new DrawableTurtle();

		// drawSquare(turtle, 40);
		// drawRegularPolygon(turtle, 360, 1);
		drawPersonalArt(turtle);

		// draw the window
		turtle.draw();
	}

}
