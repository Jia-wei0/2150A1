// CLASS: UserLibrary
//
// Author: Jiawei Fan, 7909503
//
// REMARKS: Manages user profile
// 
//-----------------------------------------
public class UserLibrary {
    private LinkedList<User> users = new LinkedList<>();

    public void add(User user) { 
        users.add(user); 
    }

    public User find(String username) { 
        return users.find(username); 
    }
}