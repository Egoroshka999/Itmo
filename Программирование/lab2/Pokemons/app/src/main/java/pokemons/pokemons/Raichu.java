package pokemons;

import ru.ifmo.se.pokemon.*;
import attacks.Confide;
import attacks.Discharge;
import attacks.Rest;
import attacks.Swagger;

public class Raichu extends Pikachu{
    public Raichu(String pokemonName, int level){
        super(pokemonName, level);
        setType(Type.ELECTRIC);
        setStats(60, 90, 55, 90, 80, 110);
        setMove(new Rest(), new Confide(), new Discharge(), new Swagger());
    }

    public Raichu(){
        this("",1);  
    }
}
