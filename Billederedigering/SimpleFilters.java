
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
		image_.pixelsUpdated();
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
		image_.pixelsUpdated();
    }

	/**
     * Invert the image
	 *
     */
	public void invert()
    {
        for(Pixel i : image_.getPixels())
		{
			i.setValue(255-i.getValue());
		}
		image_.pixelsUpdated();
    }

	/**
     * Solarize the image
	 *
     */
	public void solarize()
    {
        for(Pixel i : image_.getPixels())
		{
			if(i.getValue()<128)
			{
				i.setValue(255-i.getValue());
			}
		}
		image_.pixelsUpdated();
    }
}
