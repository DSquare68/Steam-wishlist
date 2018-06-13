package data;

import java.util.Date;

public class DDate extends Date{

	public DDate(long l) {
		super(l);
	}

	@Override
	public String toString() {
		return String.format("%02d", getDate())+":"+String.format("%02d", getMonth()+1)+":"+(getYear()+1900);
	}
	

}
