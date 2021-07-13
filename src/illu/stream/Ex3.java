package illu.stream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Personne {
    private String nom, prenom, genre, ville;
    private int annee_naiss;
    private double salaire;

    public Personne(String nom, String prenom, String genre, String ville, int annee_naiss, double salaire) {
        this.nom = nom;
        this.prenom = prenom;
        this.genre = genre;
        this.ville = ville;
        this.annee_naiss = annee_naiss;
        this.salaire = salaire;
    }

    @Override
    public String toString() {
        return "Personne{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", genre='" + genre + '\'' +
                ", ville='" + ville + '\'' +
                ", annee_naiss=" + annee_naiss +
                ", salaire=" + salaire +
                '}';
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getGenre() {
        return genre;
    }

    public String getVille() {
        return ville;
    }

    public int getAnnee_naiss() {
        return annee_naiss;
    }

    public double getSalaire() {
        return salaire;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public void setAnnee_naiss(int annee_naiss) {
        this.annee_naiss = annee_naiss;
    }

    public void setSalaire(double salaire) {
        this.salaire = salaire;
    }
}

public class Ex3 {

    public static void main(String[] args) {

        String filepath = "src/illu/stream/personnes.txt";
        Personne[] peoples = getlist(filepath).toArray(Personne[]::new);

        System.out.println("Nés apres 1991 :");
        Stream.of(peoples).filter(pers -> pers.getAnnee_naiss() > 1991).forEach(System.out::println);
        System.out.println();
        System.out.println("Nés en 1995 :");
        Stream.of(peoples).filter(pers -> pers.getAnnee_naiss() == 1995).map(Personne::getNom).forEach(System.out::println);
        System.out.println();
        System.out.println("Nés avant 1990 (Ordered + count) :");
        AtomicInteger count = new AtomicInteger(0);
        Stream.of(peoples).filter(pers -> pers.getAnnee_naiss() < 1990).map(Personne::getNom).sorted().forEach(name -> {
            System.out.println(name);
            count.getAndIncrement();
        });
        System.out.println("Count : " + count);
        System.out.println();
        System.out.println("All Ordered (2 level) :");
        Stream.of(peoples).sorted(Comparator.comparing(Personne::getNom).thenComparing(Personne::getPrenom)).forEach(System.out::println);
        System.out.println();
        System.out.println("Gender 'F' Name in 'T'");
        Stream.of(peoples).filter(personne -> personne.getGenre().equals("F")).filter(personne -> personne.getNom().startsWith("T")).forEach(System.out::println);
        System.out.println();
        System.out.println("Gender lowerCase + Gender 'h' :");
        Stream.of(peoples).forEach(personne -> {
            personne.setGenre(personne.getGenre().toLowerCase());
            if (personne.getGenre().equals("h")) System.out.println(personne);
        });
        System.out.println();
        System.out.println("Younger Year");
        Stream.of(peoples).map(Personne::getAnnee_naiss).mapToInt(Integer::intValue).max().ifPresent(System.out::println);
        System.out.println();
        System.out.println("Salaire Moyen Lyon");
        Stream.of(peoples).filter(personne -> personne.getVille().equals("Lyon")).mapToDouble(Personne::getSalaire).average().ifPresent(System.out::println);
    }

    private static List<Personne> getlist(String path){
        List<Personne> lines = new ArrayList<>();
        try (Stream<String> pers = Files.lines(Path.of(path))){
            lines = pers.skip(1)
                    .map(p -> p.split(", "))
                    .filter(tab -> tab[4].equalsIgnoreCase("f") || tab[4].equalsIgnoreCase("h"))
                    .map(t -> new Personne(t[1],t[0],t[4],t[5],Integer.parseInt(t[2]),Double.parseDouble(t[3])))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }
}
