
/**
 * A collection of simple image filters
 * 
 * @author Mathias Rav and Michael Søndergaard
 * @version 16/9-2010
 */
public class SimpleFilters
{
    private Image image_;

    /**
     * Constructor for objects of class SimpleFilters
     */
    public SimpleFilters(Image image)
    {
        image_ = image;
    }

    /**
     * Brightens the image by amount
     * 
     * @param  amount   brighten by this amount 
     */
    public void brighten(int amount)
    {
        for(Pixel i : image_.getPixels())
		{
			i.setValue(i.getValue()+amount);
		}
    }

	/**
     * Darkens the image by amount
     * 
     * @param  amount   brighten by this amount 
     */
	public void darken(int amount)
    {
        for(Pixel i : image_.getPixels())
		{
			i.setValue(i.getValue()-amount);
		}
    }
}
