package pokemons;

import ru.ifmo.se.pokemon.*;
import attacks.DoubleTeam;
import attacks.Rest;
import attacks.ShockWave;
import attacks.ThunderWave;

public class Rotom extends Pokemon {

    public Rotom(String pokemonName, int level){
        super(pokemonName, level);
        setType(Type.ELECTRIC, Type.GHOST);
        setStats(50, 50, 77, 95, 77, 91);
        setMove(new Rest(), new ShockWave(), new DoubleTeam(), new ThunderWave());
    }

    public Rotom(){
        this("",1);  
    }

}

