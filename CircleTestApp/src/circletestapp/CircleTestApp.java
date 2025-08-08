package circletestapp;

public class CircleTestApp {

    public static void main(String[] args) {
        CircleClass circle = new CircleClass();
        circle.fillCircles();
        
        System.out.println("Сгенерированный список шаров:");
        System.out.println(circle.getCircles());
        
        System.out.println("Нахождение шара:");
        System.out.println(circle.findBlackCircle());
    }
}
