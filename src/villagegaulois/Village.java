package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;


public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	private Marche marche;
	private int taille_marche;

	public Village(String nom, int nbVillageoisMaximum,int taille_marche) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
		marche= new Marche(taille_marche);
	}

	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() {
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef "
					+ chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom()
					+ " vivent les légendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
	
	public String installerVendeur(Gaulois vendeur, String produit,int nbProduit) {
		StringBuilder chaine = new StringBuilder(vendeur.getNom()+
				" cherche un endroit pour vendre "+nbProduit+" "+produit+"\n");
		
		chaine.append("Le vendeur"+vendeur.getNom()+" vend des "+produit+
				" à l'étal n° "+ marche.trouverVendeur(vendeur));
		return chaine.toString();	
		}
	
	public String rechercherVendeursProduit(String produit) {
		StringBuilder vendeurexist = new StringBuilder("Les vendeurs qui proposent des "
				+produit+" sont ");
		StringBuilder novendeur = new StringBuilder("Il n'y a pas de vendeur qui propose des "
				+produit+" au marché.");
		
		
		int vendeurtrouv=0;
		int i=0;
		while(i<taille_marche) {
		//for (int i=0;i<taille_marche;i++) {
			i++;
			if (marche.etals[i].contientProduit(produit)==true) {
				vendeurtrouv++;
				vendeurexist.append("- "+marche.etals[i].getVendeur().getNom()
						+"\n");
			}
		}
		if (vendeurtrouv==1) {
			StringBuilder univendeur = new StringBuilder("Seul le vendeur "+marche.etals[i].getVendeur().getNom()+
					"propose des "+produit+" au marché");
			return vendeurexist.toString();
		}
		if (vendeurtrouv>1) {
			return vendeurexist.toString();
		}
		
		return novendeur.toString();
	}
	private static class Marche{
		private Etal[] etals;
		private int nbEtal;

		public Marche(int nbEtal) {
			super();
			this.etals = etals;
			etals= new Etal[nbEtal];
			
			for ( int i=0;i<nbEtal;++i) {
				etals[i]=new Etal();
			}
		}
		
		public void utiliserEtal(int indiceEtal, Gaulois vendeur,String produit, int nbProduit) {
			etals[indiceEtal].occuperEtal(vendeur,produit,nbProduit);
			}
			
		
		
		public int trouverEtalLibre() {
			for (int i=0; i<nbEtal;++i) {
				if (!(etals[i].isEtalOccupe())) {
					return i;
				}
			}
			return -1;
		}
		
		public Etal[] trouveretals(String produit) {
			Etal[] tabdevendeur;
			tabdevendeur= new Etal[nbEtal];
			
			for (int i=0; i<nbEtal;++i) {
				if (etals[i].contientProduit(produit)) {
					tabdevendeur[i]=etals[i];
				}
			}
			return tabdevendeur;
		}
		
		public Etal trouverVendeur(Gaulois gaulois) {
			for (int i=0; i<nbEtal;++i) {
				if (etals[i].getVendeur()==gaulois) {
					return etals[i];
				}
			}	
			return null;
		}
		
		public String afficherMarche() {
			StringBuilder chaine = new StringBuilder();
			int nbEtalVide=nbEtal;
			for (int i=0; i<nbEtal;++i) {
				
				nbEtalVide--;
				etals[i].afficherEtal();
				}
			chaine.append("Il reste "+nbEtalVide+"non utilisés dans le marché.");
			return chaine.toString();
		}
		
			
	}
	
}