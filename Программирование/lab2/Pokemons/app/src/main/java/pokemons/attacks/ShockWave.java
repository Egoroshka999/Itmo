package attacks;

import ru.ifmo.se.pokemon.SpecialMove;
import ru.ifmo.se.pokemon.Type;

public class ShockWave extends SpecialMove{
    public ShockWave() {
        super(Type.ELECTRIC, 60, 100);
    }

    @Override
    protected String describe() {
        return "использует Shock wave";
    }
    

}
