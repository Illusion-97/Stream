package illu.stream;

import java.util.stream.Stream;

class Personne {
    private String nom, prenom, genre;
    private int annee_naiss;

    public Personne(String nom, String prenom, String genre, int annee) {
        this.nom = nom;
        this.prenom = prenom;
        this.genre = genre;
        annee_naiss = annee;
    }

    @Override
    public String toString() {
        return "Personne{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", genre='" + genre + '\'' +
                ", annee_naiss=" + annee_naiss +
                '}';
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getAnnee_naiss() {
        return annee_naiss;
    }

    public void setAnnee_naiss(int annee_naiss) {
        this.annee_naiss = annee_naiss;
    }
}

public class Ex3 {

    public static void main(String[] args) {
        Personne[] peoples = {};
        Stream.of(peoples).filter(pers -> pers.getAnnee_naiss() > 1991).forEach(System.out::println);
        Stream.of(peoples).filter(pers -> pers.getAnnee_naiss() == 1995).map(Personne::getNom).forEach(System.out::println);
        Stream.of(peoples).filter(pers -> pers.getAnnee_naiss() < 1990).map(Personne::getNom).sorted().forEach(System.out::println);
    }
}
