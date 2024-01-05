import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ChatMonitor {
    private final Map<String, MySocket> users = new HashMap<>();
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    public void addUser(String nick, MySocket socket) {
        lock.writeLock().lock();
        try {
            users.put(nick, socket);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void removeUser(String nick) {
        lock.writeLock().lock();
        try {
            users.remove(nick);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public MySocket getUserSocket(String nick) {
        lock.readLock().lock();
        try {
            return users.get(nick);
        } finally {
            lock.readLock().unlock();
        }
    }

    public boolean doesUserExist(String nick) {
        lock.readLock().lock();
        try {
            return users.containsKey(nick);
        } finally {
            lock.readLock().unlock();
        }
    }

    public void sendMessage(String sender, String message) {
        lock.readLock().lock();
        try {
            // El missatge apareixerá als terminals de tots els usuaris menys del sender
            for (String nick : users.keySet()) {
                if (!sender.equals(nick)) {
                    users.get(nick).println(sender + ": " + message);
                }
            }
        } finally {
            lock.readLock().unlock();
        }
    }

}
