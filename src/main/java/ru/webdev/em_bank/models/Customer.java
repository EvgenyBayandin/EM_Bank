package ru.webdev.em_bank.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.springframework.lang.Nullable;

@Entity
@Table(name = "Customer")
public class Customer {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty(message = "Поле login не может быть пустым")
    @Size(min = 5, max = 100, message = "login должно быть от 5 до 100 символов")
    @Column(name = "login")
    private String login;

    @NotEmpty(message = "Поле password не может быть пустым")
    @Column(name = "password")
    @Max(value = 100, message = "Пароль не может быть более 100 символов")
    @Pattern(regexp = "(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$", message = "Пароль должен быть не менее 8 символов и содержать строчные и прописные латинские буквы, цифры, спецсимволы")
    private String password;

    @NotEmpty(message = "Поле Имя не может быть пустым")
    @Size(min = 2, max = 100, message = "Имя должно быть от 2 до 100 символов")
    @Column(name = "firstname")
    private String firstname;

    @NotEmpty(message = "Поле Фамилия не может быть пустым")
    @Size(min = 2, max = 100, message = "Фаимлия должно быть от 2 до 100 символов")
    @Column(name = "lastname")
    private String lastname;

    @Size(max = 100)
    @Column(name = "patronymic")
    private String patronymic;

    @NotEmpty(message = "Поле Дата рождения не может быть пустым")
    @Pattern(regexp = "^[0-9]{4}-[0-9]{2}-[0-9]{2}$", message = "Некорректный день рождения")
    @Column(name = "dateOfBirth")
    private String dateOfBirth;

    @NotEmpty(message = "Поле email не может быть пустым")
    @Column(name = "email")
    @Pattern(regexp = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$", message = "Некорректный email")
    private String email;

    @NotEmpty(message = "Поле phone не может быть пустым")
    @Column(name = "phone")
    @Pattern(regexp = "^[0-9]{10}$", message = "Некорректный номер телефона")
    private String phone;

    public Customer() {
    }

    public Customer(String login, String password, String email, String phone, String firstname, String lastname, String patronymic, String dateOfBirth) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.firstname = firstname;
        this.lastname = lastname;
        this.patronymic = patronymic;
        this.dateOfBirth = dateOfBirth;
    }

    public long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(@Nullable String patronymic) {
        this.patronymic = Optional.ofNullable(patronymic).orElse("");
    }

    public void setDateOfBirth(@Nullable @Pattern(regexp = "^[0-9]{4}-[0-9]{2}-[0-9]{2}$") String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getDateOfBirth() {
        return Optional.ofNullable(dateOfBirth).orElse("yyyy-MM-dd");
    }

    public List<String> getContacts() {
        return Arrays.asList(phone, email);
    }

}
