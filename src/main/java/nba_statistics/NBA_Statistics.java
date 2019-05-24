/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nba_statistics;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import nba_statistics.controllers.MainController;
import nba_statistics.entities.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

/**
 * @author Przemek
 */
public class NBA_Statistics extends Application {

    private static final String MainFXML = "/MainView.fxml";

    @Override
    public void start(Stage primaryStage) throws Exception {
        //window = primaryStage;
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource(MainFXML));
        AnchorPane anchorPane = loader.load();
        Scene scene = new Scene(anchorPane);


        primaryStage.setScene(scene);
        primaryStage.setTitle("NBA Statistics");
        primaryStage.show();



    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        //create session factory
        SessionFactory sf = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Mecze.class)
                .addAnnotatedClass(Druzyny.class)
                .addAnnotatedClass(HistoriaDruzynZawodnika.class)
                .addAnnotatedClass(OsiagnieciaZawWMeczu.class)
                .addAnnotatedClass(Sezony.class)
                .addAnnotatedClass(Zawodnicy.class)
                .addAnnotatedClass(HistZmianWMeczu.class)
                .addAnnotatedClass(PowodZejscia.class)
                .addAnnotatedClass(ZawodnikPozycja.class)
                .addAnnotatedClass(Pozycje.class)
                .addAnnotatedClass(PozycjeZawWMeczu.class)
                .buildSessionFactory();

        //create session
        Session session = sf.getCurrentSession();

        try {
            //Druzyny d1 = new Druzyny("Atlantycka", "Wschodnia", "Toronto Raptors", "Toronto");
            //Druzyny d2 = new Druzyny("Centralna", "Wschodnia", "Indiana Pacers", "Indiana");
//            Druzyny d3 = new Druzyny("Pacific", "Zachodnia", "Phoenix Suns", "Phoenix");
//
//            Zawodnicy z1 = new Zawodnicy("Andrzej", "Grabowski", "1940-02-10", 160, 140);
//            Sezony s1 = new Sezony("Play Offy", "2017-06-22", "2018-07-30");
//            HistoriaDruzynZawodnika hdz1 = new HistoriaDruzynZawodnika();
//            hdz1.setDruzyna(d2);
//            hdz1.setSezon(s1);
//            hdz1.setZawodnik(z1);


            session.beginTransaction();


            ///////// TEST OsiagnieciaZawWMeczu ////////////

     //       Zawodnicy zaw = session.get(Zawodnicy.class, 4);
      //      Mecze mecz = session.get(Mecze.class, 6);
//            Mecze mecz2 = session.get(Mecze.class, 6);
//
        //    OsiagnieciaZawWMeczu osiag = new OsiagnieciaZawWMeczu(zaw, mecz, 30, 5, 3, 4, 5, 2);
//            OsiagnieciaZawWMeczu osiag2 = new OsiagnieciaZawWMeczu(zaw, mecz2, 6, 5, 4, 3, 2, 1);
   //         session.save(osiag);
//            session.save(osiag2);
//            OsiagnieciaZawWMeczu osiagResult = session.get(OsiagnieciaZawWMeczu.class, new OsiagnieciaZawWMeczuId(7, 6));
//            System.out.println(zaw.toString());
//            System.out.println(mecz.toString());
//            System.out.println(osiagResult);

            ///////// TEST ZawodnikPozycja ////////////
/*
              Pozycje pozycja = new Pozycje("Rozgrywajacy");
//            Pozycje pozycja2 = new Pozycje("Rzucajacy obronca");
              session.save(pozycja);
//            session.save(pozycja2);
              //Zawodnicy zaw = session.get(Zawodnicy.class, 5);
              Zawodnicy zaw = new Zawodnicy("Michael", "Jordan", "1968-02-10",198,98);
              session.save(zaw);
//            Pozycje poz = session.get(Pozycje.class, 2);
//            Pozycje poz2 = session.get(Pozycje.class, 3);
              ZawodnikPozycja zawodnikPozycja = new ZawodnikPozycja(zaw, pozycja);
              session.save(zawodnikPozycja);
//              ZawodnikPozycja zawodnikPozycjaRes = session.get(ZawodnikPozycja.class,new ZawodnikPozycjaID(zaw.getId(),poz.getId()));
//              session.delete(zawodnikPozycjaRes);
            System.out.println(zaw.toString());
            System.out.println(pozycja.toString());
              System.out.println(zawodnikPozycja.toString());
*/

            ///////// TEST PozycjeZawWMeczu ////////////
/*
            Mecze mecz = session.get(Mecze.class, 6);
            Zawodnicy zawodnik = session.get(Zawodnicy.class, 4);
            Pozycje pozycja = session.get(Pozycje.class, 1);
            //PozycjeZawWMeczu pozycjeZawWMeczu = new PozycjeZawWMeczu(zawodnik,mecz,pozycja);
            //session.save(pozycjeZawWMeczu);
            PozycjeZawWMeczu pozycjeZawWMeczuRes = session.get(PozycjeZawWMeczu.class,
                    new PozycjeZawWMeczuID(zawodnik.getId(), mecz.getId(),pozycja.getId()));
            session.delete(pozycjeZawWMeczuRes);
            session.delete(mecz);

            PowodZejscia pz = new PowodZejscia("zmiana taktyczna");
            session.save(pz);

            Druzyny d2 = new Druzyny("Centralna", "Wschodnia", "Indiana Pacers", "Indiana");
            Druzyny d3 = new Druzyny("Pacific", "Zachodnia", "Phoenix Suns", "Phoenix");
            session.save(d2);
            session.save(d3);

            Mecze mecz = new Mecze("2000-02-02", 100, 80, 1);
            mecz.setDruzGosp(d3);
            mecz.setDruzGosc(d2);
            Sezony sezon = new Sezony("2016/2017", "2016-03-03", "2017-03-30");
            session.save(sezon);
            mecz.setSezon(sezon);

            mecz.dodajHistZmianWMeczu(new HistZmianWMeczu(4, "02:41", 6, pz));
            mecz.dodajHistZmianWMeczu(new HistZmianWMeczu(6,"05:21", 4, session.get(PowodZejscia.class, 1)));

            session.save(mecz);

            Zawodnicy zaw = session.get(Zawodnicy.class, 4);
            Mecze mecz = session.get(Mecze.class, 8);
            OsiagnieciaZawWMeczu o = new OsiagnieciaZawWMeczu(zaw, mecz, 30, 6, 10, 4,2,1);
            session.save(o);

            Mecze mecze = session.get(Mecze.class, 8);
            session.delete(mecze);
*/
            //Zawodnicy zaw = session.get(Zawodnicy.class, 6);
            //System.out.println(zaw.toString());


            //Zawodnicy zaw = new Zawodnicy("ziomek", "usun sie", "1000-11-11", 1,1);
            //session.save(zaw);
//            Zawodnicy zaw = session.get(Zawodnicy.class, 4);
            //session.save(zaw);
//            session.delete(zaw);
/*
            Druzyny d = session.get(Druzyny.class, 8);

            Sezony s = session.get(Sezony.class, 5);

            Pozycje p = session.get(Pozycje.class, 1);

            Mecze mecz = session.get(Mecze.class, 3);

            PowodZejscia powod = session.get(PowodZejscia.class, 1);
*/
            //mecz.dodajHistZmianWMeczu(new HistZmianWMeczu(13, "02:41", 6, powod));
            //mecz.dodajHistZmianWMeczu(new HistZmianWMeczu(4,"05:21", 13, session.get(PowodZejscia.class, 1)));

            ////////////////////////////////////////////////////////////////////////
            /*Druzyny d2 = session.get(Druzyny.class, 10);
            Druzyny d3 = session.get(Druzyny.class, 11);

            Mecze mecz2 = new Mecze("2019-03-22", 90, 79, 2);
            mecz.setDruzGosp(d3);
            mecz.setDruzGosc(d2);
            Sezony sezon = session.get(Sezony.class, 5);

            mecz.setSezon(sezon);

            mecz.dodajHistZmianWMeczu(new HistZmianWMeczu(11, "02:41", 6, powod));
            mecz.dodajHistZmianWMeczu(new HistZmianWMeczu(4,"05:21", 11, session.get(PowodZejscia.class, 1)));

            session.save(mecz);
            */

/*            Query<Mecze> theQuery = session.createQuery("from Mecze", Mecze.class);
            List<Mecze> matches = theQuery.getResultList();*/

 /*            for (Mecze m:matches)
            {
                System.out.println(m.toString());
            }*/


            //session.createQuery("delete from Druzyny where id>=8").executeUpdate();
            session.getTransaction().commit();

            System.out.println("Successful connection !!!");
        } catch (Exception e) {
            e.printStackTrace();
        }

        //close session
        session.close();
        //close session factory
        sf.close();

        //launch the main view
        launch(args);
    }

}