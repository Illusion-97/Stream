package illu.stream;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
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
        Personne[] peoples = {new Personne("Ad","ya","m",1985), new Personne("Zd","ya","m",1985), new Personne("Dd","ya","m",1985)};
        Stream.of(peoples).filter(pers -> pers.getAnnee_naiss() > 1991).forEach(System.out::println);
        Stream.of(peoples).filter(pers -> pers.getAnnee_naiss() == 1995).map(Personne::getNom).forEach(System.out::println);
        AtomicInteger count = new AtomicInteger(0);
        Stream.of(peoples).filter(pers -> pers.getAnnee_naiss() < 1990).map(Personne::getNom).sorted().forEach(name -> {
            System.out.println(name);
            count.getAndIncrement();
        });
        System.out.println("Count : " + count);
        Stream.of(peoples).sorted(Comparator.comparing(Personne::getNom).thenComparing(Personne::getPrenom)).forEach(System.out::println);
        Stream.of(peoples).filter(personne -> personne.getGenre().equals("F")).filter(personne -> personne.getNom().startsWith("j")).forEach(System.out::println);
        Stream.of(peoples).forEach(personne -> {
            personne.setGenre(personne.getGenre().toLowerCase());
            if (personne.getGenre().equals("h")) System.out.println(personne);
        });
    }
}
