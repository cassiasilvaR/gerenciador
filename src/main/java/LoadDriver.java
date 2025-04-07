import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class LoadDriver {

    
    private static String url = "jdbc:mysql://localhost:3306/sis_gerenciador";
    private static String user = "root";
    private static String password = "100874";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar ao banco de dados", e);
        }
    // public static void main(String[] args) {
       
    //     try{
    //         Class.forName("com.mysql.cj.jdbc.Driver");
    //     } catch (Exception exception){
    //         throw new RuntimeException("Falha ao conectar", exception);
    //     }
        
    //     try{ 
    //         Connection conn = DriverManager.getConnection(url, user, password);
    //         PreparedStatement statement =  conn.prepareStatement(sql_insert);
    //         Consumidor consumidor = new Consumidor("Jorge"); // Isso aqui vai lá pra main
    //         statement.setString(1, consumidor.getNome());
    //         statement.executeUpdate();                                   
    //     } catch (SQLException ex) {
    //         System.out.println("SQLException: " + ex.getMessage());
    //         System.out.println("SQLState: " + ex.getSQLState());
    //         System.out.println("VendorError: " + ex.getErrorCode());
    //      }
        
        

    //     // try {
                                                
    //     //     Consumidor consumidor = new Consumidor("Alberto");
    //     //     PreparedStatement stmt = conn.prepareStatement(sql); 

    //     //     stmt.setString(1, consumidor.getNome());
    //     //     stmt.executeUpdate();

    //     // } catch (SQLException ex) {
    //     //     throw new RuntimeException("Erro ao acessar consumidor", ex);
    //     // }
    }
}
