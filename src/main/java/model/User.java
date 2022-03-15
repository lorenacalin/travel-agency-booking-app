package model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    @Column(name = "user_id", unique = true, nullable = false)
    private Integer userId;

    @Column(name = "username", unique = true, nullable = false, length = 45)
    private String username;

    @Column(name = "password", nullable = false, length = 45)
    private String password;

    @Column(name = "type", nullable = false, length = 45)
    private String type;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_package", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "package_id"))
    private Set<VacationPackage> vacationPackages;

    public User() {
    }

    public User(String username, String password, String type) {
        this.userId = null;
        this.username = username;
        this.password = password;
        this.type = type;
        this.vacationPackages = new HashSet<>();
    }

    public Integer getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getType() {
        return type;
    }

    public Set<VacationPackage> getVacationPackages() {
        return vacationPackages;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", type='" + type + '\'' +
                ", vacationPackages=" + vacationPackages +
                '}';
    }
}
