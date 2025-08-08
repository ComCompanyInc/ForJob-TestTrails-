package circletestapp;

public enum CircleColour {
    WHITE,
    BLACK;
    
    public String getCircleColour() {
        return switch (this) {
            case WHITE -> "Белый";
            case BLACK -> "Черный";
        };
    }
}
