package illu.stream;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Employe {
    private String nom;
    private double salaire;
    public Employe(String nom, double salaire) {
        this.nom = nom;
        this.salaire = salaire;
    }
    public String getNom() {
        return nom;
    }
    public double getSalaire() {
        return salaire;
    }
    @Override
    public String toString() {
        return "Employe{" + "nom=" + nom + ", salaire=" + salaire + '}';
    }
}

public class Ex4 {

    public static void main(String[] args) {

        List<Employe> employes = new ArrayList<>();
        employes.add(new Employe("Fred",23000));
        employes.add(new Employe("Pierre",31000));
        employes.add(new Employe("Marc",7000));
        employes.add(new Employe("Paul",6000));
        employes.add(new Employe("Bob",21000));
        employes.add(new Employe("Dylan",65000));

        System.out.println(employes.stream().filter(employe -> employe.getSalaire() > 15000).sorted(Comparator.comparing(Employe::getSalaire)).map(employe -> employe.getNom() + " : " + employe.getSalaire()).collect(Collectors.joining(", ")));
    }
}
