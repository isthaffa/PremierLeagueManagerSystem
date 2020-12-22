package JavaFxGui;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import models.FootballClub;
import models.Match;
import services.PremierLeagueManager;
import services.SortComparator;
import utils.Date;

import java.util.Collections;

public class GUI extends Application {


    PremierLeagueManager premierLeagueManager= PremierLeagueManager.getInstance();
    ObservableList<FootballClub> list; //observable list for clubs
    ObservableList<Match> match; //observable list for matches



    @Override
    public void start(Stage primaryStage) throws Exception {
//main pane to add to the scene
        AnchorPane mainPane=new AnchorPane();
        mainPane.setPrefSize(900,557);
        //children
        HBox hBox=new HBox();
        hBox.setPrefSize(850,557);
        hBox.setStyle("-fx-background-color:#2c0232");




        AnchorPane menuPane=new AnchorPane();
        menuPane.setPrefSize(219,557);
        //children

        Image image=new Image("file:images/premier.png");
        ImageView imageView=new ImageView();
        imageView.setImage(image);
        imageView.setFitWidth(212);
        imageView.setFitHeight(166);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
        imageView.setCache(true);
        imageView.pickOnBoundsProperty();

        VBox menu=new VBox();
        //children
        menu.setPrefSize(219,557);
        Button leagueBtn=new Button("CLUB LIST");
        leagueBtn.setId("leagueBtn");
        leagueBtn.setPrefSize(219,40);


        Button matchBtn=new Button("MATCH LIST");
        matchBtn.setId("matchBtn");
        matchBtn.setPrefSize(219,40);

        Button addBtn=new Button("ADD MATCH");
        addBtn.setId("addBtn");
        addBtn.setPrefSize(219,40);
        //children
        menu.setLayoutY(200);
        menu.getChildren().addAll(leagueBtn,matchBtn,addBtn);
        //children
        menuPane.getChildren().addAll(imageView,menu);


        //static pane
        AnchorPane staticPane=new AnchorPane();
        staticPane.setPrefSize(656,557);

        //1st phase
        AnchorPane leaguePane=new AnchorPane();
        leaguePane.setPrefSize(656,557);
        //children
        VBox vBox=new VBox();
        vBox.setPrefSize(656,557);
        AnchorPane sortPane=new AnchorPane();
        sortPane.setPrefSize(606,120);
        //sort by goal scored

        Label sortLabel=new Label("Sort by :");
        sortLabel.setStyle("-fx-text-fill:white;-fx-font-size:16;");
        sortLabel.setLayoutX(14);
        sortLabel.setLayoutY(18);
        Button sortGoalBtn=new Button("Goal Scored");
        sortLabel.setVisible(false);
        sortGoalBtn.setPrefSize(100,20);
        sortGoalBtn.setLayoutX(115);
        sortGoalBtn.setLayoutY(19);

        //visible label handling
        sortGoalBtn.setOnMouseEntered(evt->{
            sortLabel.setVisible(true);
        });
        sortGoalBtn.setOnMouseExited(evt->{
            sortLabel.setVisible(false);
        });
        //sort by win
        Button sortWinBtn=new Button("Wins");
        Label sortLabel2=new Label("Sort by :");
        sortLabel2.setStyle("-fx-text-fill:white;-fx-font-size:16;");
        sortLabel2.setLayoutX(294);
        sortLabel2.setLayoutY(18);
        sortLabel2.setLabelFor(sortWinBtn);
        sortLabel2.setVisible(false);

        sortWinBtn.setPrefSize(100,20);
        sortWinBtn.setLayoutX(386);
        sortWinBtn.setLayoutY(19);
        //visible label handling
        sortWinBtn.setOnMouseEntered(evt->{
            sortLabel2.setVisible(true);
        });
        sortWinBtn.setOnMouseExited(evt->{
            sortLabel2.setVisible(false);
        });

        sortPane.getChildren().addAll(sortLabel,sortGoalBtn,sortLabel2,sortWinBtn);

        AnchorPane tablePane=new AnchorPane();
        tablePane.setPrefSize(588,495);
        TableView<FootballClub> leagueTable=getLeagueTableView();
        leagueTable.setMinHeight(600);
        leagueTable.setPrefWidth(606);
        tablePane.getChildren().add(leagueTable);

        vBox.getChildren().addAll(sortPane,tablePane);
        leaguePane.getChildren().add(vBox);
        //2nd phase
        AnchorPane matchPane=new AnchorPane();
        matchPane.setPrefSize(656,557);
        VBox matchBox=new VBox();
        matchBox.setPrefSize(606,557);
        AnchorPane mPane=new AnchorPane();
        mPane.setPrefSize(606,120);
        //match table

        TableView<Match> matchTable=getMatchTableView();
        matchTable.setPrefSize(588,590);


        TextField textField=new TextField();
        textField.setPromptText("Enter date here [format:d/m]");
        textField.setPrefSize(311,25);
        textField.setLayoutX(13);
        textField.setLayoutY(8);
        Button search= new Button("Search");
        search.setPrefSize(117,25);
        search.setLayoutX(324);
        search.setLayoutY(8);
        HBox mbox=new HBox();
        mbox.setPrefSize(606,120);
        mbox.setLayoutX(20);
        mbox.getChildren().addAll(textField,search);

        mbox.setVisible(false);
        matchBox.getChildren().addAll(mbox,matchTable);
        matchPane.getChildren().add(matchBox);



        //children
        staticPane.getChildren().addAll(matchPane,leaguePane);

        hBox.getChildren().addAll(menuPane,staticPane);
        //children

        //button event handling functions
        //league button
        leagueBtn.setOnAction(e->{
            System.out.println(premierLeagueManager);

            FadeTransition fadeTransition=new FadeTransition(Duration.millis(600));
            fadeTransition.setOnFinished(ev->{
                leaguePane.toFront();
            });
            sortPane.setVisible(true);
            mbox.setVisible(false);

            fadeTransition.setNode(leaguePane);
            fadeTransition.setFromValue(0);
            fadeTransition.setToValue(1);
            fadeTransition.play();
            matchTable.refresh();
            list.sort(new SortComparator().reversed());


        });

        matchBtn.setOnAction(e->{



            FadeTransition fadeTransition=new FadeTransition(Duration.millis(600));

            fadeTransition.setOnFinished(ev->{
                matchPane.toFront();

            });
            mbox.setVisible(true);
            sortPane.setVisible(false);

            fadeTransition.setNode(matchPane);
            fadeTransition.setFromValue(0);
            fadeTransition.setToValue(1);
            fadeTransition.play();
            matchTable.refresh();
            Collections.sort(match);
        });

        addBtn.setOnAction(e->{
            Match match= PremierLeagueManager.getInstance().addRandomMatch();
            makeLabel(match.getDate().toString(),match.getHomeTeam().getClubName(),match.getAwayTeam().getClubName(),match.getHomeTeamScore(),match.getAwayTeamScore());

            leagueTable.refresh();

            list.sort(new SortComparator().reversed());
            matchTable.refresh();

        });

        search.setOnAction(e->{
            Alert alert = new Alert(Alert.AlertType.NONE);

            if(Date.DateIsValid(textField.getText())) {

                    String[] dt=textField.getText().split("/");

                    int day = Integer.parseInt(dt[0]);
                    int month = Integer.parseInt(dt[1]);
                    int year=Integer.parseInt(dt[2]);
                    if(year==20||year==2020){
                    System.out.println(day);
                    System.out.println(month);
                System.out.println(year);
                    Date date = new Date(day, month);
                    Match match;
                    match = premierLeagueManager.getMatchByDate(date);
                    if (match != null) {
                        String teamA = match.getHomeTeam().getClubName();
                        int scoreA = match.getHomeTeamScore();
                        String teamB = match.getAwayTeam().getClubName();
                        int scoreB = match.getAwayTeamScore();

                        makeLabel(date.toString(), teamA, teamB, scoreA, scoreB);

                    } else {
                        alert.setAlertType(Alert.AlertType.INFORMATION);
                        alert.setContentText("No Match found on " + date);
                        alert.show();
                    }

                } else {
                    alert.setAlertType(Alert.AlertType.ERROR);
                    alert.setContentText("you must enter a date in 2020 ");
                    alert.show();

                }
            }else {
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setContentText("you must enter a valid date to continue\n(format:Day/Month/Year)");
                alert.show();

            }
        });

        sortWinBtn.setOnAction(e->{
            list.sort(premierLeagueManager.sortByWinCount().reversed());
        });

        sortGoalBtn.setOnAction(e->{
            list.sort(premierLeagueManager.sortByGoalsCount().reversed());
        });



        //


        mainPane.getChildren().add(hBox);

        Scene scene=new Scene(mainPane,900,557);
        scene.getStylesheets().add("JavaFxGui/gui.css"); //css

        primaryStage.setScene(scene);

        primaryStage.setTitle("Premier League 2020");
        primaryStage.sizeToScene();
        primaryStage.show();



    }

