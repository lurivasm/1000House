package App;

import modifiableDates.*;
import java.time.LocalDate;




public class Prueba {
	private LocalDate date;
	
	public Prueba() {
		date = LocalDate.of(2016, 9, 3);
	}
	
	
	public LocalDate getdate() {
		return date;
	}
	
	public int diferencia() {
		if(date.getYear() > ModifiableDate.getModifiableDate().getYear()) {
			return 1;
		}
		return 0;
	}

	public static void main(String[] args) throws Exception {
		Prueba p = new Prueba();
		
		System.out.println(p.getdate());
		
		
		
		System.out.println(ModifiableDate.getModifiableDate());
		System.out.println(p.diferencia());
	}	
}
