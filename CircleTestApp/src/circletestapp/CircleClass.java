package circletestapp;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CircleClass {
    private List<String> circles = new ArrayList<>();

    public void fillCircles() {
        Random random = new Random();

        // Создаем круги белого цвета
        for(int i = 0; i < random.nextInt(10, 20); i++)
        {
            circles.add(CircleColour.WHITE.getCircleColour());
        }

        // Добавляем один черный круг случайно
        circles.set(random.nextInt(0, circles.size()), CircleColour.BLACK.getCircleColour());
    }

    public String findBlackCircle() {
        for(String circle : circles) {
            if(circle.equals(CircleColour.BLACK.getCircleColour())) {
                return "Черный круг находится на позиции - " + circles.indexOf(circle) + "\n\n[i] Все круги (кроме одного) имеют белый цвет в начальном состоянии";
            }
        }

        return "Черный круг не найден!";
    }

    public List<String> getCircles() {
        return circles;
    }
}