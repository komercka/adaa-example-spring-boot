package cz.kb.openbanking.adaa.example.springboot.web.model;

import java.time.OffsetDateTime;
import javax.annotation.Nullable;

/**
 * Account balance for specific balance type.
 *
 * @author <a href="mailto:aleh_kuchynski@kb.cz">Aleh Kuchynski</a>
 * @since 1.0
 */
public class AccountBalanceModel {
    /**
     * Indicates the type of balance.
     */
    private AccountBalanceModel.TypeEnum type;
    /**
     * Indicates whether the account balance is positive or negative.
     */
    private CreditDebitIndicatorModel creditDebitIndicator;
    /**
     * Balance amount information.
     */
    private CurrencyAmountModel amount;
    /**
     * The balance information is valid for in ISODateTime format ('YYYY-MM-DD'/'YYYY-MM-DDThh:mm:ss.sTZD').
     */
    private OffsetDateTime validAt;
    /**
     * Credit line information (allowed debit).
     */
    private CurrencyAmountModel creditLine;

    /**
     * Gets type of the account balance.
     *
     * @return type of the account balance
     */
    @Nullable
    public AccountBalanceModel.TypeEnum getType() {
        return type;
    }

    /**
     * Sets type of the account balance.
     *
     * @param type type of the account balance
     */
    public void setType(AccountBalanceModel.TypeEnum type) {
        this.type = type;
    }

    /**
     * Gets indicator whether the account balance is positive or negative.
     *
     * @return indicator whether the account balance is positive or negative
     */
    @Nullable
    public CreditDebitIndicatorModel getCreditDebitIndicator() {
        return creditDebitIndicator;
    }

    /**
     * Sets indicator whether the account balance is positive or negative.
     *
     * @param creditDebitIndicator indicator whether the account balance is positive or negative
     */
    public void setCreditDebitIndicator(CreditDebitIndicatorModel creditDebitIndicator) {
        this.creditDebitIndicator = creditDebitIndicator;
    }

    /**
     * Gets balance amount information.
     *
     * @return balance amount information
     */
    @Nullable
    public CurrencyAmountModel getAmount() {
        return amount;
    }

    /**
     * Sets balance amount information.
     *
     * @param amount balance amount
     */
    public void setAmount(CurrencyAmountModel amount) {
        this.amount = amount;
    }

    /**
     * Gets what balance information is valid for in ISODateTime format ('YYYY-MM-DD'/'YYYY-MM-DDThh:mm:ss.sTZD').
     *
     * @return information what balance information is valid for
     */
    @Nullable
    public OffsetDateTime getValidAt() {
        return validAt;
    }

    /**
     * Sets information what balance information is valid for
     * in ISODateTime format ('YYYY-MM-DD'/'YYYY-MM-DDThh:mm:ss.sTZD').
     *
     * @param validAt information what balance information is valid for
     */
    public void setValidAt(OffsetDateTime validAt) {
        this.validAt = validAt;
    }

    /**
     * Gets credit line information (allowed debit).
     *
     * @return credit line information
     */
    @Nullable
    public CurrencyAmountModel getCreditLine() {
        return creditLine;
    }

    /**
     * Sets credit line information (allowed debit).
     *
     * @param creditLine credit line information
     */
    public void setCreditLine(CurrencyAmountModel creditLine) {
        this.creditLine = creditLine;
    }

    /**
     * Type of the account balance.
     */
    public enum TypeEnum {
        PREVIOUSLY_CLOSED_BOOK("PREVIOUSLY_CLOSED_BOOK"),
        CLOSING_BOOKED("CLOSING_BOOKED"),
        CLOSING_AVAILABLE("CLOSING_AVAILABLE");

        /**
         * Code of account balance type.
         */
        private String value;

        /**
         * New instance.
         *
         * @param value code of account balance type
         */
        TypeEnum(String value) {
            this.value = value;
        }

        /**
         * Gets code of account balance type.
         *
         * @return code
         */
        public String getValue() {
            return value;
        }
    }
}
