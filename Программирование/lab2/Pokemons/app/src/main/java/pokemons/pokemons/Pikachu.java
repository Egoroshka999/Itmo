package pokemons;

import ru.ifmo.se.pokemon.*;
import attacks.Confide;
import attacks.Discharge;
import attacks.Rest;

public class Pikachu extends Pichu{
    public Pikachu(String pokemonName, int level){
        super(pokemonName, level);
        setType(Type.ELECTRIC);
        setStats(35, 55, 40, 50, 50, 90);
        setMove(new Rest(), new Confide(), new Discharge());
    }

    public Pikachu(){
        this("",1);  
    }
}
