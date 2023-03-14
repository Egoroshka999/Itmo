package lab5.Collections;


/**
 * ����� ���� City
 * 
 * @author ��������� ����, P3115
 */
public class Human {
    private String name; //���� �� ����� ���� null, ������ �� ����� ���� ������

    /**
     * ����������� ����
     * 
     * @param name ���
     */
    public Human(String name){
        setName(name);
    }

    /**
     * @param name ��� ����
     * @throws NullPointerException ���� ��� �� ������� ��� �����
     */
    public void setName(String name) throws NullPointerException {
        if (name != null && !name.isEmpty())
            this.name = name;
        else throw new NullPointerException();
    }

    /**
     * @return ��� ����
     */
    public String getName() {
        return name;
    }

    /**
     * @return ��������� ������������� �������
     */
    @Override
    public String toString() {
        return name;
    }
}
