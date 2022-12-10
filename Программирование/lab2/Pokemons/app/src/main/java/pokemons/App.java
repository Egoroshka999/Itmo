import ru.ifmo.se.pokemon.*;

import pokemons.Mantine;
import pokemons.Mantyke;
import pokemons.Pichu;
import pokemons.Raichu;
import pokemons.Pikachu;
import pokemons.Rotom;


public class App {
    public static void main(String[] args) throws Exception {
        Battle b = new Battle();
        
        Rotom rot = new Rotom("Манга", 1);
        Mantine manti = new Mantine("Ранобэ", 1);
        Mantyke manty = new Mantyke("Аниме", 1);
        b.addFoe(rot);
        b.addFoe(manti);
        b.addFoe(manty);
        
        Pichu pic = new Pichu("Лололошка", 1);
        Pikachu pika = new Pikachu("Жаба", 1);
        Raichu rai = new Raichu("Мемы", 1);
        b.addAlly(pic);
        b.addAlly(pika);
        b.addAlly(rai);

        b.go();
    }
}
