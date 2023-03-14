package lab5;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeSet;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import lab5.Collections.*;
import lab5.Commands.CommandsManager;


/**
 * �����, �������������� ���������������� ����
 * 
 * @author ��������� ����, P3115
 */
public class InputHandler {
    public static String[] arguments = null;

    /**
     * ������ ������� ��������� � ���������� ������
     *
     * @param arguments ���� �������� ���������
     * @return          �������������� ������� ���������
     */
    static City TransformArguments(String[] arguments) {
        try {
            for (int i = 0; i < arguments.length; i++)
                if (i != 3 && arguments[i].isEmpty()) throw new Exception();
            String name = arguments[0];
            Coordinates coordinates = new Coordinates(Double.parseDouble(arguments[1]), Float.parseFloat(arguments[2]));            
            Integer area = Integer.parseInt(arguments[4]);
            Integer population = Integer.parseInt(arguments[5]); 
            Double metersAboveSeaLevel = Double.parseDouble(arguments[6]);
            Climate climate = Climate.valueOf(arguments[7]);
            Government government = Government.valueOf(arguments[8]);
            StandardOfLiving standardOfLiving = StandardOfLiving.valueOf(arguments[9]);
            Human governor = new Human(arguments[10]);
            City city = new City(name, coordinates, area, population, metersAboveSeaLevel, climate, government, standardOfLiving, governor);
            if (arguments[3] != null)
                city.setCreationDate(ZonedDateTime.parse(arguments[3]));
            return city;
        } catch (Exception e) {
            System.out.println("�� ������� �������� ������, ��������� ������ ������� �������!");
            e.printStackTrace();
            return null;
        }

     }


    /**
     * ��������� ��������� �� �����
     *
     * @param args          �������� ����� �������� ���������
     * @param treeSet       ���������
     */
    static void InputLoader(String[] args, TreeSet<City> TreeSet) {
        arguments = args;
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append(System.lineSeparator());
            }
            br.close();
            
