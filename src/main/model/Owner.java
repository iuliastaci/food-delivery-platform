package main.model;

public class Owner extends User{
    public Owner(){
        super.setRole(Role.OWNER);
    }

    public Owner(int userId, String name, String email, String address, String password){
        super(userId, name, email, address, password, Role.OWNER);
    }
}
