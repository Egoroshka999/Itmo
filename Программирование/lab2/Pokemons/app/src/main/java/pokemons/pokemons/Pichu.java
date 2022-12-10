package pokemons;

import attacks.Confide;
import attacks.Rest;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Pichu extends Pokemon {

    public Pichu(String pokemonName, int level){
        super(pokemonName, level);
        setType(Type.ELECTRIC);
        setStats(20, 40, 15, 35, 35, 60);
        setMove(new Rest(), new Confide());
    }

    public Pichu(){
        this("",1);  
    }

}