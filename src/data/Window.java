package data;

import java.awt.Dimension;
import java.awt.Toolkit;

public class Window {
	static Toolkit toolkit = Toolkit.getDefaultToolkit();
	static Dimension dimension = toolkit.getScreenSize();
	
	public static int width=(int) (dimension.width);
	public static int height=(int) (dimension.height);
	
	public static Frame welcome, central, firstRun, filtr;
	public Window() {
		welcome= new Frame(width/5,height/4.5);
		central = new Frame(4*width/5,2*height/3);
		firstRun = new Frame(width/5,height/4.5);
		filtr = new Frame(2*central.width/3,central.height/3);
	}
	public static void refreshValues() {
		width=(int) (dimension.width*Settings.scaleWindows);
		height=(int) (dimension.height*Settings.scaleWindows);
		welcome.refreshValues(width/5,height/4.5);
		central.refreshValues(4*width/5,2*height/3);
		firstRun.refreshValues(width/5,height/4.5);
	}
	public class Frame {
		public double width, height;
		public Frame() {
			
		}
		
		public Frame(double width, double height) {
			this.width = width;
			this.height = height;
		}

		public double getWidth() {
			return width;
		}
		public void setWidth(double width) {
			this.width = width;
		}
		public double getHeight() {
			return height;
		}
		public void setHeight(double height) {
			this.height = height;
		}
		public void refreshValues(double width, double height) {
			setWidth(width);
			setHeight(height);
		}
		
	}
	
}
