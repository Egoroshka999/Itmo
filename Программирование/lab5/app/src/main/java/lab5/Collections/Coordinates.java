package lab5.Collections;

/**
 * ����� � ������������ City
 * 
 * @author ��������� ����, P3115
 */
public class Coordinates {
    private double x;
    private Float y; //���� �� ����� ���� null

    /**
     * ����������� ������
     * 
     * @param x ���������� x
     * @param y ���������� y
     */
    public Coordinates(double x, Float y) {
        setX(x);
        setY(y);
    }

    /**
     * @param x �������� ������� ���������� x
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * @return ���������� x
     */
    public double getX() {
        return x;
    }

    /**
     * @param y �������� ������� ���������� y
     */
    public void setY(Float y) {
        this.y = y;
    }

    /**
     * @return ���������� y
     */
    public Float getY() {
        return y;
    }


    /**
     * @return ��������� ������������� ���������
     */
    @Override
    public String toString() {
        return "����������:" +
                "x=" + x +
                ", y=" + y;
    }
}