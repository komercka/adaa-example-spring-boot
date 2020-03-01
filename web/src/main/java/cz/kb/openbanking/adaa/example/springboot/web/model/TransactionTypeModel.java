package cz.kb.openbanking.adaa.example.springboot.web.model;

import org.springframework.util.Assert;

/**
 * Contains all possible transaction types.
 *
 * @author <a href="mailto:aleh_kuchynski@kb.cz">Aleh Kuchynski</a>
 * @since 1.0
 */
public enum TransactionTypeModel {
    INTEREST("INTEREST"),
    FEE("FEE"),
    DOMESTIC("DOMESTIC"),
    FOREIGN("FOREIGN"),
    SEPA("SEPA"),
    CASH("CASH"),
    CARD("CARD"),
    OTHER("OTHER");

    private String transactionType;

    /**
     * No instance.
     *
     * @param transactionType type of transaction
     */
    TransactionTypeModel(String transactionType) {
        Assert.hasText(transactionType, "transactionType must not be empty");

        this.transactionType = transactionType;
    }

    /**
     * Gets type of transaction.
     *
     * @return type of transaction
     */
    public String getTransactionType() {
        return transactionType;
    }
}
