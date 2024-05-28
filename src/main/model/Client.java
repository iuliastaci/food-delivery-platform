package main.model;

public class Client extends User{
    public Client(){
        super.setRole(Role.CLIENT);
    }

    public Client(int userId, String name, String email, String address, String password){
        super(userId, name, email, address, password, Role.CLIENT);
    }

}
