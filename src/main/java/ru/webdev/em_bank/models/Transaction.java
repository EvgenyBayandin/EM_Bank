package ru.webdev.em_bank.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "Transaction")
public class Transaction {
    @Id
    @Column(name = "transaction_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToMany
    @JoinTable(
            name = "transaction_account",
            joinColumns = @JoinColumn(name = "transaction_id"),
            inverseJoinColumns = @JoinColumn(name = "account_id")
    )
    private List<Account> accounts;

    @NotEmpty
    @Column(name = "transaction_type")
    private String transaction_type;

    @NotEmpty
    @Column(name = "amount")
    private BigDecimal amount;

    @NotEmpty
    @Column(name = "created_at")
    private String created_at;

    public Transaction() {
    }

    public Transaction(List<Account> accounts, String transaction_type, BigDecimal amount, String created_at) {
        this.accounts = accounts;
        this.transaction_type = transaction_type;
        this.amount = amount;
        this.created_at = created_at;
    }

    public long getId() {
        return id;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public String getTransaction_type() {
        return transaction_type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public void setTransaction_type(String transaction_type) {
        this.transaction_type = transaction_type;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
