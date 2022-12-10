package pokemons;

import ru.ifmo.se.pokemon.*;
import attacks.AerialAce;
import attacks.Amnesia;
import attacks.Slam;

public class Mantyke extends Pokemon {

    public Mantyke(String pokemonName, int level){
        super(pokemonName, level);
        setType(Type.WATER, Type.FLYING);
        setStats(45, 20, 50, 60, 120, 50);
        setMove(new AerialAce(), new Amnesia(), new Slam());
    }

    public Mantyke(){
        this("",1);  
    }

}

