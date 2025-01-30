public class UserLibrary {
    private LinkedList<User> users = new LinkedList<>();

    public void add(User user) { users.add(user); }
    public User find(String username) { return users.find(username); }
}