
package Domain;


public class AudioVisual extends Material{
    private String brand;

    public AudioVisual(String brand, String name, String signature, int availability, String description) {
        super(name, signature, availability, description);
        this.brand = brand;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    

    @Override
    public String toString() {
        return super.toString() + "\nAudioVisual{" + "marco=" + brand + '}';
    }
    
    
}
