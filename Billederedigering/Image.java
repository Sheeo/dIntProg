import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.image.WritableRaster;
import java.io.File;
import javax.swing.*;
import java.awt.*;
import java.awt.image.*;

import java.util.List;
import java.util.ArrayList;

/**
 * A model of an image with the proportions 
 * <code>width</code> X <code>height</code>
 * 
 * @author Thomas G. Kristensen
 * @author Mark Greve
 * @version 1.1
 */
public class Image
{
    private Pixel[][] pixels;
    private int width, height;
    private java.awt.Image img;
    
    /**
     * Creates a new <code>Image</code> object, based on
     * an existing file.
     * The title of the image is taken from the filename.
     * 
     * @param filename The file to be loaded. Can be a JPG, PNG, BMP or GIF
     */
    public Image(String filename)
    {
        try{
            BufferedImage input = ImageIO.read(new File(filename));
            width = input.getWidth();
            height = input.getHeight();
            pixels = new Pixel[width][height];
            WritableRaster raster = (WritableRaster) input.getData();
            int[] rgb = new int[3];
            for(int i = 0; i < width; i++)
                for(int j = 0; j < height; j++)
                {
                    rgb = raster.getPixel(i, j, rgb);
                    int value = (rgb[0] + rgb[1] + rgb[2]) / 3;
                    pixels[i][j] = new Pixel(value);
                }
        } catch(Exception e)
        {
            width = 100;
            height = 100;
            pixels = new Pixel[width][height];
            for(int i = 0; i < width; i++)
                for(int j = 0; j < height; j++)
                    pixels[i][j] = new Pixel(0);
        }
        
        vindue = new JFrame(filename);
        tavle = new Tavle();
        
        vindue.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vindue.setContentPane(tavle);
        vindue.pack();
        vindue.setVisible(true);
    }
    
    /**
     * Constructs a new empty image.
     * Constructs all of the pixels in the image as well.
     * 
     * @param width The width of the image
     * @param height The height of the image
     * @param title The title of the image
     */
    public Image(int width, int height, String title)
    {
        this.width = width;
        this.height = height;
        pixels = new Pixel[width][height];
        for(int i = 0; i < width; i++)
            for(int j = 0; j < height; j++)
                pixels[i][j] = new Pixel(0);

        vindue = new JFrame(title);
        tavle = new Tavle();
        
        vindue.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vindue.setContentPane(tavle);
        vindue.pack();
        vindue.setVisible(true);
        
    }
    
    /**
     * Returns the width of the image
     * 
     * @return The width of the image
     */
    public int getWidth()
    {
        return width;
    }
    
    /**
     * Returns the height of the image
     * 
     * @return The height of the image
     */
    public int getHeight()
    {
        return height;
    }
    
    /**
     * Informs the image that one or more of its pixels
     * has changed value, and that it is time to repaint
     * the canvas.
     */
    public void pixelsUpdated()
    {
        tavle.rebuildPicture();
        tavle.repaint();
    }
    
    /**
     * Returns the pixel in position <i>(i, j)</i>.
     * If the position <i>(i, j)</i> is outside 
     * the image, an exception is thrown.
     * 
     * @param i The horizontal index
     * @param j The vertical index
     */
    public Pixel getPixel(int i, int j)
    {
        return pixels[i][j];
    }
    
    /**
     * Returns a list of all the pixels in the image
     * 
     * @return A list of all the pixels in the image.
     */
    public List<Pixel> getPixels()
    {
        List<Pixel> all = new ArrayList<Pixel>();
        for(int i = 0; i < width; i++)
            for(int j = 0; j < height; j++)
                all.add(pixels[i][j]);
        return all;
    }
    
    private void addPixelToList(int i, int j, List<Pixel> list)
    {
        if(i >= 0 && j >= 0 && i < width && j < height)
            list.add(pixels[i][j]);
    }
    
    /**
     * Returns the up to nine neighbours in the neighbourhod
     * of the pixel in position <i>(i, j)</i>.
     * 
     * @param i The horizontal index
     * @param j The vertical index
     */
    public List<Pixel> getNeighbours(int i, int j)
    {
        List<Pixel> result = new ArrayList<Pixel>();
        
        for(int x = i - 1; x <= i + 1; x ++)
            for(int y  = j - 1; y <= j + 1; y++)
                addPixelToList(x, y, result);
        
        return result;
    }
    
    private class Tavle extends JPanel
    {
        private Tavle()
        {
            setPreferredSize(new Dimension(width, height));
        }
        
        private void rebuildPicture() {
            int pix[] = new int[width*height];
            int tmp = 0;
            for(int j = 0; j < height; j++) {
                for(int i = 0; i < width; i++) {
                    int val = pixels[i][j].getValue();
                    // alpha, red, green, blue
                    pix[tmp++] = (255 << 24) | (val << 16) | (val << 8) | val;     
                }
            }
            img = createImage(new MemoryImageSource(width, height, pix, 0, width));
        }
        
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);         
            if (img == null) rebuildPicture();
            g.drawImage(img, 0, 0, this);
        }
    }

    private JFrame vindue;
    private Tavle tavle;
}
