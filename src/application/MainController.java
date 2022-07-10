package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import connection.MySqlConnection;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainController implements Initializable{
	
	@FXML
    private AnchorPane homePane;

    @FXML
    private Button jerryRice_button;

    @FXML
    private Button joeMontana_button;

    @FXML
    private Button joeBurrow_button;

    @FXML
    private Button lebronJames_button;

    @FXML
    private Button michaelJordan_button;

    @FXML
    private Button kobeBryant_button;

    @FXML
    private Button blastoise_button;

    @FXML
    private Button charizard_button;

    @FXML
    private Button venusaur_button;

    @FXML
    private Button logout_button;
    
    @FXML
    private TextArea txt_cart;
    
    @FXML
    private TextField total;

	// variables to connect with back end database to store and retrieve info
    Connection conn = null;
    Statement statement = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    // variable initialized to get total price of items ordered
    double totalPrice = 0.0;
    
    
    // method for user to logout of account after ordering cards
    // Once logout button is pressed on top right
    // controller reroutes user to the Login.FXML file where 
    // they would have to login again to order more items
    @FXML
    public void logout() {
    	try {
			Stage mainStage = (Stage) homePane.getScene().getWindow();
			mainStage.close();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/login/Login.FXML"));
			AnchorPane pane = loader.load();
			Scene scene = new Scene(pane, 663, 473);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    // method for user to order basketball card
    // Used tab pane to separate basketball, football, and pokemon cards
    // connection to database
    // create a new object of BasketballCard
    // set Id, selection name, and price of each basketball card
    // EventHandler initialized to each of the 3 options provided - one for LeBron James, Michael Jordan, and Kobe Bryant
    // every time 'place order' is clicked on, cart is updated and order is written to mysql database depending on user selection
    
    @FXML
    public void orderBasketballCard() {
    	conn = MySqlConnection.ConnectDb();
    	BasketballCard bballCard = new BasketballCard();
    	EventHandler<ActionEvent> eventLebronJames = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            { 
            	bballCard.setId(4);
            	bballCard.setSelection("LeBron James Card");
            	bballCard.setPrice(1007.99);
                txt_cart.appendText(bballCard.getSelection() + " $" + bballCard.getPrice() + "\n"); 
                totalPrice += bballCard.getPrice();
                writeBasketballOrder(bballCard);
            } 
        }; 
        lebronJames_button.setOnAction(eventLebronJames);
        
        
        EventHandler<ActionEvent> eventMichaelJordan = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            { 
            	bballCard.setId(5);
            	bballCard.setSelection("Michael Jordan Card");
            	bballCard.setPrice(1111.99);
                txt_cart.appendText(bballCard.getSelection() + " $" + bballCard.getPrice() + "\n"); 
                totalPrice += bballCard.getPrice();
                writeBasketballOrder(bballCard);
            } 
        }; 
        michaelJordan_button.setOnAction(eventMichaelJordan);
        
        
        EventHandler<ActionEvent> eventKobeBryant = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            { 
            	bballCard.setId(6);
            	bballCard.setSelection("Kobe Bryant Card");
            	bballCard.setPrice(999.99);
                txt_cart.appendText(bballCard.getSelection() + "$" + bballCard.getPrice() + "\n"); 
                totalPrice += bballCard.getPrice();
                writeBasketballOrder(bballCard);
            } 
        }; 
        kobeBryant_button.setOnAction(eventKobeBryant); 
    }

    
    // method for user to order pokemon card
    // Used tab pane to separate basketball, football, and pokemon cards
    // connection to database
    // create a new object of PokemonCard
    // set Id, selection name, and price of each card
    // EventHandler initialized to each of the 3 options provided - one for blastoise, charizard, and venusaur
    // every time 'place order' is clicked on, cart is updated and order is written to mysql database depending on user selection
    @FXML
    public void orderPokemonCard() {
    	conn = MySqlConnection.ConnectDb();
    	PokemonCard pCard = new PokemonCard();
    	EventHandler<ActionEvent> eventBlastoise = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            { 
            	pCard.setId(7);
            	pCard.setSelection("Blastoise Card");
            	pCard.setPrice(335.99);
                txt_cart.appendText(pCard.getSelection() + " $" + pCard.getPrice() + "\n"); 
                totalPrice += pCard.getPrice();
                writePokemonOrder(pCard);
            } 
        }; 
        blastoise_button.setOnAction(eventBlastoise);
        
        
        EventHandler<ActionEvent> eventCharizard = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            { 
            	pCard.setId(8);
            	pCard.setSelection("Charizard Card");
            	pCard.setPrice(789.99);
                txt_cart.appendText(pCard.getSelection() + " $" + pCard.getPrice() + "\n"); 
                totalPrice += pCard.getPrice();
                writePokemonOrder(pCard);
            } 
        }; 
        charizard_button.setOnAction(eventCharizard);
        
        
        EventHandler<ActionEvent> eventVenusaur = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            { 
            	pCard.setId(9);
            	pCard.setSelection("Venusaur Card");
            	pCard.setPrice(450.49);
                txt_cart.appendText(pCard.getSelection() + " $" + pCard.getPrice() + "\n"); 
                totalPrice += pCard.getPrice();
                writePokemonOrder(pCard);
            } 
        }; 
        venusaur_button.setOnAction(eventVenusaur);
    }

    
    // method for user to order football card
    // Used tab pane to separate basketball, football, and pokemon cards
    // connection to database
    // create a new object of FootballCard
    // set Id, selection name, and price of each football card
    // EventHandler initialized to each of the 3 options provided - one for Jerry Rice, Joe Montana, and Joe Burrow
    // every time 'place order' is clicked on, cart is updated and order is written to mysql database depending on user selection
    @FXML
    public void orderFootballCard() {
    	conn = MySqlConnection.ConnectDb();
    	FootballCard fbCard = new FootballCard();
    	EventHandler<ActionEvent> eventJerryRice = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            { 
            	fbCard.setId(1);
            	fbCard.setSelection("Jerry Rice Card");
            	fbCard.setPrice(1799.99);
                txt_cart.appendText(fbCard.getSelection() + " $" + fbCard.getPrice() + "\n");
                totalPrice += fbCard.getPrice();
                writeFootballOrder(fbCard);
            } 
        }; 
        jerryRice_button.setOnAction(eventJerryRice);
        
        
        EventHandler<ActionEvent> eventJoeMontana = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            { 
            	fbCard.setId(2);
            	fbCard.setSelection("Joe Montana Card");
            	fbCard.setPrice(1478.49);
                txt_cart.appendText(fbCard.getSelection() + " $" + fbCard.getPrice() + "\n");
                totalPrice += fbCard.getPrice();
                writeFootballOrder(fbCard);
            } 
        }; 
        joeMontana_button.setOnAction(eventJoeMontana);
        
        
        EventHandler<ActionEvent> eventJoeBurrow = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            { 
            	fbCard.setId(3);
            	fbCard.setSelection("Joe Burrow Card");
            	fbCard.setPrice(785.79);
                txt_cart.appendText(fbCard.getSelection() + " $" + fbCard.getPrice() + "\n"); 
                totalPrice += fbCard.getPrice();
                writeFootballOrder(fbCard);
            } 
        }; 
        joeBurrow_button.setOnAction(eventJoeBurrow);   
    }

    
    // helper method used in orderFootballCard()
    // method connects to database
    // mysql statement to update 'cards' table based on the football card selected
    // database stores orders for users
    public void writeFootballOrder(FootballCard fbCard) {
    	try {
    		conn = MySqlConnection.ConnectDb();
    		statement = conn.createStatement();
    		String write = "insert into cards(item_name, price) " +  "values (" + fbCard.getId() + ", " + fbCard.getPrice() + ")";
    		System.out.println(write);
    		statement.executeUpdate(write);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
    }
    
    
    // helper method used in orderBasketballCard()
    // method connects to database
    // mysql statement to update 'cards' database based on the basketball card selected
    // database stores orders per each user
    public void writeBasketballOrder(BasketballCard bballCard) {
    	try {
    		conn = MySqlConnection.ConnectDb();
    		statement = conn.createStatement();
    		String write = "insert into cards(item_name, price) " +  "values (" + bballCard.getId() + ", " + bballCard.getPrice() + ")";
    		System.out.println(write);
    		statement.executeUpdate(write);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
    }

    
    // helper method used in orderPokemonCard()
    // method connects to database
    // mysql statement to update 'cards' database based on the pokemon card selected
    // database stores orders per each user
    public void writePokemonOrder(PokemonCard pCard) {
    	try {
    		conn = MySqlConnection.ConnectDb();
    		statement = conn.createStatement();
    		String write = "insert into cards(item_name, price) " +  "values (" + pCard.getId() + ", " + pCard.getPrice() + ")";
    		System.out.println(write);
    		statement.executeUpdate(write);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}   	
    }

    
    // method to read orders database
    // methods writeBasketballOrder(), writePokemonOrder(), writeFootballOrder() all input data into 'cards' table
    // this method connects to the database and reads the order placed
    // once read, the cart updates and users can see what they ordered in the cart tab
    // total price text field also given for users to see their total amount they need to pay
    @FXML
    public void readOrder() {
    	try {
    		conn = MySqlConnection.ConnectDb();
    		String read = "select * from cards";
    		ps = conn.prepareStatement(read);
    		rs = ps.executeQuery();
    		
    		while(rs.next()) {
    			int order_id = rs.getInt(1);
    			int item_name = rs.getInt(2);
    			double price = rs.getDouble(3);
    		}
    		String s = String.valueOf(totalPrice);
			total.appendText(s);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    
    // method for users to clear their cart if they want to delete their order
    // if cart is empty, error message pops up
    // else all the parameters (total price, cart, total) are deleted and reset
    @FXML 
    public void clear() {
    	if(txt_cart.getText().equals("")) {
    		Alert error = new Alert(AlertType.ERROR);
			error.setContentText("No items added to cart");
	    	error.showAndWait();
    	}
    	else {
    		Alert success = new Alert(AlertType.CONFIRMATION);
    		success.setContentText("Items deleted");
    		success.showAndWait();
    		totalPrice = 0.0;
    		txt_cart.clear();
    		total.clear();
    	}
    }
    
    
    // method for users to proceed to input details once done with order
    // if total price is not calculated or nothing is added into cart, error messages pop up
    // else, user is redirected to User.FXML page where they can input their name, phone number, address, and any notes they have
    // cart, total, and total price clears every time an order is successful
    @FXML
    public void proceed(ActionEvent event) throws IOException {	
		if(total.getText().equals("")) {
			Alert error = new Alert(AlertType.ERROR);
			error.setContentText("Total not calculated");
	    	error.showAndWait();
		}
		else if(totalPrice == 0.0) {
			Alert error = new Alert(AlertType.ERROR);
			error.setContentText("No items selected");
	    	error.showAndWait();
		}
		else {
			Parent pane = FXMLLoader.load(getClass().getResource("/userDelivery/User.FXML"));
			Scene scene = new Scene(pane);
			Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
			stage.setScene(scene);
			stage.setHeight(422);
			stage.setWidth(629);
			stage.show();
			totalPrice = 0.0;
			txt_cart.clear();
			total.clear();
		}
    }
    
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
	}

}