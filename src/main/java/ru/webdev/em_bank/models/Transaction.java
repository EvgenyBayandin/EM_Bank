package ru.webdev.em_bank.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Transaction")
public class Transaction {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToMany
    @JoinTable(
            name = "transaction_account",
            joinColumns = @JoinColumn(name = "transactionId"),
            inverseJoinColumns = @JoinColumn(name = "accountId")
    )
    private List<Account> accounts;

    @NotEmpty
    @Column(name = "transaction_type")
    private String transactionType;

    @NotEmpty
    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public Transaction() {
    }

    public Transaction(List<Account> accounts, String transactionType, BigDecimal amount) {
        this.accounts = accounts;
        this.transactionType = transactionType;
        this.amount = amount;
        this.createdAt = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
