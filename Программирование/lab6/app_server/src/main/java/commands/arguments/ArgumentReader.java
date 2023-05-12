package commands.arguments;

import java.io.PrintStream;
import java.util.Scanner;

public class ArgumentReader {
    public static String readString(PrintStream stdout, Scanner scanner, String prompt) {
        stdout.print(prompt + " > ");
        do {
            try {
                String s = scanner.nextLine();
                if (s.isEmpty()) throw new Exception();
                return s;
            } catch (Exception e) {
                stdout.print(prompt + " > ");
            }
        } while (true);
    }

    public static int readInt(PrintStream stdout, Scanner scanner, String prompt) {
        stdout.print(prompt + " > ");
        do {
            try {
                String s = scanner.nextLine();
                int i = Integer.valueOf(s);
                return i;
            } catch (Exception e) {
                stdout.print(prompt + " > ");
            }
        } while (true);
    }

    public static long readLong(PrintStream stdout, Scanner scanner, String prompt) {
        stdout.print(prompt + " > ");
        do {
            try {
                String s = scanner.nextLine();
                long l = Long.valueOf(s);
                return l;
            } catch (Exception e) {
                stdout.print(prompt + " > ");
            }
        } while (true);
    }

    public static float readFloat(PrintStream stdout, Scanner scanner, String prompt) {
        stdout.print(prompt + " > ");
        do {
            try {
                String s = scanner.nextLine();
                float f = Float.valueOf(s);
                return f;
            } catch (Exception e) {
                stdout.print(prompt + " > ");
            }
        } while (true);
    }

    public static double readDouble(PrintStream stdout, Scanner scanner, String prompt) {
        stdout.print(prompt + " > ");
        do {
            try {
                String s = scanner.nextLine();
                double d = Double.valueOf(s);
                return d;
            } catch (Exception e) {
                stdout.print(prompt + " > ");
            }
        } while (true);
        
    }

    public static <E extends Enum<E>> E readEnum(PrintStream stdout, Scanner scanner, String prompt, Class<E> enumClass) {
        stdout.print(prompt + " > ");
        do {
            try {
                String s = scanner.nextLine();
                return Enum.valueOf(enumClass, s.trim().toUpperCase());
            } catch(Exception e) {
                stdout.print(prompt + " > ");
            }
            
        } while(true);
    }
}