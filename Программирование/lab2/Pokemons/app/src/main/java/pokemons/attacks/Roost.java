package attacks;

import ru.ifmo.se.pokemon.*;

public class Roost extends StatusMove {
    public Roost() {
        super(Type.FLYING, 0, 100);
    }

    @Override
    protected void applySelfEffects(Pokemon pokemon) {
        if (pokemon.getStat(Stat.HP) * 0.5 > pokemon.getHP()) {
            pokemon.addEffect(new Effect().turns(1).stat(Stat.HP, (int) (pokemon.getStat(Stat.HP) * 0.5 + pokemon.getHP())));
        }
    }

    @Override
    protected String describe() {
        return "использует Roost";
    }
}