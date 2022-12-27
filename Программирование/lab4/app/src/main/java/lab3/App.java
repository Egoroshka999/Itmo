package lab3;

import lab3.model.enums.*;
import lab3.model.exceptions.OpenError;
import lab3.model.clothes.*;
import java.util.ArrayList;
import java.util.List;
import lab3.model.abstracts.BodyPart;
import lab3.model.locations.Location;
import lab3.model.professions.Ment;

public class App {
	public static void main(String[] args) {
		Location outside = new Location("Улица");
		Location tvstudio = new Location("Ателье");

		List<Ment> ments = new ArrayList<Ment>();
		for(var i = 0; i < 3; i++){
			ments.add(new Ment(Integer.toString(i+1)));
		}

		for(var i = 0; i < 3; i++){
			try {
				System.out.println(ments.get(i).enter(tvstudio));
			} catch (Exception e) {
				System.out.println(ments.get(i).secondenter(tvstudio));
			}
			
		}

		for(var i = 0; i < 3; i++){
			System.out.println(ments.get(i).admit(HumanEffect.FEAR));
			System.out.println(ments.get(i).admit(HumanEffect.NAUSEA)); 
		}
			
		System.out.println(ments.get(2).remember(HumanEffect.LIGHTNESS));

		for(Side i:Side.values()){
			ments.get(2).getArm(i).addModifier(BodyPartModifier.NOT_FELT);
		}

		for(Side i:Side.values()){
			ments.get(2).getLeg(i).addModifier(BodyPartModifier.NOT_FELT);
		}

		System.out.println(ments.get(2).toString());

		for(var i = 0; i < 2; i++){
			System.out.println(ments.get(i).confirm(HumanEffect.LIGHTNESS));
		}
        
		BodyPart.Torso menttorso = new BodyPart.Torso();
		Mundir mentmundir = new Mundir();
		menttorso.addClothes(mentmundir);
		for(var i = 3; i < 7; i++){
			ments.add(new Ment(Integer.toString(i+1),new BodyPart.Head(), menttorso, new BodyPart.Arm(Side.LEFT), new BodyPart.Arm(Side.RIGHT), new BodyPart.Leg(Side.LEFT), new BodyPart.Leg(Side.RIGHT)));		
		}

		for(var i = 3; i < 7; i++) {
			try {
				System.out.println(ments.get(i).leave(outside));
				System.out.println(ments.get(i).enter(tvstudio));
				System.out.println(ments.get(i).lookLike(HumanEffect.SHABBY));
			} catch (OpenError e){
				System.out.println(ments.get(i).secondenter(tvstudio));
				System.out.println(ments.get(i).lookLike(HumanEffect.SHABBY));
			}
		}
		

		mentmundir.addModifier(ClothesModifier.DIRTY);
		mentmundir.addModifier(ClothesModifier.TORN);
		for(var i = 3; i < 7; i++){
			ments.get(i).getTorso().addClothes(mentmundir);
		}

		ments.get(3).getArm(Side.LEFT).addModifier(BodyPartModifier.BANDAGED);
        System.out.println(ments.get(3).toString());

		ments.get(4).getLeg(Side.RIGHT).addModifier(BodyPartModifier.BANDAGED);
        Boot boot1 = new Boot();
        ments.get(4).getLeg(Side.RIGHT).addClothes(boot1);
        System.out.println(ments.get(4).toString());

        ments.get(4).getLeg(Side.RIGHT).removeClothes(boot1);

		Galosh galosh1 = new Galosh();
		ments.get(4).getLeg(Side.RIGHT).addClothes(galosh1);
        System.out.println(ments.get(4).toString());

		for(var i = 3; i < 7; i++){
			ments.get(i).getHead().addModifier(BodyPartModifier.BANDAGED);
		}
        
		Helmet helmet = new Helmet();
		helmet.addModifier(ClothesModifier.LOOSE);
		for(var i = 3; i < 7; i++){
			ments.get(i).getHead().addClothes(helmet);
		}

		Patch patch1 = new Patch();
		ments.get(3).getHead().getForehead().addClothes(patch1);
		Patch patch2 = new Patch();
		ments.get(4).getHead().getNose().addClothes(patch2);
		Patch patch3 = new Patch();
		ments.get(5).getHead().getEye(Side.LEFT).addClothes(patch3);
		Patch patch4 = new Patch();
		ments.get(6).getHead().getCheeks().addClothes(patch4);

		for(var i = 3; i < 7; i++){
			System.out.println(ments.get(i).toString());
		}
        

		class Bench{
			String color;
			String description;

			Bench(String color, String description){
				this.color = color;
				this.description = description;
			}

			public void describe() {
				System.out.format("Создана %s скамья, %s\n", color, description);
			}
		}

		Bench bench = new Bench("розовая", "стоящая в углу");
		bench.describe();
	}
}