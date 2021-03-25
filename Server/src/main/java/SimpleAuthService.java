public class SimpleAuthService implements AuthService {


    private final DBHelper dbHelper = DBHelper.getInstance();

    public String getNicknameByLoginAndPassword(String login, String password) {
        return dbHelper.findByLoginAndPassword(login, password);
    }
}
