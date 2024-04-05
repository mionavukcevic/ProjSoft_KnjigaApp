/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baza;

import domain.Autor;
import domain.Knjiga;
import domain.Zanr;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import  java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Miona
 */
//forma kaze Controlleru sta joj je potrebnio, Controller komunicira sa DBBRokerom,
//DBBroker uz pomocu Konekcije skoci u bazu i izvrsi promene ili izvuce podatke, onda ih vrati Controllerom, a Controller daje formi
    
public class DBBroker {

    public List<Knjiga> ucitajListuKnjigaIzBaze() {
         List<Knjiga> lista = new ArrayList<>();
        try {
            String upit = "SELECT * FROM knjiga k JOIN autor a ON k.autorId = a.id";
            Statement st = Konekcija.getInstance().getConnection().createStatement(); //ova linija koda sluzi da naparvimo st koji ce da izvrsi sql upit
            ResultSet rs = st.executeQuery(upit);
            
            //za svaki red iz rs (rs je tabela) pravimo add to lista
            while(rs.next()){
                int id = rs.getInt("k.id");
                String naslov = rs.getString("k.naslov");
                int godinaIzdanja = rs.getInt("k.godinaIzdanja");
                String ISBN = rs.getString("k.isbn");
                String zanr = rs.getString("k.zanr");
                //pretvaranje String u ENUM
                Zanr z = Zanr.valueOf(zanr);
                
               
                String ime = rs.getString("a.ime");
                String prezime = rs.getString("a.prezime");
                String biografija = rs.getString("a.biografija");
                int godinaRodjenja = rs.getInt("a.godinaRodjenja");
                int idAutora = rs.getInt("a.id");
                
                
                Autor autor = new Autor(idAutora, ime, prezime, godinaRodjenja, biografija);
                      
                
                Knjiga k = new Knjiga(id, naslov, autor, ISBN, godinaIzdanja, z);
                lista.add(k);
            }
            
    
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public List<Autor> ucitajListuAutoraIzBaze() {
        List<Autor> lista = new ArrayList<>();
        
        try {
            String upit = "SELECT * FROM autor";
            Statement st = Konekcija.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(upit);
            
            while (rs.next()) {                
                int id = rs.getInt("id");
                String ime = rs.getString("ime");
                String prezime = rs.getString("prezime");
                int godinaRodjenja = rs.getInt("godinaRodjenja");
                String biografija = rs.getString("biografija");
                
                Autor a = new Autor(id, ime, prezime, godinaRodjenja, biografija);
                

                lista.add(a);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return lista;
    }

    public void obrisiKnjiguIzBaze(int id) {
        try {
            //ANTIPREPORUKA ZA SLEDECI NACIN---> bolje je koristiti preparedstatemnent
            //String upit = "SELECT * FROM knjiga WHERE id =" +id;
            
            
            String upit ="DELETE FROM knjiga WHERE id=?";            
            PreparedStatement ps = Konekcija.getInstance().getConnection().prepareStatement(upit);
            ps.setInt(1, id);
            //System.out.println(upit);
            ps.executeUpdate();
            Konekcija.getInstance().getConnection().commit();
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void dodajKnjiguUBazu(Knjiga novaKnjiga) {
        
        try {
            String upit = "INSERT INTO knjiga (id, naslov, autorId, godinaIzdanja, ISBN, zanr) VALUES (?,?,?,?,?,?)";
            PreparedStatement ps = Konekcija.getInstance().getConnection().prepareStatement(upit);
            ps.setInt(1, novaKnjiga.getId());
            ps.setString(2, novaKnjiga.getNaslov());
            ps.setInt(3, novaKnjiga.getAutor().getId());
            ps.setInt(4, novaKnjiga.getGodinaIzdanja());
            ps.setString(5, novaKnjiga.getISBN());
            ps.setString(6, novaKnjiga.getZanr().toString());
            ps.executeUpdate();
            Konekcija.getInstance().getConnection().commit();
            
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }

    public void azurirajKnjiguIzBaze(Knjiga knjigaZaIzmenu) {
         try {
            String upit = "UPDATE knjiga SET naslov=?, autorId=?, godinaIzdanja=?, zanr=? WHERE id=?";
            PreparedStatement ps = Konekcija.getInstance().getConnection().prepareStatement(upit);
            ps.setString(1, knjigaZaIzmenu.getNaslov());
            ps.setInt(2, knjigaZaIzmenu.getAutor().getId());
            ps.setInt(3, knjigaZaIzmenu.getGodinaIzdanja());
            ps.setString(4, knjigaZaIzmenu.getZanr().toString());
            ps.setInt(5, knjigaZaIzmenu.getId());
            
            ps.executeUpdate();
            Konekcija.getInstance().getConnection().commit();
            
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
}
