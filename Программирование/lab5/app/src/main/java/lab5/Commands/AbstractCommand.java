package lab5.Commands;

import lab5.Collections.*;

import java.util.Set;
import java.util.TreeSet;

/**
 * ����������� �����, �� �������� ����������� ��� �������
 *
 * @author ��������� ����, P3115
 */
public abstract class AbstractCommand{
    protected String name;
    protected String help;

    /**
     * ����� ���������� �������
     * 
     * @param args              ���������, ������� ��������� �������
     * @param treeSet           ���������, � ������� �������� ������������
     * @param commandsManager   ������ ������ CommandsManager
     */
    public abstract void execute(String args[], TreeSet<City> treeSet, CommandsManager commandsManager);

    /**
     * @return �������� �������
     */
    public String getName() {
        return name;
    }

    /** 
     * @return �������� ������ �������
     */
    public String getHelp() {
        return help;
    }
}