    public void makeLabel(String date,String text1,String text2,int scoreA,int scoreB){
        Stage stage=new Stage();
        stage.setTitle("Match Details");
        stage.initModality(Modality.APPLICATION_MODAL);

        AnchorPane pane=new AnchorPane();
        pane.setPrefSize(710,300);

        Label datelb=new Label(date);
        datelb.setPrefSize(85,35);
        datelb.setLayoutX(14);
        datelb.setLayoutY(14);
        Label home=new Label(text1);
        home.setPrefSize(233,36);
        home.setLayoutY(102);
        home.setLayoutX(14);
        home.setTextAlignment(TextAlignment.CENTER);
        home.setStyle("-fx-font-size:25px");
        Label away=new Label(text2);
        away.setPrefSize(207,36);
        away.setLayoutY(102);
        away.setLayoutX(492);
        away.setTextAlignment(TextAlignment.CENTER);
        away.setStyle("-fx-font-size:25px");
        Label score=new Label(scoreA+"");
        score.setPrefSize(104,141);
        score.setLayoutX(235);
        score.setLayoutY(58);
        score.setTextAlignment(TextAlignment.CENTER);
        score.setStyle("-fx-font-size:96px");



        Label colon=new Label(":");
        colon.setPrefSize(21,141);
        colon.setLayoutX (335 );
        colon.setLayoutY(50);
        colon.setTextAlignment(TextAlignment.CENTER);
        colon.setStyle("-fx-font-size:96px");

        Label score2=new Label(""+scoreB);
        score2.setPrefSize(104,141);
        score2.setLayoutX(383);
        score2.setLayoutY(58);
        score2.setTextAlignment(TextAlignment.CENTER);
        score2.setStyle("-fx-font-size:96px");


        Label status=new Label();
        status.setPrefSize(378,34);
        status.setTextAlignment(TextAlignment.CENTER);
        status.setLayoutY(191);
        status.setLayoutX(187);
        status.setFont(Font.font("arial",FontWeight.EXTRA_BOLD,25));



        if(scoreA>scoreB){
            status.setText(" "+text1+" won the match ");


        }
        if(scoreA<scoreB){
            status.setText(" "+text2+" won the match ");

        }
        if(scoreA==scoreB){
            status.setText(" Match Drawn ");
        }



        pane.getChildren().addAll(datelb,home,away,score,colon,score2,status);

        stage.setScene(new Scene(pane,710,300));
        stage.setAlwaysOnTop(true);
        stage.show();

    }




