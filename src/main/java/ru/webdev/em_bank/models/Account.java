//package ru.webdev.em_bank.models;
//
//import jakarta.persistence.*;
//import jakarta.validation.constraints.NotEmpty;
//import java.math.BigDecimal;
//import java.time.LocalDateTime;
//import java.util.List;
//
//@Entity
//@Table(name = "Account")
//public class Account {
//
//    @Id
//    @Column(name = "id")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long id;
//
//    @OneToOne
//    @JoinColumn(name = "id")
//    private Customer customer;
//
//    @ManyToMany(mappedBy = "accounts")
//    private List<Transaction> transactions;
//
//
//    @NotEmpty
//    @Column(name = "id")
////    @Column(insertable=false, updatable=false)
//    private long id;
//
//    @NotEmpty
//    @Column(name = "balance")
//    private BigDecimal balance;
//
//    @NotEmpty
//    @Column(name = "accountType")
//    private String accountType;
//
//    @NotEmpty
//    @Column(name = "accountStatus")
//    private String accountStatus;
//
//    @NotEmpty
//    @Column(name = "currency")
//    private String currency;
//
//    @NotEmpty
//    @Column(name = "createdAt")
//    private LocalDateTime createdAt;
//
//    @NotEmpty
//    @Column(name = "updatedAt")
//    private LocalDateTime updatedAt;
//
//    public Account() {
//    }
//
//    public Account(long id, BigDecimal balance, String accountType, String accountStatus, String currency, LocalDateTime createdAt, LocalDateTime updatedAt) {
//        this.id = id;
//        this.balance = balance;
//        this.accountType = accountType;
//        this.accountStatus = accountStatus;
//        this.currency = currency;
//        this.createdAt = LocalDateTime.now();
//        this.updatedAt = LocalDateTime.now();
//    }
//
//    public long getAccountId() {
//        return id;
//    }
//
//    public long getCustomerId() {
//        return id;
//    }
//
//    public void setCustomerId(long id) {
//        this.id = id;
//    }
//
//    public BigDecimal getBalance() {
//        return balance;
//    }
//
//    public void setBalance(BigDecimal balance) {
//        this.balance = balance;
//    }
//
//    public String getAccountType() {
//        return accountType;
//    }
//
//    public void setAccountType(String accountType) {
//        this.accountType = accountType;
//    }
//
//    public String getAccountStatus() {
//        return accountStatus;
//    }
//
//    public void setAccountStatus(String accountStatus) {
//        this.accountStatus = accountStatus;
//    }
//
//    public String getCurrency() {
//        return currency;
//    }
//
//    public void setCurrency(String currency) {
//        this.currency = currency;
//    }
//
//    public LocalDateTime getCreatedAt() {
//        return createdAt;
//    }
//
//    public void setCreatedAt(LocalDateTime createdAt) {
//        this.createdAt = createdAt;
//    }
//
//    public LocalDateTime getUpdatedAt() {
//        return updatedAt;
//    }
//
//    public void setUpdatedAt(LocalDateTime updatedAt) {
//        this.updatedAt = updatedAt;
//    }
//
//    public void setCustomer(Customer customer) {
//        this.customer = customer;
//    }
//
//    public Customer getCustomer() {
//        return customer;
//    }
//
//}

package ru.webdev.em_bank.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Account")
public class Account {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToMany(mappedBy = "accounts")
    private List<Transaction> transactions;

    @Column(name = "customer_id_fk")
    private int customerId;

    @Column(name = "balance")
    private BigDecimal balance;

    @Column(name = "account_type")
    private String accountType;

    @Column(name = "account_status")
    private String accountStatus;

    @Column(name = "currency")
    private String currency;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Account() {
    }

    public Account(int customerId, BigDecimal balance, String accountType, String accountStatus, String currency, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.customerId = customerId;
        this.balance = balance;
        this.accountType = accountType;
        this.accountStatus = accountStatus;
        this.currency = currency;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
