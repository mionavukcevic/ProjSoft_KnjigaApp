/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domain.Autor;
import domain.Knjiga;
import domain.Zanr;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Miona
 */
public class Controller {
    private List<Knjiga> listaKnjiga;
    private List<Autor> listaAutora;
    

    //pravi se staticko polje instanca koja je privatna
    private static Controller instance;
    
    public static Controller getInstance(){
        if(instance == null){
            instance=new Controller();
        }
        return instance;
    }
    
    
    //privatni konstruktor!!!
    private Controller() {
        Autor a1 = new Autor("Ivo","Andric", 1892,"Biografija autora Ive Andrica bla bla");
        Autor a2 = new Autor("Danilo","Kis", 1935,"Biografija autora Danila Kisa bla bla");
        Autor a3 = new Autor("Mesa","Selimovic", 1910,"Biografija autora Mese Selimovica bla bla");
        
        
        Knjiga k1 = new Knjiga("Na Drini cuprija", a1, "1234567890", 1945, Zanr.ROMAN);
        Knjiga k2 = new Knjiga("Basta, pepeo", a2, "0987654321", 1982,Zanr.ROMAN);
        Knjiga k3 = new Knjiga("Tvrdjava", a3, "1122334455", 1970,Zanr.ROMAN);
        
        listaKnjiga = new ArrayList<>();
        listaAutora = new ArrayList<>();
        
        listaKnjiga.add(k1);
        listaKnjiga.add(k2);
        listaKnjiga.add(k3);
        
        listaAutora.add(a1);
        listaAutora.add(a2);
        listaAutora.add(a3);
   
    }

    public List<Knjiga> getListaKnjiga() {
        return listaKnjiga;
    }

    public void setListaKnjiga(List<Knjiga> listaKnjiga) {
        this.listaKnjiga = listaKnjiga;
    }

    public List<Autor> getListaAutora() {
        return listaAutora;
    }

    public void setListaAutora(List<Autor> listaAutora) {
        this.listaAutora = listaAutora;
    }

    public void obrisiKnjigu(int selektovaniRed) {
        listaKnjiga.remove(selektovaniRed);
    }

    public void dodajKnjiga(Knjiga novaKnjiga) {
        listaKnjiga.add(novaKnjiga);
        //System.out.println("KNJIGA JE DODATA");
        //System.out.println(listaKnjiga);
    }
    
    
    
    
}