    public TableView getLeagueTableView(){
        //creating  table view for league clubs
        TableView<FootballClub> footballClubTableView=new TableView<>();

        //creating columns for the table view
        //for name
        TableColumn<FootballClub,String> nameColumn=new TableColumn<>("Club Name");
        nameColumn.setMaxWidth(250);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("ClubName"));
        //for location
        TableColumn<FootballClub,String> locationColumn=new TableColumn<>("Location");
        locationColumn.setMaxWidth(200);
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        //for points
        TableColumn<FootballClub,Integer> pointsColumn=new TableColumn<>("Pts");
        pointsColumn.setPrefWidth(60);
        pointsColumn.setCellValueFactory(new PropertyValueFactory<>("Points"));
        //for matchesWon
        TableColumn<FootballClub,Integer> matchesWonColumn=new TableColumn<>("W");
        matchesWonColumn.setPrefWidth(60);
        matchesWonColumn.setCellValueFactory(new PropertyValueFactory<>("matchesWon"));
        //for matchesDrawn
        TableColumn<FootballClub,Integer> matchesDrawnColumn=new TableColumn<>("DR");
        matchesDrawnColumn.setPrefWidth(60);
        matchesDrawnColumn.setCellValueFactory(new PropertyValueFactory<>("matchesDrawn"));
        //for matchesDefeated
        TableColumn<FootballClub,Integer> matchesDefeatedColumn=new TableColumn<>("DF");
        matchesDefeatedColumn.setPrefWidth(60);
        matchesDefeatedColumn.setCellValueFactory(new PropertyValueFactory<>("matchesDefeated"));
        //for scoredGoals
        TableColumn<FootballClub,Integer> scoredGoalsColumn=new TableColumn<>("GS");
        scoredGoalsColumn.setPrefWidth(60);
        scoredGoalsColumn.setCellValueFactory(new PropertyValueFactory<>("scoredGoals"));
        //for goalDifference
        TableColumn<FootballClub,Integer> goalDifferenceColumn=new TableColumn<>("GD");
        goalDifferenceColumn.setPrefWidth(60);
        goalDifferenceColumn.setCellValueFactory(new PropertyValueFactory<>("goalDifference"));
        //for matchesPlayed
        TableColumn<FootballClub,Integer> matchesPlayedColumn=new TableColumn<>("MP");
        matchesPlayedColumn.setPrefWidth(60);
        matchesPlayedColumn.setCellValueFactory(new PropertyValueFactory<>("matchesPlayed"));
        list= FXCollections.observableArrayList();
        list.addAll(premierLeagueManager.getFootballClubs());

