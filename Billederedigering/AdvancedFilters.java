
/**
 * Advanced imagefilters
 * 
 * @author Mathias Rav and Michael Søndergaard
 * @version 16/9-2010
 */
public class AdvancedFilters
{
	
	private Image image_;
	
    public void AdvancedFilters(Image image)
	{
		image_ = image;
	}
	
	/**
	 * Mirror the image
	 */
	public void mirror()
	{
		Image mirroredImage = new Image(image_.getWidth(), image_.getHeight(), "Whatever");
		for(int i = 0; i< image_.getWidth(); i++) {
			for(int j = 0; j < image_.getHeight(); j++) {
				// Mirror at width - i - 1
				mirroredImage.getPixel(image_.getWidth()-i-1, j).setValue(image_.getPixel(i,j).getValue());
			}
		}
		image_=mirroredImage;
		image_.pixelsUpdated();
	}
}
