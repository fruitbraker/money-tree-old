/*
 * This file is generated by jOOQ.
 */
package generated.com.moneytree.persist.db.generated.tables.pojos;


import java.io.Serializable;

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
public class IncomeCategory implements Serializable {

    private static final long serialVersionUID = 306244817;

    private String incomeCategoryId;

    public IncomeCategory() {}

    public IncomeCategory(IncomeCategory value) {
        this.incomeCategoryId = value.incomeCategoryId;
    }

    public IncomeCategory(
        String incomeCategoryId
    ) {
        this.incomeCategoryId = incomeCategoryId;
    }

    public String getIncomeCategoryId() {
        return this.incomeCategoryId;
    }

    public IncomeCategory setIncomeCategoryId(String incomeCategoryId) {
        this.incomeCategoryId = incomeCategoryId;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("IncomeCategory (");

        sb.append(incomeCategoryId);

        sb.append(")");
        return sb.toString();
    }
}
