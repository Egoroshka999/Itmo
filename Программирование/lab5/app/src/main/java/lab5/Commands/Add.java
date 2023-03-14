package lab5.Commands;

import java.util.TreeSet;

import lab5.InputHandler;
import lab5.Collections.City;

/**
 * ����� ������� add
 * 
 * @author ��������� ����, P3115
 */
public class Add extends AbstractCommand {
    public Add() {
        name = "add";
        help = "������� � ����������� ����� ������ ���������� � ���������";
    }

    /**
     * ��������� ����� ������� � ���������
     * 
     * @param args              �� ��������� ���������
     * @param treeSet           ���������, � ������� ����������� �������
     * @param commandsManager   ������ ������ CommandsManager
     */
    @Override
    public void execute(String[] args, TreeSet<City> treeSet, CommandsManager commandsManager) {
        try {
            if (args.length > 0) {
                System.out.println("�� ������ ����� ������� �� ��������� ���������");
                if (commandsManager.isScript()) {
                    commandsManager.commandRewider();
                }
            } else {
                if (treeSet.add(InputHandler.ArgumentsReader(commandsManager)) && !commandsManager.isNotPrintAdd()) {
                    System.out.println("������� �������� � ���������");
                }
            }
        } catch (NullPointerException ignored) {
        }
    }
}
