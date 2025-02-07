package com.example.ws_projekt.Model.UserModel;


import com.example.ws_projekt.Model.CityCoordinate;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "user-Data")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String username;
    @NotBlank
    @Size(min = 5, max = 10, message = "Size must be 5 to 10 characters long!")
    private String password;

    private String role;



    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "city_id")
    private CityCoordinate cityOfOrigin;

    public String getRole() {
        return role != null ? role : "ROLE_USER";
    }


    public void setRole(String role) {
        this.role = role;
    }



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
