package circletestapp;

public class CircleTestApp {

    public static void main(String[] args) {
        CircleClass circle = new CircleClass();
        circle.fillCircles();

        System.out.println("Список всех кругов:");
        System.out.println(circle.getCircles());

        System.out.println("Черные круги:");
        System.out.println(circle.findBlackCircle());
    }
}