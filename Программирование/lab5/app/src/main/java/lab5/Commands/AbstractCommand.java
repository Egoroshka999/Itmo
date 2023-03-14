package lab5.Commands;

import lab5.Collections.*;

import java.util.Set;
import java.util.TreeSet;

/**
 * јбстрактный класс, от которого наследуютс€ все команды
 *
 * @author ƒерев€гин ≈гор, P3115
 */
public abstract class AbstractCommand{
    protected String name;
    protected String help;

    /**
     * ћетод выполнени€ команды
     * 
     * @param args              аргументы, которые принимает команда
     * @param treeSet           коллекци€, с которой работает пользователь
     * @param commandsManager   объект класса CommandsManager
     */
    public abstract void execute(String args[], TreeSet<City> treeSet, CommandsManager commandsManager);

    /**
     * @return название команды
     */
    public String getName() {
        return name;
    }

    /** 
     * @return описание работы команды
     */
    public String getHelp() {
        return help;
    }
}
