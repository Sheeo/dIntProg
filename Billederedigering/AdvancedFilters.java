import java.util.List;
import java.lang.Math.*;

/**
 * Advanced imagefilters
 * 
 * @author Mathias Rav and Michael Søndergaard
 * @version 16/9-2010
 */
public class AdvancedFilters
{

	private Image image_;

	public AdvancedFilters(Image image)
	{
		image_ = image;
	}

	/**
	 * Mirror the image
	 */
	public void mirror()
	{
		Image mirroredImage = new Image(image_.getWidth(), image_.getHeight(), "Mirrored Image");
		for(int i = 0; i< image_.getWidth(); i++) {
			for(int j = 0; j < image_.getHeight(); j++) {
				// Mirror at width - i - 1
				mirroredImage.getPixel(image_.getWidth()-i-1, j).setValue(image_.getPixel(i,j).getValue());
			}
		}
		image_=mirroredImage;
		image_.pixelsUpdated();
	}

	/**
	 * Flip the image
	 */
	public void flip()
	{
		Image flippedImage = new Image(image_.getWidth(), image_.getHeight(), "Flipped Image");
		for(int i = 0; i< image_.getWidth(); i++) {
			for(int j = 0; j < image_.getHeight(); j++) {
				// Flip at height - j - 1
				flippedImage.getPixel(i, image_.getHeight()-j-1).setValue(image_.getPixel(i,j).getValue());
			}
		}
		image_=flippedImage;
		image_.pixelsUpdated();
	}

	/**
	 * Rotates the image 90 degrees CCW
	 */
	public void rotate()
	{
		Image rotatedImage = new Image(image_.getHeight(), image_.getWidth(), "Rotated Image");
		for(int i = 0; i< image_.getWidth(); i++) {
			for(int j = 0; j < image_.getHeight(); j++) {
				// Rotate
				rotatedImage.getPixel(j, i).setValue(image_.getPixel(i,j).getValue());
			}
		}
		image_=rotatedImage;
		image_.pixelsUpdated();
	}

	/**
	 * Blurs the image using average-neighbour values
	 */
	public void blur()
	{
		for(int i = 0; i < image_.getWidth(); i++)
		{
			for(int j = 0; j < image_.getHeight(); j++)
			{
				image_.getPixel(i,j).setValue( average( image_.getNeighbours(i,j) ) );
			}
		}
		image_.pixelsUpdated();
	}

	/**
	 * Returns average value of neighbouring pixels
	 */
	private int average(List<Pixel> pixels)
	{
		int sum = 0;
		for(Pixel p : pixels)
		{
			sum += p.getValue();
		}
		return sum / pixels.size();
	}
	
	/**
	 * Returns the value of:
	 * (1/(2*pi*sigma^2))*e^(-u^2+v^2)/(2*sigma^2)
	 */
	/*private double G(int u, int v, double sigma)
	{
		return (1/(2*PI*( Math.pow(sigma,2) )))*Math.pow(E, (Math.pow(u, 2) + Math.pow(v, 2) / 2* Math.pow(sigma,2)));
	}*/
}