            JsonArray array = JsonParser.parseString(stringBuilder.toString()).getAsJsonArray();
            for (int i = 0; i < array.size(); i++) {
                JsonObject object = array.get(i).getAsJsonObject();
                String[] values = new String[11];
                values[0] = object.get("name").getAsString();
                values[1] = object.get("x").getAsString();
                values[2] = object.get("y").getAsString();
                values[3] = object.get("creation date").getAsString();
                values[4] = object.get("area").getAsString();
                values[5] = object.get("population").getAsString();
                values[6] = object.get("meters above sea level").getAsString();
                values[7] = object.get("climate").getAsString();
                values[8] = object.get("government").getAsString();
                values[9] = object.get("standard of living").getAsString();
                values[10] = object.get("governor").getAsString();
                City city = TransformArguments(values);
                if (city != null) TreeSet.add(city);
            }
        } catch (FileNotFoundException e) {
            System.out.println("�������� ��� �����. ���������� ������ ��� ���.");
            System.exit(-1);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("��������� ������");
        }
    }

    /**
     * ��������� ��������� �� ����������������� �����
     *
     * @param commandsManager ������ ������ CommandsManager
     * @return ������ ����������
     */
    public static City ArgumentsReader(CommandsManager commandsManager) {
        String[] arguments = new String[11];
        arguments[0] = InputValidation("������� �������� ������:", false, commandsManager);
        arguments[1] = InputValidation("������� ���������� x(������� �����):", Double.MIN_VALUE, Double.MAX_VALUE, commandsManager);
        arguments[2] = InputValidation("������� ���������� y(����� �����):", Float.MIN_VALUE, Float.MAX_VALUE, commandsManager);
        arguments[3] = null;
        arguments[4] = InputValidation("������� ������� ������ ��^2:", 1, Integer.MAX_VALUE, commandsManager);
        arguments[5] = InputValidation("������� ����������� ��������� � ������:", 1, Integer.MAX_VALUE, commandsManager);
        arguments[6] = InputValidation("������� ������� ������ ��� ������� ����:", 0.0d, Double.MAX_VALUE, commandsManager);
        arguments[7] = "";
        while (!Arrays.stream(Climate.values()).anyMatch((t) -> t.name().equals(arguments[7]))) {
            arguments[7] = InputValidation("�������� ��� �������: (HUMIDSUBTROPICAL, HUMIDCONTINENTAL, MEDITERRANIAN, STEPPE, SUBARCTIC)", true, commandsManager);
            if (commandsManager.isScript()) break;
        }

        arguments[8] = "";
        while (!Arrays.stream(Government.values()).anyMatch((t) -> t.name().equals(arguments[8]))) {
            arguments[8] = InputValidation("�������� ��� ���������� �������: (ARISTOCRACY, KRITARCHY, PATRIARCHY, THEOCRACY, TOTALITARIANISM)", false, commandsManager);
            if (commandsManager.isScript()) break;
        }
        arguments[9] = "";
        while (!Arrays.stream(StandardOfLiving.values()).anyMatch((t) -> t.name().equals(arguments[9]))) {
            arguments[9] = InputValidation("�������� �������� ����� ���������: (ULTRA_HIGH, HIGH, LOW)", true, commandsManager);
            if (commandsManager.isScript()) break;
        }
        arguments[10] = InputValidation("������� ��� ���� ������:", false, commandsManager);
        return TransformArguments(arguments);
    }

    /**
     * ��������� �������� ������
     * 
     * @param message           ���������� ���������
     * @param maybenull         �����-�� ���� ���� null
     * @param commandsManager   ������ ������ CommandsManager
     * @return                  ���������� �������� ����
     */
    static String InputValidation(String message, Boolean maybenull, CommandsManager commandsManager) {
        String line = "";
        try {
            Scanner scanner = new Scanner(System.in);
            do {
                if (!commandsManager.isScript()) System.out.print(message + " ");
                line = commandsManager.isScript() ? commandsManager.getScriptBufferedReader().readLine() : scanner.nextLine();
            } while (!maybenull && line.isEmpty());
        } catch (FileNotFoundException e) {
            System.out.println("���� �� ������");
        } catch (NullPointerException e) {
        } catch (Exception e) {
            System.out.print("������� �������� ������. ");
            if (commandsManager.isScript()) return line;
        }
        return line;
    }

    /**
     * ��������� �������� ������
     * 
     * @param message            ���������� ���������
     * @param min                ����� ������� ���������
     * @param max                ������ ������� ���������
     * @param commandsManager    ������ ������ CommandsManager
     * @return                   ���������� �������� ����
     */
    static String InputValidation(String message, int min, int max, CommandsManager commandsManager) {
        String line = "";
        try {
            Scanner scanner = new Scanner(System.in);
            do {
                if (!commandsManager.isScript()) System.out.print(message + " ");
                line = commandsManager.isScript() ? commandsManager.getScriptBufferedReader().readLine() : scanner.nextLine();
                if (!commandsManager.isScript()) {
                    if ((Integer.parseInt(line) > max || Integer.parseInt(line) < min)) {
                        System.out.print("�������� ��� ���������. ");
                        line = InputValidation(message, min, max, commandsManager);
                    }
                    Integer.parseInt(line);
                }
            } while (line.isEmpty());
            return line;
        } catch (FileNotFoundException e) {
            System.out.println("���� �� ������");
            return null;
        } catch (Exception e) {
            System.out.print("������� �������� ������. ");
            if (commandsManager.isScript()) return line;
            else return InputValidation(message, min, max, commandsManager);
        }
    }

    /**
     * ��������� �������� ������
     *
     * @param message           ���������� ���������
     * @param min               ����� ������� ���������
     * @param max               ������ ������� ���������
     * @param commandsManager   ������ ������ CommandsManager
     * @return                  ���������� �������� ����
     */
    static String InputValidation(String message, long min, long max, CommandsManager commandsManager) {
        String line = "";
        try {
            Scanner scanner = new Scanner(System.in);
            do {
                if (!commandsManager.isScript()) System.out.print(message + " ");
                line = commandsManager.isScript() ? commandsManager.getScriptBufferedReader().readLine() : scanner.nextLine();
                if (!commandsManager.isScript()) {
                    if ((Long.parseLong(line) > max || Long.parseLong(line) < min)) {
                        System.out.print("�������� ��� ���������.");
                        line = InputValidation(message, min, max, commandsManager);
                    }
                    Long.parseLong(line);
                }
            } while (line.isEmpty());
            return line;
        } catch (FileNotFoundException e) {
            System.out.println("���� �� ������");
            return null;
        } catch (Exception e) {
            System.out.print("������� �������� ������. ");
            if (commandsManager.isScript()) return line;
            else return InputValidation(message, min, max, commandsManager);
        }
    }

    /**
     * ��������� �������� ������
     * 
     * @param message           ���������� ���������
     * @param min               ����� ������� ���������
     * @param max               ������ ������� ���������
     * @param commandsManager   ������ ������ CommandsManager
     * @return                  ���������� �������� ����
     */
    static String InputValidation(String message, Double min, Double max, CommandsManager commandsManager) {
        String line = "";
        try {
            Scanner scanner = new Scanner(System.in);
            do {
                if (!commandsManager.isScript()) System.out.print(message + " ");
                line = commandsManager.isScript() ? commandsManager.getScriptBufferedReader().readLine() : scanner.nextLine();
                if (!commandsManager.isScript()) {
                    if ((Double.parseDouble(line) > max || Double.parseDouble(line) <= min)) {
                        System.out.print("�������� ��� ���������. ");
                        line = InputValidation(message, (double) min, (double) max, commandsManager);
                    }
                    Double.parseDouble(line);
                }
            } while (line.isEmpty());
            return line;
        } catch (FileNotFoundException e) {
            System.out.println("���� �� ������");
            return null;
        } catch (Exception e) {
            System.out.print("������� �������� ������.");
            if (commandsManager.isScript()) return line;
            else return InputValidation(message, (double) min, (double) max, commandsManager);
        }
    }
    

     /**
     * ��������� �������� ������
     * 
     * @param message           ���������� ���������
     * @param min               ����� ������� ���������
     * @param max               ������ ������� ���������
     * @param commandsManager   ������ ������ CommandsManager
     * @return                  ���������� �������� ����
     */
    static String InputValidation(String message, Float min, Float max, CommandsManager commandsManager) {
        String line = "";
        try {
            Scanner scanner = new Scanner(System.in);
            do {
                if (!commandsManager.isScript()) System.out.print(message + " ");
                line = commandsManager.isScript() ? commandsManager.getScriptBufferedReader().readLine() : scanner.nextLine();
                if (!commandsManager.isScript()) {
                    if ((Float.parseFloat(line) > max || Float.parseFloat(line) <= min)) {
                        System.out.print("�������� ��� ���������. ");
                        line = InputValidation(message, (Float) min, (Float) max, commandsManager);
                    }
                    Float.parseFloat(line);
                }
            } while (line.isEmpty());
            return line;
        } catch (FileNotFoundException e) {
            System.out.println("���� �� ������");
            return null;
        } catch (Exception e) {
            System.out.print("������� �������� ������. ");
            if (commandsManager.isScript()) return line;
            else return InputValidation(message, (Float) min, (Float) max, commandsManager);
        }
    }
}
