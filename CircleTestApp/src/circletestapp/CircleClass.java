package circletestapp;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CircleClass {
    private List<String> circles = new ArrayList<>();
    
    public void fillCircles() {
        Random random = new Random();
        
        // Заполняю белые шары
        for(int i = 0; i < random.nextInt(10, 20); i++)
        {
            circles.add(CircleColour.WHITE.getCircleColour());
        }
        
        // Добавляю в лист один черный шар
        circles.set(random.nextInt(0, circles.size()), CircleColour.BLACK.getCircleColour());
    }

    public String findBlackCircle() {
        for(String circle : circles) {
            if(circle.equals(CircleColour.BLACK.getCircleColour())) {
                return "Черный шар найден под индексом - " + circles.indexOf(circle) + "\n\n[i] Любой массив (как и список) начинает счет с нулевого индекса";
            }
        }
        
        return "черный шар не найден!";
    }
    
    public List<String> getCircles() {
        return circles;
    }
}