package ru.webdev.em_bank.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "Account")
public class Account {

    @Id
    @Column(name = "account_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToMany(mappedBy = "accounts")
    private List<Transaction> transactions;

    @NotEmpty
    @Column(name = "customer_id")
    private long customer_id;

    @NotEmpty
    @Column(name = "balance")
    private BigDecimal balance;

    @NotEmpty
    @Column(name = "account_type")
    private String account_type;

    @NotEmpty
    @Column(name = "account_status")
    private String account_status;

    @NotEmpty
    @Column(name = "currency")
    private String currency;

    @NotEmpty
    @Column(name = "created_at")
    private String created_at;

    @NotEmpty
    @Column(name = "updated_at")
    private String updated_at;

    public Account() {
    }

    public Account(long customer_id, BigDecimal balance, String account_type, String account_status, String currency, String created_at, String updated_at) {
        this.customer_id = customer_id;
        this.balance = balance;
        this.account_type = account_type;
        this.account_status = account_status;
        this.currency = currency;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public long getAccount_id() {
        return id;
    }

    public long getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(long customer_id) {
        this.customer_id = customer_id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getAccount_type() {
        return account_type;
    }

    public void setAccount_type(String account_type) {
        this.account_type = account_type;
    }

    public String getAccount_status() {
        return account_status;
    }

    public void setAccount_status(String account_status) {
        this.account_status = account_status;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

}
