# Système de Gestion des Coupons d'Impression - Bibliothèque Universitaire

## Contexte du Problème

À la bibliothèque universitaire, notamment celle de l'Université du Québec à Chicoutimi (UQAC), les utilisateurs ont la possibilité d'acheter des coupons d'impression auprès de la bibliothécaire pour effectuer des opérations telles que :

1. Impression : utilisation du papier à 0.5 $ par page.

2. Photocopie : utilisation du papier à 0.5 $ par page.

3. Scan : opération gratuite (aucun coût lié).

Chaque coupon dispose d’un montant initial de 5 $. Le montant du coupon diminue au fur et à mesure des opérations payantes. Une fois le solde à 0, l'utilisateur doit acheter un nouveau coupon pour continuer.

Dans le cadre de ce projet, nous nous concentrons exclusivement sur la gestion des fonctionnalités des coupons (impression, copie, scan) tout en suivant le modèle Puzzle Pattern. La gestion des achats de coupons, des utilisateurs et des documents est volontairement omise pour simplifier l'implémentation.

## Approche : Le Puzzle Pattern

Le Puzzle Pattern est une approche modulaire qui permet de décomposer les fonctionnalités d'une application en petites unités réutilisables. Dans cette analogie :

- Chaque interface représente un morceau de puzzle (une fonctionnalité indépendante).

- La classe concrète assemble ces morceaux pour former un ensemble cohérent, tout comme un plateau de puzzle.

### Principes du Puzzle Pattern appliqués :

1. Interfaces distinctes : Chaque fonctionnalité (impression, copie, scan) est représentée par une interface dédiée.

2. Comportements indépendants : Les interfaces contiennent la logique des opérations avec des méthodes abstraites pour accéder à l'état requis.

3. Classe concrète : Une classe assemble les interfaces et fournit l'état nécessaire (le montant du coupon).

Cette architecture favorise la réutilisabilité, la maintenabilité et un couplage faible entre les composants.

## Structure du Code

### Interfaces :

1. `IScannable`

Interface pour la fonctionnalité de numérisation (scan).

```
public interface IScannable {
    void scan();
}
```

2. `IPrintable`

Interface pour la fonctionnalité d'impression. Elle dépend de l'état (montant du coupon).

```
public interface IPrintable {
    void print(Coupon coupon, int pages);
}
```

3. `ICopyable`

Interface pour la fonctionnalité de copie. Elle dépend également du montant du coupon.

```
public interface ICopyable {
    void copy(Coupon coupon, int pages);
}
```

## Services :

1. `DatabaseService`

Service responsable d'ajouter des coupons, les valider et mettre à jour leurs montants après une opération.

```
public class DatabaseService {
	private List<Coupon> coupons;
	
	public DatabaseService() {
        this.coupons = new ArrayList<>();
        // Quelques coupons pour tester
        coupons.add(new Coupon("123", "password123"));
        coupons.add(new Coupon("456", "password456"));
    }
	
	public Coupon validateCoupon(String id, String password) {
        for (Coupon coupon : coupons) {
            if (coupon.getId().equals(id) && coupon.getPassword().equals(password)) {
                return coupon;
            }
        }
        return null;
    }
	
	public void deductAmount(Coupon coupon, double amount) {
		coupon.setAmount(coupon.getAmount() - amount);
	}

}
```

2. `ServiceCoupon`

Ce Service implémente l'interface `IScannable`, `IPrintable` et `ICopyable`.

```
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
```

## Classe Principale (`Main`)

La classe Main contient le menu utilisateur qui permet de :

1. Saisir les informations du coupon (ID et mot de passe).

2. Choisir une opération : Scan, Impression, ou Copie.

3. Répéter les opérations jusqu'à ce que le solde du coupon soit épuisé.

## Coupons Disponibles dans le Code

Pour tester le projet, plusieurs coupons sont ajoutés directement dans le code. Ils sont stockés dans une liste dans la classe Main.

Liste des Coupons :

| ID                  | Mot de Passe           |
|---------------------|------------------------|
| `123`               | password123    |
| `456`               | password456        |

Vous pouvez utiliser ces coupons pour exécuter le projet. Si un coupon atteint 0, il ne pourra plus être utilisé.

## Exécution du Projet

1. Compiler les fichiers Java.

2. Exécuter la classe Main.

3. Suivre les instructions à l'écran pour :

    - Saisir les informations du coupon.

    - Choisir une opération (Scan, Impression, ou Copie).

    - Continuer jusqu'à l'épuisement du montant du coupon.

## Conclusion

Ce projet met en œuvre le Puzzle Pattern en séparant chaque fonctionnalité dans des interfaces indépendantes et en les assemblant dans une classe concrète. Cette approche modulaire facilite la réutilisabilité, la maintenance et l’évolution du système.
