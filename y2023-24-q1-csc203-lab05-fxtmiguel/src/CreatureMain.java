public class CreatureMain {
    public static void main(String[] args) {
        // Change to constructor calls
        Creature dinosaur = new GrassDinosaur("rose");
        Creature lizard = new FireLizard(600.0);
        Creature turtle = new WaterTurtle();

        dinosaur.doAction();
        lizard.doAction();
        turtle.doAction();

        ((Protector) dinosaur).protect(); // Typecast as needed
//        lizard.protect(); // Comment out
        ((Protector) turtle).protect(); // Typecast as needed

        ((PhotosynthesizingCreature)dinosaur).photosynthesize(); // Typecast as needed
        ((PhotosynthesizingCreature)lizard).photosynthesize(); // Typecast as needed
//        turtle.photosynthesize(); // Comment out
    }
}
