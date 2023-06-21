package coneccionsocket;

import coneccionsocket.Utils.ConstGlobal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

/**
 * @author angel
 */
public class ClientPsql {

    private Connection con = null;
    private Statement stmt = null;

    private String host;
    private int port;
    private String user;
    private String pass;
    private String dbName;

    public ClientPsql(String host, int port, String user, String pass, String dbname) {
        this.host = host;
        this.port = port;
        this.user = user;
            this.pass = pass;
        this.dbName = dbname;
    }

    public ClientPsql() {
        this.host = ConstGlobal.SERVIDOR;
        this.port = ConstGlobal.PORT_DB;
        this.user = ConstGlobal.USER_SERV;
            this.pass = ConstGlobal.PASS_SERV;
        this.dbName = ConstGlobal.DB_NAME;
    }

    public Connection conectar(){
        
        String url = ConstGlobal.PSQL + host + ":" + port + ConstGlobal.Slash + dbName;
        try {
            //Class.forName(ConstGlobal.fileNamePostgres);
            con = DriverManager.getConnection(url, user, pass);
        } catch (SQLException e ) {
            System.err.println(" Class ClientePsql dice: Ocurrio un error al momento de conectar()");
        }
        return con;
    }

    public void closeConection(){
        try {
            if (con != null) {
                con.close();
            }
            
        } catch (SQLException e ) {
            System.err.println(" Class ClientePsql dice: Ocurrio un error al momento de closeConection()");
        }
    }
    private String sql(String token) {
        String to = "'%" + token + "%'";
        return "SELECT * FROM PERSONA WHERE (per_nom like " + to + " or per_appm like " + to + " )";
    }

    public String getPersona() throws SQLException {
        String resultado = "";
        stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from persona");

        while (rs.next()) {
            resultado += rs.getInt("per_cod") + " | " + rs.getString("per_nom").trim() + " | "
                    + rs.getString("per_appm").trim() + " | " + rs.getString("per_prof").trim() + " | "
                    + rs.getString("per_telf").trim() + " | " + rs.getString("per_cel").trim() + " | "
                    + rs.getString("per_email").trim() + " | " + rs.getString("per_dir").trim() + " | "
                    + rs.getDate("per_fnac") + " | " + rs.getString("per_flug").trim() + " | " + rs.getInt("per_type")
                    + " | " + rs.getString("per_pass").trim() + "\n";
        }
        rs.close();
        stmt.close();
        return resultado;
    }

    
    public String consulta(String token) throws SQLException {
        String resultado = "";
        stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sql(token));

        while (rs.next()) {
            resultado += rs.getInt("per_cod") + " | " + rs.getString("per_nom").trim() + " | "
                    + rs.getString("per_appm").trim() + " | " + rs.getString("per_prof").trim() + " | "
                    + rs.getString("per_telf").trim() + " | " + rs.getString("per_cel").trim() + " | "
                    + rs.getString("per_email").trim() + " | " + rs.getString("per_dir").trim() + " | "
                    + rs.getDate("per_fnac") + " | " + rs.getString("per_flug").trim() + " | " + rs.getInt("per_type")
                    + " | " + rs.getString("per_pass").trim() + "\n";
        }
        rs.close();
        stmt.close();
        return resultado;
    }

    public void close() throws SQLException {
        con.close();
    }

}
