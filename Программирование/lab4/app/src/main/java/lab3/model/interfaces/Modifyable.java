package lab3.model.interfaces;

public interface Modifyable<T> {
	public void addModifier(T object);
	public void removeModifier(T object);
}
