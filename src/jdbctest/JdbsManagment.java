package jdbctest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbsManagment {

    private Connection conn = null;
    private Statement stm = null;
    private ResultSet rs = null;

    public void JdbsManagment() throws SQLException {
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/julianasqldb?useSSl=false", "root", "root");
        stm = conn.createStatement();//konekcija so url, user i pass + Ime na dataBasa

    }

    
    public void insert(String name, String lastName, int age) throws SQLException {
        try {
            PreparedStatement p = conn.prepareStatement("insert into artist (name,lastName,age) values(?,?,?)");
            p.setString(1, name);
            p.setString(2, lastName);
            p.setInt(3, age);
            
            int result = p.executeUpdate();
            System.out.println("Records inserted: " + result);
            
            } catch (SQLException e) {
            System.out.println(e);
                } finally {
                conn.close();
        }
    }

    
    public void update(String name, int id) throws SQLException {
        String updateTableSQL = "UPDATE ARTIST SET NAME = ? WHERE ID = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(updateTableSQL);
        preparedStatement.setString(1, name);//priper stetment mozi i klasna varijabla
        preparedStatement.setInt(2, id);// da se napravi i da se povikuva od sekade

        int result = preparedStatement.executeUpdate();
        System.out.println("Records inserted: " + result);

    }
    public void updateAge(int id, int age) throws SQLException {
        String updateTableSQL = "UPDATE ARTIST SET age = ? WHERE ID = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(updateTableSQL);
        preparedStatement.setInt(1, age);
        preparedStatement.setInt(2, id);
        
        int result = preparedStatement.executeUpdate();
        System.out.println("Records inserted: " + result);
    }

    
    public void showAll() throws SQLException {
        JdbsManagment();
        ResultSet rs = stm.executeQuery("select * from artist");
        
        
    System.out.println("   ID        Name                      Last name               Age      ");
    System.out.println("==========================================================================");    
        while (rs.next()) {
     System.out.println("Id is:" + fixLengthString((rs.getString("id")), 5)+"Name of Artist: " 
        + fixLengthString((rs.getString("name")), 10)+ " last name is: "+
    fixLengthString((rs.getString("Lastname")), 10)+ " age is: " + fixLengthString((rs.getString("age")), 3));

        }
    }
    
    public static String fixLengthString(String start, int length){
        if(start.length() >= length){
            return start.substring(0, 10);
    }
        else{
            while(start.length() < length){
            start += " ";
            }
        return start;
        }
    }
    
    
    public static String fixLengthString(int start, int length){
        String startString = String.valueOf(start);
        return startString;//da vraka brojot kako string
    
    }
    
    
    public void delete(int id) throws SQLException {
        String delete = "delete from ARTIST WHERE ID = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(delete);
        preparedStatement.setInt(1, id);
        
        int result = preparedStatement.executeUpdate();
        System.out.println("Records inserted: " + result);

    }
    
    public void findById(int id) throws SQLException{
        String fbId = "select * from ARTIST where id=?";
        PreparedStatement preparedStatement = conn.prepareStatement(fbId);
        preparedStatement.setInt(1, id);
    
        preparedStatement.executeQuery();
    
         ResultSet rs = preparedStatement.executeQuery();
             while(rs.next()){
             System.out.println(rs.getInt("id")+" "+ rs.getString("name")+" "+rs.getString("lastName"));
     }
   }
}
