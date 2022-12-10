package attacks;

import ru.ifmo.se.pokemon.Effect;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Status;
import ru.ifmo.se.pokemon.StatusMove;
import ru.ifmo.se.pokemon.Type;

public class ThunderWave extends StatusMove {
    public ThunderWave(){
        super(Type.ELECTRIC, 0, 90);
    }

    @Override
    protected void applyOppEffects(Pokemon p) {
        // TODO Auto-generated method stub
        super.applyOppEffects(p);
        Effect e = new Effect().chance(0.25).attack(0).condition(Status.PARALYZE);
        p.addEffect(e);
    }
    
    @Override
    protected String describe() {
        // TODO Auto-generated method stub
        return "использует ThunderWave";
    }
    
}
