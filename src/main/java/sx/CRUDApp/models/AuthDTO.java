package sx.CRUDApp.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class AuthDTO {
    @NotEmpty
    @Size(min = 2, max = 100, message = "Имя должно быть от 2 до 100 символов")
    private String username;
    private String password;

    public AuthDTO() {
    }

    public AuthDTO(String username, String password) {
        this.username = username;
        this.password = password;
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

    public void setPassword(String pass) {
        this.password = pass;
    }
}
