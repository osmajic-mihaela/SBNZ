package demo.facts;


import java.io.Serializable;

public class User implements Serializable{

    private String email;
    private String password;
    private String name;
    private String surname;
    private String phoneNum;

    private Role role;

    public User(String email, String password, String name, String surname, String phoneNum, Role role) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.phoneNum = phoneNum;
        this.role=role;
    }

    public User() {
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public Role getRole(){return role;}


    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
    public  void setRole(Role role){ this.role = role;}

}
