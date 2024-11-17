/*
  IMPORTANT NOTES
  You will have to setup your own PostgreSQL database for this web service to work.
  You will also have to manually fill your database with locations! example of this:
  at the http://localhost:xxxx/city endpoint make a post request with a body like this:
 {
  "city": "Stockholm",
  "latitude": 59.3293,
  "longitude": 18.0686
}

To fill in different locations!


To register Users simply make a post request at the http://localhost:xxxx/user enpoint with a body like this:

{
  "username": "John",
  "password": "Doe123",
  "cityOfOrigin": {
    "city": "Stockholm"
  }
}

Please note that this only works if you imported in a city called Stockholm!


 * */


package com.example.ws_projekt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WsProjektApplication {

    public static void main(String[] args) {
        SpringApplication.run(WsProjektApplication.class, args);
    }

}

