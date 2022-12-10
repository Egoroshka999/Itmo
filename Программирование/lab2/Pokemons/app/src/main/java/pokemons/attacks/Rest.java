package attacks;

import ru.ifmo.se.pokemon.Effect;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Status;
import ru.ifmo.se.pokemon.StatusMove;
import ru.ifmo.se.pokemon.Type;

public class Rest extends StatusMove {
    public Rest(){
        super(Type.PSYCHIC, 0, 100);
    }

    @Override
    protected void applySelfEffects(Pokemon p) {
        // TODO Auto-generated method stub
        super.applySelfEffects(p);
        Effect e = new Effect().condition(Status.SLEEP).turns(2);
        p.restore();
        p.addEffect(e);
    }
    
    @Override
    protected String describe() {
        // TODO Auto-generated method stub
        return "использует Rest";
    }
    
}
