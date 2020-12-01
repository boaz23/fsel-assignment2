package hw2.data;

public class Admin {
    private String userName;
    private String password;
    private City city;

    public Admin(String userName, String password, City city) {
        this.userName = userName;
        this.password = password;
        this.city = city;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public City getCity() {
        return city;
    }
}
