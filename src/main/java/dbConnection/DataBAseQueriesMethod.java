package dbConnection;

import static com.wci.auction.org.resources.BaseClass.prop;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.wci.auction.org.resources.BaseClass;

public class DataBAseQueriesMethod extends BaseClass {

	String eventID;
	String eventIDWA;
	String eventIDCA;
	String eventIDQC;
	String eventIDWA_RS;
	// DB Connection variable
	public static String Sale_Event_ID;
	public static String Sale_Event_ID_R70;
	public static String Sale_Event_IDWS;
	public static String Sale_Event_IDCA;
	public static String Sale_Event_IDQC;
	public static String Sale_Event_IDWS_RS;
	public String eventUpdateQuery;
	static Connection connection = null;
	static String databaseName = "Sale_Event";
	static String url = "jdbc:mysql://wci-qa-db-cluster.cluster-cudofuostxaw.us-west-2.rds.amazonaws.com:3306/Sale_Event";
	static String userName = "wci_qa_rw";
	static String password = "wciqa#rw";

	public void fetchSaleEventID(String eventID) {
		try {
			File src = new File(System.getProperty("user.dir") + "/Configuration/config.properties");
			FileInputStream fis = new FileInputStream(src);
			prop = new Properties();
			prop.load(fis);

//			eventID = prop.getProperty("CITSS_Event_ID");
			System.out.println("eventID : "+eventID);
			String query = "Select * from Sale_Event.Sale_Event Where CITSS_Event_id='" + eventID + "';";

			Statement st = connection.createStatement();
			ResultSet res = st.executeQuery(query);
			while (res.next()) {
				Sale_Event_ID = res.getString(1);
				System.out.println("Sale_Event_ID: " + Sale_Event_ID);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void fetchSaleEventID_PE1() {
		try {
			File src = new File(System.getProperty("user.dir") + "/Configuration/Config_Prescribed_Event.properties");
			FileInputStream fis = new FileInputStream(src);
			prop = new Properties();
			prop.load(fis);

			eventID = prop.getProperty("CITSS_Event_ID_PE1");
			System.out.println("eventID : "+eventID);
			String query = "Select * from Sale_Event.Sale_Event Where CITSS_Event_id='" + eventID + "';";

			Statement st = connection.createStatement();
			ResultSet res = st.executeQuery(query);
			while (res.next()) {
				Sale_Event_ID = res.getString(1);
				System.out.println("Sale_Event_ID: " + Sale_Event_ID);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void fetchSaleEventID_PE2() {
		try {
			File src = new File(System.getProperty("user.dir") + "/Configuration/Config_Prescribed_Event.properties");
			FileInputStream fis = new FileInputStream(src);
			prop = new Properties();
			prop.load(fis);

			eventID = prop.getProperty("CITSS_Event_ID_PE2");
			System.out.println("eventID : "+eventID);
			String query = "Select * from Sale_Event.Sale_Event Where CITSS_Event_id='" + eventID + "';";

			Statement st = connection.createStatement();
			ResultSet res = st.executeQuery(query);
			while (res.next()) {
				Sale_Event_ID = res.getString(1);
				System.out.println("Sale_Event_ID: " + Sale_Event_ID);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void fetchSaleEventID_PE3() {
		try {
			File src = new File(System.getProperty("user.dir") + "/Configuration/Config_Prescribed_Event.properties");
			FileInputStream fis = new FileInputStream(src);
			prop = new Properties();
			prop.load(fis);

			eventID = prop.getProperty("CITSS_Event_ID_PE3");
			System.out.println("eventID : "+eventID);
 
			String query = "Select * from Sale_Event.Sale_Event Where CITSS_Event_id='" + eventID + "';";

			Statement st = connection.createStatement();
			ResultSet res = st.executeQuery(query);
			while (res.next()) {
				Sale_Event_ID = res.getString(1);
				System.out.println("Sale_Event_ID: " + Sale_Event_ID);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void fetchSaleEventID_PE5() {
		try {
			File src = new File(System.getProperty("user.dir") + "/Configuration/Config_Prescribed_Event.properties");
			FileInputStream fis = new FileInputStream(src);
			prop = new Properties();
			prop.load(fis);

			eventID = prop.getProperty("CITSS_Event_ID_PE5");
			System.out.println("eventID : "+eventID);
 
			String query = "Select * from Sale_Event.Sale_Event Where CITSS_Event_id='" + eventID + "';";

			Statement st = connection.createStatement();
			ResultSet res = st.executeQuery(query);
			while (res.next()) {
				Sale_Event_ID = res.getString(1);
				System.out.println("Sale_Event_ID: " + Sale_Event_ID);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void fetchSaleEventID_PE6() {
		try {
			File src = new File(System.getProperty("user.dir") + "/Configuration/Config_Prescribed_Event.properties");
			FileInputStream fis = new FileInputStream(src);
			prop = new Properties();
			prop.load(fis);

			eventID = prop.getProperty("CITSS_Event_ID_PE6");
			System.out.println("eventID : "+eventID);
 
			String query = "Select * from Sale_Event.Sale_Event Where CITSS_Event_id='" + eventID + "';";

			Statement st = connection.createStatement();
			ResultSet res = st.executeQuery(query);
			while (res.next()) {
				Sale_Event_ID = res.getString(1);
				System.out.println("Sale_Event_ID: " + Sale_Event_ID);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void fetchSaleEventID_PE7() {
		try {
			File src = new File(System.getProperty("user.dir") + "/Configuration/Config_Prescribed_Event.properties");
			FileInputStream fis = new FileInputStream(src);
			prop = new Properties();
			prop.load(fis);

			eventID = prop.getProperty("CITSS_Event_ID_PE7");
			System.out.println("eventID : "+eventID);
 
			String query = "Select * from Sale_Event.Sale_Event Where CITSS_Event_id='" + eventID + "';";

			Statement st = connection.createStatement();
			ResultSet res = st.executeQuery(query);
			while (res.next()) {
				Sale_Event_ID = res.getString(1);
				System.out.println("Sale_Event_ID: " + Sale_Event_ID);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void fetchSaleEventID_PE8() {
		try {
			File src = new File(System.getProperty("user.dir") + "/Configuration/Config_Prescribed_Event.properties");
			FileInputStream fis = new FileInputStream(src);
			prop = new Properties();
			prop.load(fis);

			eventID = prop.getProperty("CITSS_Event_ID_PE8");
			System.out.println("eventID : "+eventID);
 
			String query = "Select * from Sale_Event.Sale_Event Where CITSS_Event_id='" + eventID + "';";

			Statement st = connection.createStatement();
			ResultSet res = st.executeQuery(query);
			while (res.next()) {
				Sale_Event_ID = res.getString(1);
				System.out.println("Sale_Event_ID: " + Sale_Event_ID);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void fetchSaleEventID_PE9() {
		try {
			File src = new File(System.getProperty("user.dir") + "/Configuration/Config_Prescribed_Event.properties");
			FileInputStream fis = new FileInputStream(src);
			prop = new Properties();
			prop.load(fis);

			eventID = prop.getProperty("CITSS_Event_ID_PE9");
			System.out.println("eventID : "+eventID);
 
			String query = "Select * from Sale_Event.Sale_Event Where CITSS_Event_id='" + eventID + "';";

			Statement st = connection.createStatement();
			ResultSet res = st.executeQuery(query);
			while (res.next()) {
				Sale_Event_ID = res.getString(1);
				System.out.println("Sale_Event_ID: " + Sale_Event_ID);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void fetchSaleEventID_PE10() {
		try {
			File src = new File(System.getProperty("user.dir") + "/Configuration/Config_Prescribed_Event.properties");
			FileInputStream fis = new FileInputStream(src);
			prop = new Properties();
			prop.load(fis);

			eventID = prop.getProperty("CITSS_Event_ID_PE10");
			System.out.println("eventID : "+eventID);
 
			String query = "Select * from Sale_Event.Sale_Event Where CITSS_Event_id='" + eventID + "';";

			Statement st = connection.createStatement();
			ResultSet res = st.executeQuery(query);
			while (res.next()) {
				Sale_Event_ID = res.getString(1);
				System.out.println("Sale_Event_ID: " + Sale_Event_ID);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void fetchSaleEventID_PE4() {
		try {
			File src = new File(System.getProperty("user.dir") + "/Configuration/Config_Prescribed_Event.properties");
			FileInputStream fis = new FileInputStream(src);
			prop = new Properties();
			prop.load(fis);

			eventID = prop.getProperty("CITSS_Event_ID_PE4");
			System.out.println("eventID : "+eventID);
 
			String query = "Select * from Sale_Event.Sale_Event Where CITSS_Event_id='" + eventID + "';";

			Statement st = connection.createStatement();
			ResultSet res = st.executeQuery(query);
			while (res.next()) {
				Sale_Event_ID = res.getString(1);
				System.out.println("Sale_Event_ID: " + Sale_Event_ID);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void fetchSaleEventID_RE_R70() {
		try {
			File src = new File(System.getProperty("user.dir") + "/Configuration/Config_Prescribed_Event.properties");
			FileInputStream fis = new FileInputStream(src);
			prop = new Properties();
			prop.load(fis);

			eventID = prop.getProperty("CITSS_Event_ID_RE1");
			System.out.println("eventID : "+eventID);
 
			String query = "Select * from Sale_Event.Sale_Event Where CITSS_Event_id='" + eventID + "';";

			Statement st = connection.createStatement();
			ResultSet res = st.executeQuery(query);
			while (res.next()) {
				Sale_Event_ID = res.getString(1);
				System.out.println("Sale_Event_ID: " + Sale_Event_ID);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void fetchSaleEventIDWA() {
		try {
			File src = new File(System.getProperty("user.dir") + "/Configuration/config.properties");
			FileInputStream fis = new FileInputStream(src);
			prop = new Properties();
			prop.load(fis);

			eventIDWA = prop.getProperty("WAAuctionEventID");
 
			String query = "Select * from Sale_Event.Sale_Event Where CITSS_Event_id='" + eventIDWA + "';";

			Statement st = connection.createStatement();
			ResultSet res = st.executeQuery(query);
			while (res.next()) {
				Sale_Event_IDWS= res.getString(1);
				System.out.println("Sale_Event_ID: " + Sale_Event_IDWS);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public void fetchSaleEventIDWA_RS() {
		try {
			File src = new File(System.getProperty("user.dir") + "/Configuration/config.properties");
			FileInputStream fis = new FileInputStream(src);
			prop = new Properties();
			prop.load(fis);

			eventIDWA_RS = prop.getProperty("ReserveSaleEventID_WA");
 
			String query = "Select * from Sale_Event.Sale_Event Where CITSS_Event_id='" + eventIDWA_RS + "';";

			Statement st = connection.createStatement();
			ResultSet res = st.executeQuery(query);
			while (res.next()) {
				Sale_Event_IDWS_RS= res.getString(1);
				System.out.println("Sale_Event_ID: " + Sale_Event_IDWS_RS);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void fetchSaleEventIDCA() {
		try {
			File src = new File(System.getProperty("user.dir") + "/Configuration/config.properties");
			FileInputStream fis = new FileInputStream(src);
			prop = new Properties();
			prop.load(fis);

			eventIDCA = prop.getProperty("CAAuctionEventID");
 
			String query = "Select * from Sale_Event.Sale_Event Where CITSS_Event_id='" + eventIDCA + "';";

			Statement st = connection.createStatement();
			ResultSet res = st.executeQuery(query);
			while (res.next()) {
				Sale_Event_IDCA= res.getString(1);
				System.out.println("Sale_Event_ID: " + Sale_Event_IDCA);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void fetchSaleEventIDQC() {
		try {
			File src = new File(System.getProperty("user.dir") + "/Configuration/config.properties");
			FileInputStream fis = new FileInputStream(src);
			prop = new Properties();
			prop.load(fis);

			eventIDQC = prop.getProperty("QCAuctionEventID");
 
			String query = "Select * from Sale_Event.Sale_Event Where CITSS_Event_id='" + eventIDQC + "';";

			Statement st = connection.createStatement();
			ResultSet res = st.executeQuery(query);
			while (res.next()) {
				Sale_Event_IDQC= res.getString(1);
				System.out.println("Sale_Event_ID: " + Sale_Event_IDQC);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void fetchSaleEventID2() {
		try {
			File src = new File(System.getProperty("user.dir") + "/Configuration/config.properties");
			FileInputStream fis = new FileInputStream(src);
			prop = new Properties();
			prop.load(fis);

			eventID = prop.getProperty("CITSS_Event_ID");
			System.out.println("eventID"+ eventID);
			String query = "Select * from Sale_Event.Sale_Event Where CITSS_Event_id='" + prop.getProperty("ReserveSaleEventID") + "';";

			Statement st = connection.createStatement();
			ResultSet res = st.executeQuery(query);
			while (res.next()) {
				Sale_Event_ID = res.getString(1);
				System.out.println("Sale_Event_ID: " + Sale_Event_ID);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void fetchSaleEventIDForR70() {
		try {
			File src = new File(System.getProperty("user.dir") + "/Configuration/config.properties");
			FileInputStream fis = new FileInputStream(src);
			prop = new Properties();
			prop.load(fis);

			eventID = prop.getProperty("CITSS_Event_ID_RE1");
			System.out.println("eventID :"+ eventID);
			String query = "Select * from Sale_Event.Sale_Event Where CITSS_Event_id='" + eventID + "';";
			Statement st = connection.createStatement();
			ResultSet res = st.executeQuery(query);
			while (res.next()) {
				Sale_Event_ID_R70 = res.getString(1);
				System.out.println("Sale_Event_ID: " + Sale_Event_ID_R70);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateCittsEventID(String eventId) throws SQLException {
		Statement st = connection.createStatement();
		DataBAseQueriesMethod ref = new DataBAseQueriesMethod();
		ref.fetchSaleEventID(eventId);
		eventUpdateQuery = "UPDATE `Sale_Event`.`Sale_Event` SET `CITSS_Event_ID` = '2' WHERE (`Sale_Event_ID` = '"+Sale_Event_ID+"');";
		st.executeUpdate(eventUpdateQuery);
	}
	
	public void updateCittsEventID_PE1() throws SQLException {
		Statement st = connection.createStatement();
		DataBAseQueriesMethod ref = new DataBAseQueriesMethod();
		ref.fetchSaleEventID_PE1();
 
 
		eventUpdateQuery = "UPDATE `Sale_Event`.`Sale_Event` SET `CITSS_Event_ID` = '2' WHERE (`Sale_Event_ID` = '"+Sale_Event_ID+"');";
		st.executeUpdate(eventUpdateQuery);
	}
	public void updateCittsEventID_PE2() throws SQLException {
		Statement st = connection.createStatement();
		DataBAseQueriesMethod ref = new DataBAseQueriesMethod();
		ref.fetchSaleEventID_PE2();
 
 
		eventUpdateQuery = "UPDATE `Sale_Event`.`Sale_Event` SET `CITSS_Event_ID` = '2' WHERE (`Sale_Event_ID` = '"+Sale_Event_ID+"');";
		st.executeUpdate(eventUpdateQuery);
	}
	public void updateCittsEventID_PE3() throws SQLException {
		Statement st = connection.createStatement();
		DataBAseQueriesMethod ref = new DataBAseQueriesMethod();
		ref.fetchSaleEventID_PE3();
 
 
		eventUpdateQuery = "UPDATE `Sale_Event`.`Sale_Event` SET `CITSS_Event_ID` = '2' WHERE (`Sale_Event_ID` = '"+Sale_Event_ID+"');";
		st.executeUpdate(eventUpdateQuery);
	}
	public void updateCittsEventID_PE5() throws SQLException {
		Statement st = connection.createStatement();
		DataBAseQueriesMethod ref = new DataBAseQueriesMethod();
		ref.fetchSaleEventID_PE5();
 
 
		eventUpdateQuery = "UPDATE `Sale_Event`.`Sale_Event` SET `CITSS_Event_ID` = '2' WHERE (`Sale_Event_ID` = '"+Sale_Event_ID+"');";
		st.executeUpdate(eventUpdateQuery);
	}
	public void updateCittsEventID_PE6() throws SQLException {
		Statement st = connection.createStatement();
		DataBAseQueriesMethod ref = new DataBAseQueriesMethod();
		ref.fetchSaleEventID_PE6();
 
 
		eventUpdateQuery = "UPDATE `Sale_Event`.`Sale_Event` SET `CITSS_Event_ID` = '2' WHERE (`Sale_Event_ID` = '"+Sale_Event_ID+"');";
		st.executeUpdate(eventUpdateQuery);
	}
	public void updateCittsEventID_PE7() throws SQLException {
		Statement st = connection.createStatement();
		DataBAseQueriesMethod ref = new DataBAseQueriesMethod();
		ref.fetchSaleEventID_PE7();
 
 
		eventUpdateQuery = "UPDATE `Sale_Event`.`Sale_Event` SET `CITSS_Event_ID` = '2' WHERE (`Sale_Event_ID` = '"+Sale_Event_ID+"');";
		st.executeUpdate(eventUpdateQuery);
	}
	public void updateCittsEventID_PE8() throws SQLException {
		Statement st = connection.createStatement();
		DataBAseQueriesMethod ref = new DataBAseQueriesMethod();
		ref.fetchSaleEventID_PE8();
 
 
		eventUpdateQuery = "UPDATE `Sale_Event`.`Sale_Event` SET `CITSS_Event_ID` = '2' WHERE (`Sale_Event_ID` = '"+Sale_Event_ID+"');";
		st.executeUpdate(eventUpdateQuery);
	}
	public void updateCittsEventID_PE9() throws SQLException {
		Statement st = connection.createStatement();
		DataBAseQueriesMethod ref = new DataBAseQueriesMethod();
		ref.fetchSaleEventID_PE9();
		eventUpdateQuery = "UPDATE `Sale_Event`.`Sale_Event` SET `CITSS_Event_ID` = '2' WHERE (`Sale_Event_ID` = '"+Sale_Event_ID+"');";
		st.executeUpdate(eventUpdateQuery);
		
	}
	public void updateCittsEventID_PE10() throws SQLException {
		Statement st = connection.createStatement();
		DataBAseQueriesMethod ref = new DataBAseQueriesMethod();
		ref.fetchSaleEventID_PE10();
		eventUpdateQuery = "UPDATE `Sale_Event`.`Sale_Event` SET `CITSS_Event_ID` = '2' WHERE (`Sale_Event_ID` = '"+Sale_Event_ID+"');";
		st.executeUpdate(eventUpdateQuery);
	}
	public void updateCittsEventID_PE4() throws SQLException {
		Statement st = connection.createStatement();
		DataBAseQueriesMethod ref = new DataBAseQueriesMethod();
		ref.fetchSaleEventID_PE4();
 
 
		eventUpdateQuery = "UPDATE `Sale_Event`.`Sale_Event` SET `CITSS_Event_ID` = '2' WHERE (`Sale_Event_ID` = '"+Sale_Event_ID+"');";
		st.executeUpdate(eventUpdateQuery);
	}
	public void updateCittsEventIDForReserveSale() throws SQLException {
		Statement st = connection.createStatement();
		DataBAseQueriesMethod ref = new DataBAseQueriesMethod();
		ref.fetchSaleEventID2();
 
 
		eventUpdateQuery = "UPDATE `Sale_Event`.`Sale_Event` SET `CITSS_Event_ID` = '2' WHERE (`Sale_Event_ID` = '"+Sale_Event_ID+"');";
		st.executeUpdate(eventUpdateQuery);
	}
	
	public void updateCittsEventIDForReserveSale_For_R70() throws SQLException {
		Statement st = connection.createStatement();
		DataBAseQueriesMethod ref = new DataBAseQueriesMethod();
		ref.fetchSaleEventIDForR70();
 
 
		eventUpdateQuery = "UPDATE `Sale_Event`.`Sale_Event` SET `CITSS_Event_ID` = '2' WHERE (`Sale_Event_ID` = '"+Sale_Event_ID_R70+"');";
		System.out.println(eventUpdateQuery);
		st.executeUpdate(eventUpdateQuery);
		
	}
	

	
	@SuppressWarnings("deprecation")
	public static  void setConnection()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		// To set the connection
		Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		connection = DriverManager.getConnection(url, userName, password);
		System.out.println("connection done successfully");
			//java.sql.Statement st = connection.createStatement();

	}
	public void updateCittsEventIDWS() throws SQLException {
		Statement st = connection.createStatement();
		DataBAseQueriesMethod ref = new DataBAseQueriesMethod();
		ref.fetchSaleEventIDWA();
 
 
		eventUpdateQuery = "UPDATE `Sale_Event`.`Sale_Event` SET `CITSS_Event_ID` = '2' WHERE (`Sale_Event_ID` = '"+Sale_Event_IDWS+"');";
		st.executeUpdate(eventUpdateQuery);
	}
	public void updateCittsEventIDWS_RS() throws SQLException {
		Statement st = connection.createStatement();
		DataBAseQueriesMethod ref = new DataBAseQueriesMethod();
		ref.fetchSaleEventIDWA_RS();
 
 
		eventUpdateQuery = "UPDATE `Sale_Event`.`Sale_Event` SET `CITSS_Event_ID` = '2' WHERE (`Sale_Event_ID` = '"+Sale_Event_IDWS_RS+"');";
		st.executeUpdate(eventUpdateQuery);
	}
	public void updateCittsEventIDCA() throws SQLException {
		Statement st = connection.createStatement();
		DataBAseQueriesMethod ref = new DataBAseQueriesMethod();
		ref.fetchSaleEventIDCA();
 
 
		eventUpdateQuery = "UPDATE `Sale_Event`.`Sale_Event` SET `CITSS_Event_ID` = '2' WHERE (`Sale_Event_ID` = '"+Sale_Event_IDCA+"');";
		st.executeUpdate(eventUpdateQuery);
	}
	public void updateCittsEventIDQC() throws SQLException {
		Statement st = connection.createStatement();
		DataBAseQueriesMethod ref = new DataBAseQueriesMethod();
		ref.fetchSaleEventIDQC();
		eventUpdateQuery = "UPDATE `Sale_Event`.`Sale_Event` SET `CITSS_Event_ID` = '2' WHERE (`Sale_Event_ID` = '"+Sale_Event_IDQC+"');";
		st.executeUpdate(eventUpdateQuery);
	}
	public static void updateAllFeilds() throws SQLException {
		DataBAseQueriesMethod ref = new DataBAseQueriesMethod();
//		ref.fetchSaleEventID(eventId);

		// Upload HL and PL
		String query1 = "UPDATE `Sale_Event`.`Sale_Event_Account` SET `Holding_Limit_Current` = '11598000', `Purchase_Limit_Current` = '5000', `Holding_Limit_Future` = '0', `Purchase_Limit_Future` = '0',Bond_Bid_Guarantee_Value='0' WHERE (fsa_Account_Number ='QC4446-5674' and Sale_Event_ID ='"
				+ Sale_Event_ID + "');";
		String query2 = "UPDATE `Sale_Event`.`Sale_Event_Account` SET `Holding_Limit_Current` = '11598000', `Purchase_Limit_Current` = '5000', `Holding_Limit_Future` = '0', `Purchase_Limit_Future` = '0',Bond_Bid_Guarantee_Value='0' WHERE (fsa_Account_Number ='QC4448-5678' and Sale_Event_ID ='"
				+ Sale_Event_ID + "');";
		String query3 = "UPDATE `Sale_Event`.`Sale_Event_Account` SET `Holding_Limit_Current` = '11598000', `Purchase_Limit_Current` = '5000', `Holding_Limit_Future` = '0', `Purchase_Limit_Future` = '0',Bond_Bid_Guarantee_Value='0' WHERE (fsa_Account_Number ='CA4376-5560' and Sale_Event_ID ='"
				+ Sale_Event_ID + "');";
		String query4 = "UPDATE `Sale_Event`.`Sale_Event_Account` SET `Holding_Limit_Current` = '11598000', `Purchase_Limit_Current` = '5000', `Holding_Limit_Future` = '0', `Purchase_Limit_Future` = '0',Bond_Bid_Guarantee_Value='0' WHERE (fsa_Account_Number ='CA4377-5562' and Sale_Event_ID ='"
				+ Sale_Event_ID + "');";
		String query5 = "UPDATE `Sale_Event`.`Sale_Event_Account` SET `Holding_Limit_Current` = '11598000', `Purchase_Limit_Current` = '5000', `Holding_Limit_Future` = '0', `Purchase_Limit_Future` = '0',Bond_Bid_Guarantee_Value='0' WHERE (fsa_Account_Number ='CA4378-5564' and Sale_Event_ID ='"
				+ Sale_Event_ID + "');";
	

//		Upload Bid Guarantee
		String query6 = "UPDATE `Sale_Event`.`Sale_Event_Account` SET `Cash_Bid_Guarantee` = '1000000' , Letter_Of_Credit_Bid_Guarantee_Value='0' Where fsa_Account_Number ='QC4446-5674' and Sale_Event_Id='"
				+ Sale_Event_ID + "';";
		String query7 = "UPDATE `Sale_Event`.`Sale_Event_Account` SET `Cash_Bid_Guarantee` = '90000' , Letter_Of_Credit_Bid_Guarantee_Value='0' Where fsa_Account_Number ='QC4448-5678' and Sale_Event_Id='"
				+ Sale_Event_ID + "';";
		String query8 = "UPDATE `Sale_Event`.`Sale_Event_Account` SET `Cash_Bid_Guarantee` = '65000' , Letter_Of_Credit_Bid_Guarantee_Value='0' Where fsa_Account_Number ='CA4376-5560' and Sale_Event_Id='"
				+ Sale_Event_ID + "';";
		String query9 = "UPDATE `Sale_Event`.`Sale_Event_Account` SET `Cash_Bid_Guarantee` = '90000' , Letter_Of_Credit_Bid_Guarantee_Value='0' Where fsa_Account_Number ='CA4377-5562' and Sale_Event_Id='"
				+ Sale_Event_ID + "';";
		String query10 = "UPDATE `Sale_Event`.`Sale_Event_Account` SET `Cash_Bid_Guarantee` = '200000' , Letter_Of_Credit_Bid_Guarantee_Value='0' Where fsa_Account_Number ='CA4378-5564' and Sale_Event_Id='"
				+ Sale_Event_ID + "';";

		System.out.println(query1);
		Statement st = connection.createStatement();
		st.executeUpdate(query1);
		st.executeUpdate(query2);
		st.executeUpdate(query3);
		st.executeUpdate(query4);
		st.executeUpdate(query5);
		st.executeUpdate(query6);
		st.executeUpdate(query7);
		st.executeUpdate(query8);
		st.executeUpdate(query9);
		st.executeUpdate(query10);
	}
	@SuppressWarnings("deprecation")
	public static void updateAllFeildsWA() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		connection = DriverManager.getConnection(url, userName, password);
		DataBAseQueriesMethod ref = new DataBAseQueriesMethod();
		ref.fetchSaleEventIDWA();

		// Upload HL and PL
		String query1 = "UPDATE `Sale_Event`.`Sale_Event_Account` SET `Holding_Limit_Current` = '11598000', `Purchase_Limit_Current` = '5000', `Holding_Limit_Future` = '0', `Purchase_Limit_Future` = '0',Bond_Bid_Guarantee_Value='0' WHERE (fsa_Account_Number ='WA6588-9944' and Sale_Event_ID ='"
				+ Sale_Event_IDWS + "');";
		String query2 = "UPDATE `Sale_Event`.`Sale_Event_Account` SET `Holding_Limit_Current` = '11598000', `Purchase_Limit_Current` = '5000', `Holding_Limit_Future` = '0', `Purchase_Limit_Future` = '0',Bond_Bid_Guarantee_Value='0' WHERE (fsa_Account_Number ='WA6589-9946' and Sale_Event_ID ='"
				+ Sale_Event_IDWS + "');";
		String query3 = "UPDATE `Sale_Event`.`Sale_Event_Account` SET `Holding_Limit_Current` = '11598000', `Purchase_Limit_Current` = '5000', `Holding_Limit_Future` = '0', `Purchase_Limit_Future` = '0',Bond_Bid_Guarantee_Value='0' WHERE (fsa_Account_Number ='WA6590-9948' and Sale_Event_ID ='"
				+ Sale_Event_IDWS + "');";
		String query4 = "UPDATE `Sale_Event`.`Sale_Event_Account` SET `Holding_Limit_Current` = '11598000', `Purchase_Limit_Current` = '5000', `Holding_Limit_Future` = '0', `Purchase_Limit_Future` = '0',Bond_Bid_Guarantee_Value='0' WHERE (fsa_Account_Number ='WA6591-9950' and Sale_Event_ID ='"
				+ Sale_Event_IDWS + "');";
		String query5 = "UPDATE `Sale_Event`.`Sale_Event_Account` SET `Holding_Limit_Current` = '11598000', `Purchase_Limit_Current` = '5000', `Holding_Limit_Future` = '0', `Purchase_Limit_Future` = '0',Bond_Bid_Guarantee_Value='0' WHERE (fsa_Account_Number ='WA6592-9952' and Sale_Event_ID ='"
				+ Sale_Event_IDWS + "');";
//		Upload Bid Guarantee
		String query6 = "UPDATE `Sale_Event`.`Sale_Event_Account` SET `Cash_Bid_Guarantee` = '1000000' , Letter_Of_Credit_Bid_Guarantee_Value='0' Where fsa_Account_Number ='WA6588-9944' and Sale_Event_Id='"
				+ Sale_Event_IDWS + "';";
		String query7 = "UPDATE `Sale_Event`.`Sale_Event_Account` SET `Cash_Bid_Guarantee` = '90000' , Letter_Of_Credit_Bid_Guarantee_Value='0' Where fsa_Account_Number ='WA6589-9946' and Sale_Event_Id='"
				+ Sale_Event_IDWS + "';";
		String query8 = "UPDATE `Sale_Event`.`Sale_Event_Account` SET `Cash_Bid_Guarantee` = '65000' , Letter_Of_Credit_Bid_Guarantee_Value='0' Where fsa_Account_Number ='WA6590-9948' and Sale_Event_Id='"
				+ Sale_Event_IDWS + "';";
		String query9 = "UPDATE `Sale_Event`.`Sale_Event_Account` SET `Cash_Bid_Guarantee` = '90000' , Letter_Of_Credit_Bid_Guarantee_Value='0' Where fsa_Account_Number ='WA6591-9950' and Sale_Event_Id='"
				+ Sale_Event_IDWS + "';";
		String query10 = "UPDATE `Sale_Event`.`Sale_Event_Account` SET `Cash_Bid_Guarantee` = '200000' , Letter_Of_Credit_Bid_Guarantee_Value='0' Where fsa_Account_Number ='WA6592-9952' and Sale_Event_Id='"
				+ Sale_Event_IDWS + "';";

		System.out.println(query1);
		Statement st = connection.createStatement();
		st.executeUpdate(query1);
		st.executeUpdate(query2);
		st.executeUpdate(query3);
		st.executeUpdate(query4);
		st.executeUpdate(query5);
		st.executeUpdate(query6);
		st.executeUpdate(query7);
		st.executeUpdate(query8);
		st.executeUpdate(query9);
		st.executeUpdate(query10);
	}
	@SuppressWarnings("deprecation")
	public static void updateAllFeildsCA() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		connection = DriverManager.getConnection(url, userName, password);
		DataBAseQueriesMethod ref = new DataBAseQueriesMethod();
		ref.fetchSaleEventIDCA();

		// Upload HL and PL
		String query1 = "UPDATE `Sale_Event`.`Sale_Event_Account` SET `Holding_Limit_Current` = '11598000', `Purchase_Limit_Current` = '5000', `Holding_Limit_Future` = '0', `Purchase_Limit_Future` = '0',Bond_Bid_Guarantee_Value='0' WHERE (fsa_Account_Number ='CA4376-5560' and Sale_Event_ID ='"
				+ Sale_Event_IDCA + "');";
		String query2 = "UPDATE `Sale_Event`.`Sale_Event_Account` SET `Holding_Limit_Current` = '11598000', `Purchase_Limit_Current` = '5000', `Holding_Limit_Future` = '0', `Purchase_Limit_Future` = '0',Bond_Bid_Guarantee_Value='0' WHERE (fsa_Account_Number ='CA4377-5562' and Sale_Event_ID ='"
				+ Sale_Event_IDCA + "');";
		String query3 = "UPDATE `Sale_Event`.`Sale_Event_Account` SET `Holding_Limit_Current` = '11598000', `Purchase_Limit_Current` = '5000', `Holding_Limit_Future` = '0', `Purchase_Limit_Future` = '0',Bond_Bid_Guarantee_Value='0' WHERE (fsa_Account_Number ='CA4378-5564' and Sale_Event_ID ='"
				+ Sale_Event_IDCA + "');";
		String query4 = "UPDATE `Sale_Event`.`Sale_Event_Account` SET `Holding_Limit_Current` = '11598000', `Purchase_Limit_Current` = '5000', `Holding_Limit_Future` = '0', `Purchase_Limit_Future` = '0',Bond_Bid_Guarantee_Value='0' WHERE (fsa_Account_Number ='CA4379-5556' and Sale_Event_ID ='"
				+ Sale_Event_IDCA + "');";
		String query5 = "UPDATE `Sale_Event`.`Sale_Event_Account` SET `Holding_Limit_Current` = '11598000', `Purchase_Limit_Current` = '5000', `Holding_Limit_Future` = '0', `Purchase_Limit_Future` = '0',Bond_Bid_Guarantee_Value='0' WHERE (fsa_Account_Number ='CA4380-5558' and Sale_Event_ID ='"
				+ Sale_Event_IDCA + "');";
//		Upload Bid Guarantee
		String query6 = "UPDATE `Sale_Event`.`Sale_Event_Account` SET `Cash_Bid_Guarantee` = '1000000' , Letter_Of_Credit_Bid_Guarantee_Value='0' Where fsa_Account_Number ='CA4376-5560' and Sale_Event_Id='"
				+ Sale_Event_IDCA + "';";
		String query7 = "UPDATE `Sale_Event`.`Sale_Event_Account` SET `Cash_Bid_Guarantee` = '90000' , Letter_Of_Credit_Bid_Guarantee_Value='0' Where fsa_Account_Number ='CA4377-5562' and Sale_Event_Id='"
				+ Sale_Event_IDCA + "';";
		String query8 = "UPDATE `Sale_Event`.`Sale_Event_Account` SET `Cash_Bid_Guarantee` = '65000' , Letter_Of_Credit_Bid_Guarantee_Value='0' Where fsa_Account_Number ='CA4378-5564' and Sale_Event_Id='"
				+ Sale_Event_IDCA + "';";
		String query9 = "UPDATE `Sale_Event`.`Sale_Event_Account` SET `Cash_Bid_Guarantee` = '90000' , Letter_Of_Credit_Bid_Guarantee_Value='0' Where fsa_Account_Number ='CA4379-5556' and Sale_Event_Id='"
				+ Sale_Event_IDCA + "';";
		String query10 = "UPDATE `Sale_Event`.`Sale_Event_Account` SET `Cash_Bid_Guarantee` = '200000' , Letter_Of_Credit_Bid_Guarantee_Value='0' Where fsa_Account_Number ='CA4380-5558' and Sale_Event_Id='"
				+ Sale_Event_IDCA + "';";

		System.out.println(query1);
		Statement st = connection.createStatement();
		st.executeUpdate(query1);
		st.executeUpdate(query2);
		st.executeUpdate(query3);
		st.executeUpdate(query4);
		st.executeUpdate(query5);
		st.executeUpdate(query6);
		st.executeUpdate(query7);
		st.executeUpdate(query8);
		st.executeUpdate(query9);
		st.executeUpdate(query10);
	}
	@SuppressWarnings("deprecation")
	public static void updateAllFeildsQC() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		connection = DriverManager.getConnection(url, userName, password);
		DataBAseQueriesMethod ref = new DataBAseQueriesMethod();
		ref.fetchSaleEventIDQC();

		// Upload HL and PL
		String query1 = "UPDATE `Sale_Event`.`Sale_Event_Account` SET `Holding_Limit_Current` = '11598000', `Purchase_Limit_Current` = '5000', `Holding_Limit_Future` = '0', `Purchase_Limit_Future` = '0',Bond_Bid_Guarantee_Value='0' WHERE (fsa_Account_Number ='QC4447-5676' and Sale_Event_ID ='"
				+ Sale_Event_IDQC + "');";
		String query2 = "UPDATE `Sale_Event`.`Sale_Event_Account` SET `Holding_Limit_Current` = '11598000', `Purchase_Limit_Current` = '5000', `Holding_Limit_Future` = '0', `Purchase_Limit_Future` = '0',Bond_Bid_Guarantee_Value='0' WHERE (fsa_Account_Number ='QC4450-5682' and Sale_Event_ID ='"
				+ Sale_Event_IDQC + "');";
		String query3 = "UPDATE `Sale_Event`.`Sale_Event_Account` SET `Holding_Limit_Current` = '11598000', `Purchase_Limit_Current` = '5000', `Holding_Limit_Future` = '0', `Purchase_Limit_Future` = '0',Bond_Bid_Guarantee_Value='0' WHERE (fsa_Account_Number ='QC4456-5694' and Sale_Event_ID ='"
				+ Sale_Event_IDQC + "');";
		String query4 = "UPDATE `Sale_Event`.`Sale_Event_Account` SET `Holding_Limit_Current` = '11598000', `Purchase_Limit_Current` = '5000', `Holding_Limit_Future` = '0', `Purchase_Limit_Future` = '0',Bond_Bid_Guarantee_Value='0' WHERE (fsa_Account_Number ='QC4457-5696' and Sale_Event_ID ='"
				+ Sale_Event_IDQC + "');";
		String query5 = "UPDATE `Sale_Event`.`Sale_Event_Account` SET `Holding_Limit_Current` = '11598000', `Purchase_Limit_Current` = '5000', `Holding_Limit_Future` = '0', `Purchase_Limit_Future` = '0',Bond_Bid_Guarantee_Value='0' WHERE (fsa_Account_Number ='QC4471-5724' and Sale_Event_ID ='"
				+ Sale_Event_IDQC + "');";
//		Upload Bid Guarantee
		String query6 = "UPDATE `Sale_Event`.`Sale_Event_Account` SET `Cash_Bid_Guarantee` = '1000000' , Letter_Of_Credit_Bid_Guarantee_Value='0' Where fsa_Account_Number ='QC4447-5676' and Sale_Event_Id='"
				+ Sale_Event_IDQC + "';";
		String query7 = "UPDATE `Sale_Event`.`Sale_Event_Account` SET `Cash_Bid_Guarantee` = '90000' , Letter_Of_Credit_Bid_Guarantee_Value='0' Where fsa_Account_Number ='QC4450-5682' and Sale_Event_Id='"
				+ Sale_Event_IDQC + "';";
		String query8 = "UPDATE `Sale_Event`.`Sale_Event_Account` SET `Cash_Bid_Guarantee` = '65000' , Letter_Of_Credit_Bid_Guarantee_Value='0' Where fsa_Account_Number ='QC4456-5694' and Sale_Event_Id='"
				+ Sale_Event_IDQC + "';";
		String query9 = "UPDATE `Sale_Event`.`Sale_Event_Account` SET `Cash_Bid_Guarantee` = '90000' , Letter_Of_Credit_Bid_Guarantee_Value='0' Where fsa_Account_Number ='QC4457-5696' and Sale_Event_Id='"
				+ Sale_Event_IDQC + "';";
		String query10 = "UPDATE `Sale_Event`.`Sale_Event_Account` SET `Cash_Bid_Guarantee` = '200000' , Letter_Of_Credit_Bid_Guarantee_Value='0' Where fsa_Account_Number ='QC4471-5724' and Sale_Event_Id='"
				+ Sale_Event_IDQC + "';";

		System.out.println(query1);
		Statement st = connection.createStatement();
		st.executeUpdate(query1);
		st.executeUpdate(query2);
		st.executeUpdate(query3);
		st.executeUpdate(query4);
		st.executeUpdate(query5);
		st.executeUpdate(query6);
		st.executeUpdate(query7);
		st.executeUpdate(query8);
		st.executeUpdate(query9);
		st.executeUpdate(query10);
	}
	@SuppressWarnings({ "deprecation", "static-access" })
	public static void main(String[] args)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		// To set the connection
		Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		connection = DriverManager.getConnection(url, userName, password);
		System.out.println("connection done successfully");

		DataBAseQueriesMethod ref = new DataBAseQueriesMethod();
		ref.updateAllFeilds();
	}

}
