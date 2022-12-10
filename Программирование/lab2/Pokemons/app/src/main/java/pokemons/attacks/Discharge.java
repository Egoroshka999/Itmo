package attacks;
import ru.ifmo.se.pokemon.*;

public class Discharge extends SpecialMove {

    public Discharge() {
        super(Type.ELECTRIC, 80, 100);
    }

    @Override
    protected void applyOppEffects(Pokemon p) {
        super.applyOppEffects(p);
        Effect e = new Effect().chance(0.3).condition(Status.PARALYZE);
        p.addEffect(e);
        
    }

    @Override
    protected String describe() {
        return "использует Discharge";
    }

}
