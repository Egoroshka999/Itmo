package attacks;

import ru.ifmo.se.pokemon.PhysicalMove;
import ru.ifmo.se.pokemon.Type;


public class Slam extends PhysicalMove {
    public Slam(){
        super(Type.NORMAL, 80, 75);
    }

    @Override
    protected String describe() {
        return "использует Slam";
    }
    
}
