package junitTests;

import app.Application;
import app.HolidaysOffer;
import app.Host;
import app.LivingOffer;
import app.Profile;
import app.User;
import app.UserStates;
import exception.NotAdmin;
import java.time.*;

public class Prueba {

	public static void main(String[] args) throws Exception{
		LocalDate to = LocalDate.of(2018, 4, 20);
		LocalDate from = LocalDate.of(2018, 4, 5);
		from.minusDays(1);
		 to.plusDays(1);
	
		if(LocalDate.of(2018, 4, 5).isAfter(from) && LocalDate.of(2018, 4, 20).isBefore(to)) {
			System.out.println("DArtfuf");
		}
	}

}
