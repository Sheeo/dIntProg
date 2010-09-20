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
	
	private double sigma_;
	
	private int travelDistance_;
	
	public AdvancedFilters(Image image)
	{
		image_ = image;
		sigma_ = 0.84089642;
		travelDistance_ = 3;
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
		Image blurredImage = new Image(image_.getWidth(), image_.getHeight(), "Blurred image");
		for(int i = 0; i < image_.getWidth(); i++)
		{
			for(int j = 0; j < image_.getHeight(); j++)
			{
				blurredImage.getPixel(i,j).setValue( average( image_.getNeighbours(i,j) ) );
			}
		}
		image_ = blurredImage;
		image_.pixelsUpdated();
	}
	
	/**
	 * Blurs the image using gaussian distribution
	 */
	public void gaussianBlur()
	{
		Image blurredImage = new Image(image_.getWidth(), image_.getHeight(), "Blurred image");
		
		for(int i = 0; i < image_.getWidth(); i++)
		{
			for(int j = 0; j < image_.getHeight(); j++)
			{
				blurredImage.getPixel(i,j).setValue( gaussianWeighedAverage(i, j) );
			}
		}
		image_ = blurredImage;
		image_.pixelsUpdated();
	}
	
	/**
	 * Scale the image by factor
	 * Important: Cannot enlarge image!
	 *
	 * @param factor Scale by this factor
	 */
	public void scale(double factor)
	{
		if(factor >= 1)
			return;
		Image scaledImage = new Image((int)Math.round(image_.getWidth()*factor), (int)Math.round(image_.getHeight()*factor), "Scaled image");
		
		for(int i = 0; i < scaledImage.getWidth(); i++)
		{
			for(int j = 0; j < scaledImage.getHeight(); j++)
			{
				scaledImage.getPixel(i,j).setValue(image_.getPixel((int)Math.round(i/factor), (int)Math.round(j/factor)).getValue());
			}
		}
		image_=scaledImage;
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
	 * Returns the gaussian-weighed average of neighbouring pixels
	 */
	private int gaussianWeighedAverage(int i, int j)
	{
		int sum = 0;
		for(int dI = -travelDistance_; dI < travelDistance_; dI++)
		{
			for(int dJ = -travelDistance_; dJ < travelDistance_; dJ++)
			{
				if(i+dI > 0 && i+dI < image_.getWidth() && j+dJ > 0 && j+dJ < image_.getHeight())
				{
					sum += (image_.getPixel(i+dI, j+dJ).getValue() * G(Math.abs(dI), Math.abs(dJ)));
				}
			}
		}
		return sum;
	}
	
	/**
	 * Returns the value of:
	 * (1/(2*pi*sigma_^2))*e^(-u^2+v^2)/(2*sigma_^2)
	 */
	private double G(int u, int v)
	{
		return (1/(2* Math.PI * ( Math.pow(sigma_,2) ) ) ) * Math.pow(Math.E, -(Math.pow(u, 2) + Math.pow(v, 2)) / (2 * Math.pow(sigma_,2)) );
	}
}
