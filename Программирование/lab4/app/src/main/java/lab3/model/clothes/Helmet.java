package lab3.model.clothes;

import lab3.model.abstracts.Clothes;

public class Helmet extends Clothes {
	public Helmet() {
		super("Каска");
	}

	public void Parametrs() {
		class Durability{
			int value;

			Durability(int value){
				this.value = value;
			}
		}


		var thing = new Durability(50){
			String name = "Прочность";
		};

		System.out.println(thing.name + thing.value);

	}
}