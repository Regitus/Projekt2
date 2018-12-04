package projekt.Basis;

import java.util.Scanner;

import projekt.User.*;

public class Menu {
	
	private AktuellerBenutzer benutzer; // = new AktuellerBenutzer(); 
	Scanner in;
	
	
	public Menu()
	{
		anmelden();
	}
	
	private void anmelden()
	{
		benutzer = new AktuellerBenutzer();
		in = new Scanner(System.in);
		System.out.println("User");
		benutzer.setBenutzerName(in.next());
		System.out.println("Pass");
		benutzer.setPasswort(in.next());
	}
	
	public void programm()
	{
		boolean run = true;
		do
		{
			System.out.println( 	"1. Neue Anmeldedaten" +
									"\n2. Die letzten 100 Nachrichten" + 
									"\n99 Beenden");
			switch (in.nextInt()) {
			case 1:
				anmelden();
				break;
				
			case 2:
				
				break;
				
			case 99:
				run = false;
				break;
				

			default:
				break;
			}
		
		}while(run);
	}
	

}
