package ca.uqac.services;

import ca.uqac.coupon.Coupon;
import ca.uqac.fonctionnalites.ICopyable;
import ca.uqac.fonctionnalites.IPrintable;
import ca.uqac.fonctionnalites.IScannable;

public class ServiceCoupon implements IPrintable, ICopyable, IScannable {
	private DatabaseService databaseService;
	
	public ServiceCoupon() {
        this.databaseService = new DatabaseService();
    }

	// fonctionnalite de numerisation
	@Override
	public void scan() {
		System.out.println("Votre document a été scanné");
	}
	

	// fonctionnalite de photocopie
	@Override
	public void copy(Coupon coupon, int pages) {
		double costPerPage = 0.5;
        int pagesCopied = 0;

        while (pages > 0 && coupon.getAmount() >= costPerPage) {
            databaseService.deductAmount(coupon, costPerPage);
            pages--;
            pagesCopied++;
        }

        System.out.println(pages + " pages photocopiées avec succès. Solde du compte: " + coupon.getAmount() + "$");
        if (pages > 0) {
            System.out.println(pages + " pages restantes impossible à photocopier du fait  de votre solde insuffisant");
            System.out.println("Veuillez s'il vous plait, vous procurer un autre coupon auprès de la bibliothécaire.");
        }
	}
	

	// fonctionnalite d'impression
	@Override
	public void print(Coupon coupon, int pages) {
		double costPerPage = 0.5;
        int pagesPrinted = 0;

        while (pages > 0 && coupon.getAmount() >= costPerPage) {
            databaseService.deductAmount(coupon, costPerPage);
            pages--;
            pagesPrinted++;
        }

        System.out.println(pagesPrinted + " pages imprimées avec succès. Solde du compte: " + coupon.getAmount() + "$");
        if (pages > 0) {
            System.out.println(pages + " pages restantes impossible à imprimer du fait  de votre solde insuffisant");
            System.out.println("Veuillez s'il vous plait, vous procurer un autre coupon auprès de la bibliothécaire.");
        }
    
	}
	

	public DatabaseService getDatabaseService() {
		// TODO Auto-generated method stub
		return databaseService;
	}

}
