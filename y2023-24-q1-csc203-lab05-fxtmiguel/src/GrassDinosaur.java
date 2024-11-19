
public class GrassDinosaur extends PhotosynthesizingCreature implements Protector{
    public String flowerType;
    public GrassDinosaur(String flowerType) {
        this.flowerType =flowerType;
    }

    @Override
    public void doAction()
    {
        System.out.printf("The dinosaur's %s flower released a pleasing aroma.%n", flowerType);


        }

    @Override
    public void protect() {


        System.out.println("The dinosaur's flower released a paralyzing spore!");
    }
}
