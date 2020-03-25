package ru.stqa.pft.sandbox;

public class MyFirstProgram {
	public static void main(String[] args) { 
		hello("world");
		hello("user");
		hello("Ira");

		Square s = new Square(5);
		System.out.println("Площадь квадрата со стороной " + s.l + " = " + s.area());

		Rectangle r = new Rectangle(4,6);
		System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " = " + r.area());

		Point p1 = new Point (1,3);
		Point p2 = new Point (2,4);

		System.out.println("Расстояние между точками р1 и p2 = " + p1.distance(p2));
	}

	public static void hello(String somebody){
		System.out.println("Hello," + somebody +  "!");
	}

}