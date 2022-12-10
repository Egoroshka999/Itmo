package pokemons;

import ru.ifmo.se.pokemon.*;
import attacks.AerialAce;
import attacks.Amnesia;
import attacks.Roost;
import attacks.Slam;

public class Mantine extends Mantyke {

    public Mantine(String pokemonName, int level){
        super(pokemonName, level);
        setType(Type.WATER, Type.FLYING);
        setStats(85, 40, 70, 80, 140, 70);
        setMove(new AerialAce(), new Amnesia(), new Slam(), new Roost());
    }

    public Mantine(){
        this("",1);  
    }

}


