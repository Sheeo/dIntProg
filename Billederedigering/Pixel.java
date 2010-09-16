
/**
 * A class modelling the single pixel of an image
 * 
 * @author Thomas G. Kristensen
 * @version 1.0
 */
public class Pixel
{
    // The value of the pixel
    private int value;

    /**
     * The constructor of a new pixel
     * 
     * @param value The initial value of the pixel
     */
    public Pixel(int value)
    {
        this.value = value;
    }
    
    /**
     * Sets the current value of the pixel to the value given.
     * If the parameter is below zero or exceeds 255, it is set
     * to zero or 255 acordingly.
     * 
     * @param value The new value of the pixel
     */      
    public void setValue(int value)
    {
        if(value < 0)
            this.value = 0;
        else if(value > 255)
            this.value = 255;
        else
            this.value = value;
    }
    
    /**
     * Returns the value in the pixel
     * 
     * @return The value in the pixel
     */
    public int getValue()
    {
        return this.value;
    }
}
