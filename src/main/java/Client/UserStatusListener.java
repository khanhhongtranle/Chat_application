package Client;

import java.util.ArrayList;

public interface UserStatusListener {
    void online(String login);
    void offline(String login);
    void clean();
}
