/*
 * This file is generated by jOOQ.
 */
package generated.com.moneytree.persist.db.generated.tables.pojos;


import java.io.Serializable;
import java.sql.Timestamp;

import javax.annotation.Generated;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "https://www.jooq.org",
        "jOOQ version:3.13.0"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Expense implements Serializable {

    private static final long serialVersionUID = -1931105149;

    private Long      expenseId;
    private Timestamp transactionDate;
    private Object    transactionAmount;
    private Long      vendorId;
    private String    expenseCategory;
    private Long      metadata;
    private Boolean   hide;

    public Expense() {}

    public Expense(Expense value) {
        this.expenseId = value.expenseId;
        this.transactionDate = value.transactionDate;
        this.transactionAmount = value.transactionAmount;
        this.vendorId = value.vendorId;
        this.expenseCategory = value.expenseCategory;
        this.metadata = value.metadata;
        this.hide = value.hide;
    }

    public Expense(
        Long      expenseId,
        Timestamp transactionDate,
        Object    transactionAmount,
        Long      vendorId,
        String    expenseCategory,
        Long      metadata,
        Boolean   hide
    ) {
        this.expenseId = expenseId;
        this.transactionDate = transactionDate;
        this.transactionAmount = transactionAmount;
        this.vendorId = vendorId;
        this.expenseCategory = expenseCategory;
        this.metadata = metadata;
        this.hide = hide;
    }

    public Long getExpenseId() {
        return this.expenseId;
    }

    public Expense setExpenseId(Long expenseId) {
        this.expenseId = expenseId;
        return this;
    }

    public Timestamp getTransactionDate() {
        return this.transactionDate;
    }

    public Expense setTransactionDate(Timestamp transactionDate) {
        this.transactionDate = transactionDate;
        return this;
    }


    /**
     * @deprecated Unknown data type. Please define an explicit {@link org.jooq.Binding} to specify how this type should be handled. Deprecation can be turned off using {@literal <deprecationOnUnknownTypes/>} in your code generator configuration.
     */
    @java.lang.Deprecated
    public Object getTransactionAmount() {
        return this.transactionAmount;
    }


    /**
     * @deprecated Unknown data type. Please define an explicit {@link org.jooq.Binding} to specify how this type should be handled. Deprecation can be turned off using {@literal <deprecationOnUnknownTypes/>} in your code generator configuration.
     */
    @java.lang.Deprecated
    public Expense setTransactionAmount(Object transactionAmount) {
        this.transactionAmount = transactionAmount;
        return this;
    }

    public Long getVendorId() {
        return this.vendorId;
    }

    public Expense setVendorId(Long vendorId) {
        this.vendorId = vendorId;
        return this;
    }

    public String getExpenseCategory() {
        return this.expenseCategory;
    }

    public Expense setExpenseCategory(String expenseCategory) {
        this.expenseCategory = expenseCategory;
        return this;
    }

    public Long getMetadata() {
        return this.metadata;
    }

    public Expense setMetadata(Long metadata) {
        this.metadata = metadata;
        return this;
    }

    public Boolean getHide() {
        return this.hide;
    }

    public Expense setHide(Boolean hide) {
        this.hide = hide;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Expense (");

        sb.append(expenseId);
        sb.append(", ").append(transactionDate);
        sb.append(", ").append(transactionAmount);
        sb.append(", ").append(vendorId);
        sb.append(", ").append(expenseCategory);
        sb.append(", ").append(metadata);
        sb.append(", ").append(hide);

        sb.append(")");
        return sb.toString();
    }
}