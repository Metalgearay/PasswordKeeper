import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;


public class databasebuilder {
	Connection c = null;
	Statement stmt;
	public void connect()
	{
		
		try{
		Class.forName("org.sqlite.JDBC");
		c = DriverManager.getConnection("jdbc:sqlite:pass.db");
		DatabaseMetaData dbm = c.getMetaData();
		ResultSet tables = dbm.getTables(null, null,"LoginInfo", null);
		if(tables.next())
		{
		   c.close();
		}
		else{
             stmt = c.createStatement();
			String sql = "create table LoginInfo"+
			              "( _id INTEGER PRIMARY KEY AUTOINCREMENT, "+
					      "WebsiteName text Not Null,"+
			              "UserName text Not Null,"+
					      "Password text Not Null,"+
			              "LoginUrl text Not Null, "+
					      "Notes text);";
			
			stmt.executeUpdate(sql);
			stmt.close();
			c.close();
			
			
			
			 
		}
		}
		catch(Exception e)
		{
			System.err.println(e.getClass().getName()+":"+e.getMessage());
			System.exit(0);
		}

	}
	public String[]  Populate (String name) throws SQLException, ClassNotFoundException
	{   ArrayList<String> array = new ArrayList<String>();
	Class.forName("org.sqlite.JDBC");
	c = DriverManager.getConnection("jdbc:sqlite:pass.db");
	    stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("select * from LoginInfo;"); 
	     while(rs.next())
	     {
	    	array.add(rs.getString(name)); 
	     }
	     rs.close();
	     stmt.close();
		String[] arrayx = array.toArray(new String[array.size()]);
		Arrays.sort(arrayx);
		c.close();
		return arrayx;
		
	}
	public String select(String name, String websiteName) throws ClassNotFoundException, SQLException
	{Class.forName("org.sqlite.JDBC");
	c = DriverManager.getConnection("jdbc:sqlite:pass.db");
    stmt = c.createStatement();
    ResultSet rs = stmt.executeQuery("select"+" * "+ "from LoginInfo"+" where"+" WebsiteName"+"="+"\""+websiteName+"\""+";");
	String namex = rs.getString(name);
	stmt.close();
	c.close();
	return namex;
	}
	public void insert(String name,String userName,String password, String url, String notes) throws SQLException, ClassNotFoundException
	{Class.forName("org.sqlite.JDBC");
	c = DriverManager.getConnection("jdbc:sqlite:pass.db");
    stmt = c.createStatement();
    if(name=="")
    {
    	
    }
    String sql = "Insert into LoginInfo (WebsiteName,Username,Password,LoginUrl,Notes) Values"+"("+"\""+name+"\""+","+"\""+userName+"\""+","+"\""+password+"\""+","+"\""+url+"\""+","+"\""+notes+"\""+")"+";";
	stmt.executeUpdate(sql);
	stmt.close();
	c.close();
	}
	public void update(String company , String field, String text) throws ClassNotFoundException, SQLException
	{
		Class.forName("org.sqlite.JDBC");
		c = DriverManager.getConnection("jdbc:sqlite:pass.db");
	    stmt = c.createStatement();
	    String sql = "Update LoginInfo set "+field+" "+"="+"\""+text+"\""+" WHERE WebsiteName ="+"\""+company+"\""+";";
	    stmt.executeUpdate(sql);
	    stmt.close();
	    c.close();
	}
	public void delete(String name)
	{
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			c = DriverManager.getConnection("jdbc:sqlite:pass.db");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    try {
			stmt = c.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    String sql = "Delete from LoginInfo where WebsiteName ="+"\""+name+"\""+";";
	    try {
			stmt.executeUpdate(sql);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    try {
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	}


