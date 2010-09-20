
/**
 * Write a description of class TestDriver here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
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
	
}
