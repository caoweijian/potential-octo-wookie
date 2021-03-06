package data_center;

import java.sql.*;

public class BbkDatabaseConnector
{
	public final static String DRIVER = "com.mysql.jdbc.Driver";
	public String user = "root";
	public String password = "123456";
	public final static String URL_SERVER_CW = "jdbc:mysql://192.168.191.1/mydb";

    Connection connection;

    public BbkDatabaseConnector()
    {
    	try 
    	{	Class.forName(DRIVER);}
    	catch (ClassNotFoundException e) {}
        try 
        {	connection = DriverManager.getConnection(URL_SERVER_CW,user,password);}
        catch (SQLException e) {}
    }

    public void displayTable(String tableName)
    {
        String cmdStr = "select * from " + tableName;
        System.out.println(cmdStr);
        
        Statement statement;
		try 
		{	statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(cmdStr);

	        while (resultSet.next())
	        {	
	        	// fix me
	        	BbkOutline bbkOutline = new BbkOutline(resultSet);
	            bbkOutline.display();
	        }
	        resultSet.close(); 
		} catch (SQLException e) {e.printStackTrace();}

        System.out.println(cmdStr);
    }


    public BbkOutline getOutlineByName(String name)
    {
        String cmdStr = "select * from " + BbkDB.TABLE_MAIN + 
        		" where " + BbkDB.Header.Main.NAME + " = " + "'" + name + "'";
        BbkOutline bbkOutline = new BbkOutline();
        try 
		{	Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(cmdStr);
			bbkOutline.fillData_main(resultSet);	// resultSet.next() in this function
			resultSet.close();
		} catch (SQLException e) {e.printStackTrace();}

        return bbkOutline;
    }

    public BbkDetail getDetailByName(String name)
    {
        BbkDetail bbkDetail = new BbkDetail();
        
        try 
		{	Statement statement = connection.createStatement();
			ResultSet resultSet;
			
			resultSet = statement.executeQuery("select * from " + BbkDB.TABLE_MAIN + 
        		" where " + BbkDB.Header.Main.NAME + " = " + "'" + name + "'");
			bbkDetail.fillData_main(resultSet);
			resultSet = statement.executeQuery("select * from " + BbkDB.TABLE_CATEGORIES + 
	        		" where " + BbkDB.Header.Main.NAME + " = " + "'" + name + "'");
			bbkDetail.fillData_categories(resultSet);
			resultSet = statement.executeQuery("select * from " + BbkDB.TABLE_DEEP_SUBPARTS + 
	        		" where " + BbkDB.Header.Main.NAME + " = " + "'" + name + "'");
			bbkDetail.fillData_deepSubparts(resultSet);
			resultSet = statement.executeQuery("select * from " + BbkDB.TABLE_FEATURES + 
	        		" where " + BbkDB.Header.Main.NAME + " = " + "'" + name + "'");
			bbkDetail.fillData_features(resultSet);
			resultSet = statement.executeQuery("select * from " + BbkDB.TABLE_PARAMETERS + 
	        		" where " + BbkDB.Header.Main.NAME + " = " + "'" + name + "'");
			bbkDetail.fillData_parameters(resultSet);
			resultSet = statement.executeQuery("select * from " + BbkDB.TABLE_SPECIFIED_SUBSCARS + 
	        		" where " + BbkDB.Header.Main.NAME + " = " + "'" + name + "'");
			bbkDetail.fillData_specifiedSubscars(resultSet);
			resultSet = statement.executeQuery("select * from " + BbkDB.TABLE_SPECIFIED_SUBPARTS + 
	        		" where " + BbkDB.Header.Main.NAME + " = " + "'" + name + "'");
			bbkDetail.fillData_specifiedSubparts(resultSet);
			resultSet = statement.executeQuery("select * from " + BbkDB.TABLE_TWINS + 
	        		" where " + BbkDB.Header.Main.NAME + " = " + "'" + name + "'");
			bbkDetail.fillData_twins(resultSet);
			
			resultSet.close();
		} catch (SQLException e) {e.printStackTrace();}

        return bbkDetail;
    }


    public SearchResultList search(String keyword)
    {
        // fix me
        SearchResultList result = new SearchResultList();
        return result;
    }

    

}

