import java.sql.*;

public class DBHelper implements AutoCloseable{

    private static DBHelper instance;
    private static Connection connection;

    private DBHelper() {
    }

    public static DBHelper getInstance() {
        if (instance == null) {
            try {
                Class.forName("org.sqlite.JDBC");
                connection = DriverManager.getConnection("jdbc:sqlite:WorkDB.db");

            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
            instance = new DBHelper();
        }
        return instance;
    }

    public String findByLoginAndPassword(String login, String password) {
        try (PreparedStatement stm = connection
                .prepareStatement("SELECT * FROM chat_users WHERE UPPER(login)=UPPER('" + login + "') AND pass='" + password + "'");
             ResultSet resultSet = stm.executeQuery();) {
            if (resultSet.next()) {
                String nick = resultSet.getString("nick");
                return nick;
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }


        return null;
    }

    @Override
    public void close() throws Exception {

    }
}
