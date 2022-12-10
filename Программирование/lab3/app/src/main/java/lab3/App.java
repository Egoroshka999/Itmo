package lab3;

import lab3.model.enums.*;
import lab3.model.clothes.*;
import java.util.ArrayList;
import java.util.List;
import lab3.model.bodyparts.*;
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
			System.out.println(ments.get(i).enter(tvstudio));
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
        
		Torso menttorso = new Torso();
		Mundir mentmundir = new Mundir();
		menttorso.addClothes(mentmundir);
		for(var i = 3; i < 7; i++){
			ments.add(new Ment(Integer.toString(i+1),new Head(), menttorso, new Arm(Side.LEFT), new Arm(Side.RIGHT), new Leg(Side.LEFT), new Leg(Side.RIGHT)));		
		}

		for(var i = 3; i < 7; i++){
			System.out.println(ments.get(i).leave(outside));
			System.out.println(ments.get(i).enter(tvstudio));
			System.out.println(ments.get(i).lookLike(HumanEffect.SHABBY));
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
        
	}
}