        list.sort(new SortComparator().reversed());
        footballClubTableView.setItems(list);

        //set columns
        footballClubTableView.getColumns().addAll(nameColumn,locationColumn,pointsColumn,scoredGoalsColumn,goalDifferenceColumn,matchesWonColumn,matchesDefeatedColumn,matchesDrawnColumn,matchesPlayedColumn);


        return footballClubTableView;

    }
    public TableView getMatchTableView(){
            TableView<Match> matchTable=new TableView<>();
            //creating columns for the table view
            //for date
            TableColumn<Match,String> dateColumn=new TableColumn<>("Date");
            dateColumn.setPrefWidth(100);
            dateColumn.setCellValueFactory(val-> new SimpleStringProperty(String.valueOf (val.getValue().getDate())));
            //for teamA
            TableColumn<Match,String> teamAColumn=new TableColumn<>("Team A");
            teamAColumn.setPrefWidth(139);
            teamAColumn.setCellValueFactory(val->new SimpleStringProperty(val.getValue().getHomeTeam().getClubName()));
            //for teamB
            TableColumn<Match,String> teamBColumn=new TableColumn<>("Team B");
            teamBColumn.setPrefWidth(139);
            teamBColumn.setCellValueFactory(val->new SimpleStringProperty(val.getValue().getAwayTeam().getClubName()));
            //for teamAScore
            TableColumn<Match,Integer> scoreAColumn=new TableColumn<>("Score of team A");
            scoreAColumn.setPrefWidth(100);
            scoreAColumn.setCellValueFactory(new PropertyValueFactory<>("homeTeamScore"));
            //for teamBscore
            TableColumn<Match,Integer> scoreBColumn=new TableColumn<>("Score of team B");
            scoreBColumn.setPrefWidth(100);
            scoreBColumn.setCellValueFactory(new PropertyValueFactory<>("awayTeamScore"));

            matchTable.getColumns().addAll(dateColumn,teamAColumn,scoreAColumn,scoreBColumn,teamBColumn);
               match=FXCollections.observableArrayList();
               match.addAll(premierLeagueManager.getMatchList());
               Collections.sort(match);
               matchTable.setItems(match);

            return matchTable;
    }
}



