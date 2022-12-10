package attacks;

import ru.ifmo.se.pokemon.PhysicalMove;
import ru.ifmo.se.pokemon.Type;

public class AerialAce extends PhysicalMove{
    public AerialAce(){
        super(Type.FLYING, 60, 100);
    }

    @Override
    protected String describe() {
        return "использует Aerial Ace";
    }
}