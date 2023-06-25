package demo.facts;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class User implements Serializable{

    private String email;
    private String password;
    private String name;
    private String surname;
    private String phoneNum;

    private Role role;

    private Date birthDate;
    private double monthlyWage;
    private String employmentType;
    private Date employedUntil;

    private List<AccountPackage> accountPackages;

    public User() {
    }
    public User(String email, String password, String name, String surname, String phoneNum, Role role,
                Date birthDate, double monthlyWage, String employmentType, Date employedUntil) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.phoneNum = phoneNum;
        this.role=role;
        this.birthDate = birthDate;
        this.monthlyWage = monthlyWage;
        this.employmentType = employmentType;
        this.employedUntil = employedUntil;
        this.accountPackages = new ArrayList<>();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public double getMonthlyWage() {
        return monthlyWage;
    }

    public void setMonthlyWage(double monthlyWage) {
        this.monthlyWage = monthlyWage;
    }

    public String getEmploymentType() {
        return employmentType;
    }

    public void setEmploymentType(String employmentType) {
        this.employmentType = employmentType;
    }

    public Date getEmployedUntil() {
        return employedUntil;
    }

    public void setEmployedUntil(Date employedUntil) {
        this.employedUntil = employedUntil;
    }

    public List<AccountPackage> getAccountPackages() {
        return accountPackages;
    }

    public void setAccountPackages(List<AccountPackage> accountPackages) {
        this.accountPackages = accountPackages;
    }
}
