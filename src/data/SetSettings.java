package data;

public class SetSettings {

	public static void read() {
		Settings.initPreferences();
		if(Settings.isFirstRun()) {
			Settings.writeAll();
		} 
		Settings.readAll();
		//Settings.show();
	}
}
