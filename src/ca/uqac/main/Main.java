package ca.uqac.main;

import java.util.Scanner;

import ca.uqac.coupon.Coupon;
import ca.uqac.services.ServiceCoupon;


public class Main {
	private static ServiceCoupon db_service = new ServiceCoupon();
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

        System.out.println("=== Bienvenue à la bibliothèque ===");
        System.out.print("Veuillez entrer le compte associé à votre coupon: ");
        String id = scanner.nextLine();
        System.out.print("Veuillez entrer le mot de passe du compte: ");
        String password = scanner.nextLine();

        Coupon coupon = db_service.getDatabaseService().validateCoupon(id, password);

        if (coupon != null) {
            System.out.println("Connexion OK! Votre solde est de :" + coupon.getAmount() + "$");
            showMenu(coupon);
        } else {
            System.out.println("Compte ou mot de passe invalides. SVP, veuillez réessayer encore.");
        }
        
        scanner.close();
	}

	
	
	public static void showMenu(Coupon coupon) {
        Scanner scanner = new Scanner(System.in);
        int choix;

        do {
            System.out.println("\n=== Menu ===");
            System.out.println("1. Imprimer");
            System.out.println("2. Photocopier");
            System.out.println("3. Numériser/Scanner");
            System.out.println("4. Quitter");
            System.out.print("Enter your choice: ");
            choix = scanner.nextInt();

            switch (choix) {
                case 1:
                    System.out.print("Veuillez entrer le nombre de pages à imprimer: ");
                    int printPages = scanner.nextInt();
                    db_service.print(coupon, printPages);
                    break;
                case 2:
                    System.out.print("Veuillez entrer le nombre de pages à photocopier: ");
                    int copyPages = scanner.nextInt();
                    db_service.copy(coupon, copyPages);
                    break;
                case 3:
                	db_service.scan();
                    break;
                case 4:
                    System.out.println("Sortie du système. Au revoir et à bientot!");
                    break;
                default:
                    System.out.println("Choix invalide! Veuillez reessayer!");
            }
        } while (choix != 4);
        
        scanner.close();
    }
}
