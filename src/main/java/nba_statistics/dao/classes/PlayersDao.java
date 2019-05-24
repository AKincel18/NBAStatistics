package nba_statistics.dao.classes;

import nba_statistics.dao.interfaces.IPlayersDao;
import nba_statistics.entities.Druzyny;
import nba_statistics.entities.HistoriaDruzynZawodnika;
import nba_statistics.entities.Zawodnicy;
import nba_statistics.services.TeamsService;
import org.hibernate.query.Query;

import java.sql.Date;
import java.util.List;

public class PlayersDao extends Dao implements IPlayersDao {


    public PlayersDao(){

    }
    public void persist(Zawodnicy entity) {
        getCurrentSession().save(entity);
    }

    public int getData(String name, String surname, String date, float height, float weight, String team) {
        try{
            Date date1= Date.valueOf(date);
        }catch(IllegalArgumentException e){
            return 2;
        }
        Zawodnicy z = new Zawodnicy(name, surname, date, height, weight);
        TeamsService teamsService = new TeamsService();
        Druzyny d = teamsService.getTeam(team);
        if (d == null)
            return 1;

        z.setDruzyna(d);
        persist(z);
        return 0;
    }

    public int updatePlayer(String name, String surname, String team){
        //Query<Zawodnicy> theQuery = getCurrentSession().createQuery("from Zawodnicy where imie = :name and nazwisko = :surname",Zawodnicy.class);
        TeamsService teamsService = new TeamsService();
        Druzyny d = teamsService.getTeam(team);
        if (d == null)
            return 1;

        int id = d.getId();

        getCurrentSession().createQuery("update Zawodnicy set id_druzyny = :id  where imie = :name and nazwisko = :surname")
                .setParameter("id", id)
                .setParameter("name", name)
                .setParameter("surname", surname)
                .executeUpdate();
        return 0;


    }

    public List<Zawodnicy> getPlayers(String name, String surname){

        Query<Zawodnicy> theQuery = getCurrentSession().createQuery("from Zawodnicy where imie =:name and nazwisko = :surname")
                .setParameter("name", name)
                .setParameter("surname", surname);
        List<Zawodnicy> players = theQuery.getResultList();

        return players;
    }

    public List<HistoriaDruzynZawodnika> getPlayerTeamsHistory(int idPlayer){
        Query<HistoriaDruzynZawodnika> theQuery = getCurrentSession().createQuery("from HistoriaDruzynZawodnika where id_zawodnika = :idPlayer")
                .setParameter("idPlayer", idPlayer);
        List<HistoriaDruzynZawodnika> history = theQuery.getResultList();

        return history;
    }
}