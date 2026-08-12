package org.oop;

abstract class Shape {
    abstract double getArea();
}

class Circle extends Shape {
    private double r;
    Circle(double r) {
        this.r = r;
    }
    double getArea() {
        return 3.14 * r * r;
    }
}

class Rectangle extends Shape {
    private double l;
    private double h;
    Rectangle(double l, double h) {
        this.l = l;
        this.h = h;
    }
    double getArea() {
        return l * h;
    }
}

interface Playable{
    void playmusic();
}

class MusicPlayer implements Playable{
    @Override
    public void playmusic() {
        System.out.println("Playing music");
    }
}

public class abstraction {
    public static void main(String[] args) {
        Circle c = new Circle(4);
        Rectangle r = new Rectangle(5, 10);

        System.out.println("Dien tich hinh tron: " + c.getArea());
        System.out.println("Dien tich hinh chu nhat: " + r.getArea());

        MusicPlayer player = new MusicPlayer();
        player.playmusic();
    }
}
