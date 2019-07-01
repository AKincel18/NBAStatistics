package nba_statistics.controllers.viewer;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import nba_statistics.controllers.HelpController;
import nba_statistics.entities.PlayerMatchAchievements;
import nba_statistics.entities.Players;
import nba_statistics.entities.Seasons;
import nba_statistics.services.MatchesService;
import nba_statistics.services.PlayersService;
import nba_statistics.services.SeasonsService;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Map.Entry.comparingByValue;
import static nba_statistics.others.Alerts.*;

public class ListOfTop10Shooters implements Initializable {

    @FXML
    private ComboBox<String> seasonComboBox;
    @FXML
    private ListView<String> topTenListView;
    @FXML
    private Button backButton;
    @FXML
    private Button selectListButton;
    @FXML
    private ImageView helpBtn;
    @FXML
    private Stage stage;

    List<String> toPrint= new ArrayList<>();


    public void setVisibleLabelsTopTenSchooters() {
        topTenListView.setVisible(true);
    }

    public void setInvisibleLabelsTopTenSchooters() {
        topTenListView.setVisible(false);
    }

    public void changeScreenBack(Event event) throws IOException {
        Parent backParent = FXMLLoader.load(getClass().getResource("/DataViewer.fxml"));
        Scene backScene = new Scene(backParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(backScene);
        window.show();
    }

    private void initComboBoxSeasons() {
        SeasonsService seasonsService = new SeasonsService();
        ArrayList<String> seasons = seasonsService.getAllSeasonsName();
        seasonComboBox.setItems(FXCollections.observableArrayList(seasons));
    }

    public void selectListTopTenSchooters() {
        SeasonsService seasonsService = new SeasonsService();
        MatchesService matchesService = new MatchesService();
        PlayersService playersService = new PlayersService();

        String nameSeason = seasonComboBox.getValue();

        Seasons season = seasonsService.getSeason(nameSeason);

        List<PlayerMatchAchievements> playerMatchAchievements = matchesService.getAchievementsPlayersInSeason(season.getId());
        Map<Integer, Integer> map = selectTenPlayersData(playerMatchAchievements);
        List<Players> playersList;
        ObservableList<String> listString = FXCollections.observableArrayList();

        if (map.size() < 1) {
            setInvisibleLabelsTopTenSchooters();
            getAlertNoTopTenPlayers();
        } else {
            setVisibleLabelsTopTenSchooters();

            int i = 0;
            for (Map.Entry<Integer, Integer> mapData : map.entrySet()) {
                playersList = playersService.getPlayersById(mapData.getKey());
                setVisibleLabelsTopTenSchooters();

                i++;
                listString.add(i + ". " + playersList.get(0).getSurname() + " " + playersList.get(0).getName() + "\r\t\t\t\t\t\t Scored points: " + mapData.getValue());

                if (i == 10) break;
            }
            topTenListView.setItems(listString);

            for(String s : listString){
                toPrint.add(s);
            }
        }
        if (map.size() < 10 && map.size() > 0) getAlertLessThanTopTenPlayers();

    }


    public Map<Integer, Integer> selectTenPlayersData(List<PlayerMatchAchievements> list) {
        Map<Integer, Integer> map = new TreeMap<>();
        int sum = 0;
        int id = -1;

        for (int i = 0; i < list.size(); i++) {
            sum = 0;
            id = list.get(i).getPlayer().getId();

            for (int j = 0; j < list.size(); j++) {
                if (id == list.get(j).getPlayer().getId()) {
                    sum += list.get(j).getScoredPoints();
                    list.remove(j);
                    j--;
                    i--;
                }
            }
            map.put(id, sum);
        }

        Map<Integer, Integer> sortedMap = map.entrySet()
                .stream()
                .sorted(Collections.reverseOrder(comparingByValue()))
                .collect(Collectors.toMap(
                        e -> e.getKey(),
                        e -> e.getValue(),
                        (e1, e2) -> e2, LinkedHashMap::new));
        return sortedMap;
    }

    @SuppressWarnings("Duplicates")
    @FXML
    void helpClicked(Event event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Help.fxml"));
        Parent accountParent = (Parent)loader.load();
        HelpController helpController = loader.getController();
        helpController.setView("/ListOfTop10ShootersViewer.fxml");
        helpController.init();
        Stage parent = (Stage)((Node)event.getSource()).getScene().getWindow();
        Stage window = new Stage();
        window.initModality(Modality.WINDOW_MODAL);
        window.initOwner(parent);
        window.setHeight(350);
        window.setWidth(500);
        window.setTitle("Help window");
        Scene reviewerScene = new Scene(accountParent);
        window.setScene(reviewerScene);
        window.show();
    }

    //print
    public void printToFile(ActionEvent event) throws IOException  {
        if(toPrint.isEmpty()) getAlertNoSeasonsOrNoPlayer();
        else{
            ObservableList<String> stringList = FXCollections.observableArrayList();
            stringList.add("a");
            stringList.add("b");

            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save Copy of Report");
            fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
            fileChooser.setInitialFileName("Second report.txt");

            File file = fileChooser.showSaveDialog(stage);

            if(file!= null){
                FileWriter fileWriter = new FileWriter(file);
                for(int i = 0; i< toPrint.size();i++){
                    fileWriter.write(toPrint.get(i));
                    fileWriter.write(System.getProperty( "line.separator" ));
                }
                fileWriter.flush();
                fileWriter.close();
            }
            toPrint.clear();
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setInvisibleLabelsTopTenSchooters();
        initComboBoxSeasons();
        helpBtn.setImage(new Image("/help.png"));
    }
}
