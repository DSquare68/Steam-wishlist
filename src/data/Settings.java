package data;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.SecureDirectoryStream;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import javax.crypto.Cipher;
import javax.crypto.EncryptedPrivateKeyInfo;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.rmi.CORBA.Util;

import javafx.scene.Node;

public class Settings {
	
	public static boolean firstOpen=true; //TODO when released remember to set to true
	private final static String NODE_NAME="Settings";
	static boolean showWelcome=true;
	public static String language="pl";
	public static double scaleWindows=1;
	public static String ID="id";
	public static long profile= 123;
	private static final String KEY_SHOW_WELCOME="show_welcome", KEY_LANGUAGE="language",KEY_ID="id", KEY_PROFILE="profile", KEY_SCALE_WINDOW="scaleWindow",KEY_FIRST_OPEN="firstOpen";
	private static String coder="Blowfish";
	public static Preferences preferences;
	public static void switchLanguage() {
		if(language.equals("pl")) language="en"; else language="pl";
		write(KEY_LANGUAGE,String.valueOf(language),false);
	}
	public static void setScaleWindwo(double value) {
		scaleWindows=value>0 ? (value+8)/8 : -1/(value-1);
		Window.refreshValues();
		write( KEY_SCALE_WINDOW,String.valueOf(value),false);
	}
	public static void switchShowWelcome() {
		showWelcome=!showWelcome;
		write(KEY_SHOW_WELCOME,String.valueOf(showWelcome),false);
	}
	public static void setID(String id) {
		ID=id;
		write(KEY_ID,String.valueOf(id),true);
	}
	public static void setProfile(long profilel) {
		profile=profilel;
		write(KEY_PROFILE,String.valueOf(profilel),true);
	}
	public static void switchFirstOpen() {
		firstOpen=!firstOpen;
		write(KEY_FIRST_OPEN,String.valueOf(firstOpen),false);
	}
	private static void write(String key, String value, boolean encryption) {
		if(value==null||value.equals(null))return;
		if(encryption) {
			value=encrypted(key,value);
		}
		preferences.put(key, value);
	}
	private static String encrypted(String key, String value) {
		byte[] encrypted=null;
		 char[] encryptedChars = null;
		try {
			SecretKeySpec skeyspec=new SecretKeySpec(key.getBytes(),coder);
			Cipher cipher=Cipher.getInstance(coder);
			cipher.init(Cipher.ENCRYPT_MODE, skeyspec);
			encrypted=cipher.doFinal(value.getBytes());
			encryptedChars = new char[encrypted.length];
			for(int i=0;i<encrypted.length;i++) {
				encryptedChars[i]=(char)(encrypted[i]+127);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return new String(encryptedChars);
		
	}
	/*public static void main(String[] args) {
		writeAll();
		show();
	}*/
	static void writeAll() {
		write(KEY_ID,String.valueOf(ID),true);
		write(KEY_LANGUAGE,String.valueOf(language),false);
		write(KEY_PROFILE,String.valueOf(profile),true);
		write(KEY_SHOW_WELCOME,String.valueOf(showWelcome),false);
		write(KEY_FIRST_OPEN,String.valueOf(firstOpen),false);
		write(KEY_SCALE_WINDOW,String.valueOf(scaleWindows),false);
		
	}
	private static String read(String key,boolean decoded) {
		String chars=preferences.get(key,"none");
		if(chars.equals("none")) return null; else if(chars==null||chars.equals(null)||chars.equals("-1")) return "none";
		if(decoded) {
			chars=decoded(chars,key);
		}
		return chars;
	}
	private static String decoded(String chars,String key) {
		try {
			SecretKeySpec skeyspec=new SecretKeySpec(key.getBytes(),coder);
			Cipher cipher=Cipher.getInstance(coder);
			cipher.init(Cipher.DECRYPT_MODE, skeyspec);
			byte[] bytes = new byte[chars.length()];
			for(int i=0;i<chars.length();i++) {
				bytes[i]=(byte)(chars.charAt(i)-127);
			}
			byte[] decrypted=cipher.doFinal(bytes);
			return new String(decrypted);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return chars;
	}
	public static void readAll() {
		try {
			ID=read(KEY_ID,true);
			language=read(KEY_LANGUAGE,false);
			profile=Long.valueOf(read(KEY_PROFILE,true));
			showWelcome=Boolean.valueOf(read(KEY_SHOW_WELCOME,false));	
			firstOpen=Boolean.valueOf(read(KEY_FIRST_OPEN,false));
			scaleWindows=Double.valueOf(read(KEY_SCALE_WINDOW,false));
		} catch(Exception e) {
			writeAll();
			readAll();
			System.out.println("No Preferences propably");
			//TODO maybe some kind of message??
			e.printStackTrace();
		}
	}
	public static void show() {
		try {
			preferences.exportSubtree( System.out );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BackingStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static boolean isFirstRun() {
		return Boolean.valueOf(read(KEY_FIRST_OPEN,false))==null ? false : Boolean.valueOf(read(KEY_FIRST_OPEN,false));
	}
	public static void initPreferences() {
		preferences = Preferences.userRoot().node(NODE_NAME);
	}
}
