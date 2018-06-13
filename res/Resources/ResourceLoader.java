package Resources;


import java.net.URL;
import java.net.URLDecoder;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ResourceLoader {
	public static final String LANGUAGE_PL = "pl.jpg", LANGUAGE_EN="en.jpg";

	static public String HELP="help.jpg";
	
	static ResourceLoader rl = new  ResourceLoader();
	
	public static String decoded(URL a){
		String d = a.toString();
		String decodedValue2= null;
		try{
			decodedValue2 = URLDecoder.decode(d, "UTF-8");
		}catch(Exception E){
			
	       }
		//decodedValue2= decodedValue2.substring(6,decodedValue2.length());
		 return decodedValue2;
	}
	
	public static Image getImage(String fileName){
		Image img=null;
		URL url = ResourceLoader.class.getResource("/images/"+fileName);
		img= new Image(decoded(url));
		return img;
	}
	public static ImageView getImageView(String fileName){
		ImageView img=null;
		URL url = ResourceLoader.class.getResource("/images/"+fileName);
		img= new ImageView(decoded(url));
		return img;
	}
	public static String getPathToData() {
		return System.getProperty("user.dir")+"\\Data\\";
	}
}
