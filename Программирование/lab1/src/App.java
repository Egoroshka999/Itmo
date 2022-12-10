/*
Лабораторная работа №1
Вариант 368100
Группа R3115 Деревягин Егор
*/
import java.util.Random;
import java.lang.Math;

public class App
{
    // Объявление констант и инициализация массива для задания 1
    private static final  MAX_VALUE_A = 21;
    private static final short MIN_VALUE_A = 5;
    private static final short N_ELEMENTS_A = (MAX_VALUE_A - MIN_VALUE_A) / 2 + 1;

    private static final short[] a = new short[N_ELEMENTS_A];

    private static void exerciseFirst()
    {
        
        for(short value = MIN_VALUE_A, i = 0; value <= MAX_VALUE_A; value += 2, i++)
        {
            a[i] = value;
            System.out.print(value + " ");
        }
    }


    // Объявление констанат и инициализация массива для задания 2
    private static final int N_ELEMENTS_B = 17;

    private static final float[] x = new float[N_ELEMENTS_B];

    private static void exerciseSecond()
    {

        for(int i = 0; i < N_ELEMENTS_B; i++)
        {
            // Генерация чисел на интервале [-2.0, 11.0]

            x[i] = (new Random()).nextFloat() * 13 - 2;
            System.out.printf("%.2f ", x[i]);
        }

    }

    // Объявление констант и инициализация двумерного массива для задания 3

    private static final int N_COLS = 17;
    private static final int N_ROWS = 9;

    private static final double[][] arr = new double[N_ROWS][N_COLS];




    private static void exerciseThird()
    {
        for(int i = 0; i < N_ROWS; i++)
        {
            for(int j = 0; j < N_COLS; j++)
            {
                if(a[i] == 17)
                    arr[i][j] = Math.tan(2 * Math.pow((3/4 * (x[j] - 1)),3));
                else if (a[i] == 9 || a[i] == 15 || a[i] == 19 || a[i] == 21)
                    arr[i][j] = Math.pow((Math.atan((x[j] + 4.5) / 13) - 1), 2);
                else
                    arr[i][j] = Math.atan(1 / Math.pow((Math.pow((Math.exp(Math.exp(1/3 * (Math.pow(((x[j] / (x[j] + 1/4)) / 2),Math.exp(x[j])) - 1)))), ((Math.log(Math.abs(x[j])) - 4) / Math.cos(x[j])))), 3));
            }
        }
    }

    // Вывод двумерного массива из задания 3

    private static void exerciseFourth()
    {
        for (int i = 0; i < N_ROWS; i++)
        {
            for (int j = 0; j < N_COLS; j++)
            System.out.format("%.5f ", arr[i][j]);

            System.out.print('\n');
        }
    }

    public static void main(String[] args)
    {
        
        exerciseFirst();
        System.out.print("\n\n");
        exerciseSecond();
        exerciseThird();
        System.out.print("\n\n");
        exerciseFourth();
    }
}
