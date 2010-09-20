import java.lang.Math.*;
/**
 * Simplified testing class
 * 
 * @author Michael Søndergaard and Mathias Rav
 * @version 20/9-2010
 */
public class TestDriver
{
    public static void testFlip()
	{
		Image i = new Image("strand.jpg");
		AdvancedFilters filter = new AdvancedFilters(i);
		filter.flip();
	}
	
	public static void testRotate()
	{
		Image i = new Image("strand.jpg");
		AdvancedFilters filter = new AdvancedFilters(i);
		filter.rotate();
	}
	
	public static void testBlur()
	{
		Image i = new Image("strand.jpg");
		AdvancedFilters filter = new AdvancedFilters(i);
		filter.blur();
	}
	
	public static void testGaussianBlur()
	{
		Image i = new Image("strand.jpg");
		AdvancedFilters filter = new AdvancedFilters(i);
		filter.gaussianBlur();
	}
	
	public static void testScale()
	{
		Image i = new Image("strand.jpg");
		AdvancedFilters filter = new AdvancedFilters(i);
		filter.scale(0.5);
	}
	
	/* Was used to test the G-function on AdvancedFilters
	public static void testG()
	{
		Image i = new Image("strand.jpg");
		AdvancedFilters filter = new AdvancedFilters(i);
		System.out.println("Assert that G(1,1, 0.84089642) returns approx. 0.05472157");
		System.out.println(filter.G(1,1, 0.84089642));
		
		System.out.println("Assert that G(0,0, 0.84089642) returns approx. 0.22508352");
		System.out.println(filter.G(0,0, 0.84089642));
	}
	*/
	
}
