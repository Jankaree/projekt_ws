package com.example.ws_projekt.Model.UserModel;


import com.example.ws_projekt.Model.CityCoordinate;
import jakarta.persistence.*;

@Entity
@Table(name = "user-Data")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "city_id")
    private CityCoordinate cityOfOrigin;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CityCoordinate getCityOfOrigin() {
        return cityOfOrigin;
    }

    public void setCityOfOrigin(CityCoordinate cityOfOrigin) {
        this.cityOfOrigin = cityOfOrigin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
