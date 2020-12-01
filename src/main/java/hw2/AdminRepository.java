package hw2;

import hw2.data.Admin;
import hw2.data.City;

import java.util.HashMap;

public class AdminRepository {

    private HashMap<String, Admin> admins;

    public AdminRepository() {
        admins = new HashMap<>();
    }

    public void addAdmin(Admin admin){
        admins.put(admin.getUserName(), admin);
    }

    public Admin getAdmin(String userName){
        return admins.getOrDefault(userName, null);
    }

    public boolean hasAdmin(String userName){
        return admins.containsKey(userName);
    }

    public int size(){
        return admins.size();
    }
}
