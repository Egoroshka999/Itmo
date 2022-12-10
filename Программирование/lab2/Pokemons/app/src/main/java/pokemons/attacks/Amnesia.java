package attacks;

import ru.ifmo.se.pokemon.Effect;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Stat;
import ru.ifmo.se.pokemon.StatusMove;
import ru.ifmo.se.pokemon.Type;

public class Amnesia extends StatusMove {
    public Amnesia() {
        super(Type.PSYCHIC, 0, 100);
    }
    
    @Override
    protected void applySelfEffects(Pokemon p) {
        Effect e = new Effect().stat(Stat.SPECIAL_DEFENSE, +2);
        p.addEffect(e);
    }

    @Override
    protected String describe() {
        return "использует Amnesia";
    }
}
