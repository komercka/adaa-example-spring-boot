package cz.kb.openbanking.adaa.example.springboot.web.model;

import javax.annotation.Nullable;
import java.time.LocalDate;
import java.time.OffsetDateTime;

/**
 * Transaction details.
 *
 * @author <a href="mailto:aleh_kuchynski@kb.cz">Aleh Kuchynski</a>
 * @since 1.0
 */
public class TransactionModel {
    private OffsetDateTime lastUpdated;
    private AccountTypeModel accountType;
    private String entryReference;
    private String iban;
    private CreditDebitIndicatorModel creditDebitIndicator;
    private TransactionTypeModel transactionType;
    private CurrencyAmountModel amount;
    private LocalDate bookingDate;
    private LocalDate valueDate;
    private CurrencyAmountModel instructed;
    private Boolean reversalIndicator;
    private String status;
    private TransactionCounterpartyModel counterParty;
    private TransactionReferencesModel references;
    private String additionalTransactionInformation;

    /**
     * Gets information about when transaction was last updated.
     *
     * @return information about when transaction was last updated
     */
    @Nullable
    public OffsetDateTime getLastUpdated() {
        return lastUpdated;
    }

    /**
     * Sets information about when transaction was last updated.
     *
     * @param lastUpdated information about when transaction was last updated
     */
    public void setLastUpdated(OffsetDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    /**
     * Gets type of bank account.
     *
     * @return type of bank account
     */
    @Nullable
    public AccountTypeModel getAccountType() {
        return accountType;
    }

    /**
     * Sets type of bank account.
     *
     * @param accountType type of bank account
     */
    public void setAccountType(AccountTypeModel accountType) {
        this.accountType = accountType;
    }

    /**
     * Gets entry reference.
     *
     * @return entry reference
     */
    @Nullable
    public String getEntryReference() {
        return entryReference;
    }

    /**
     * Sets entry reference.
     *
     * @param entryReference entry reference
     */
    public void setEntryReference(String entryReference) {
        this.entryReference = entryReference;
    }

    /**
     * Gets IBAN code.
     *
     * @return IBAN code
     */
    @Nullable
    public String getIban() {
        return iban;
    }

    /**
     * Sets IBAN code.
     *
     * @param iban IBAN code
     */
    public void setIban(String iban) {
        this.iban = iban;
    }

    /**
     * Gets indicator of transaction type: credit or debit.
     *
     * @return indicator of transaction type
     */
    @Nullable
    public CreditDebitIndicatorModel getCreditDebitIndicator() {
        return creditDebitIndicator;
    }

    /**
     * Sets indicator of transaction type: credit or debit.
     *
     * @param creditDebitIndicator indicator of transaction type: credit or debit
     */
    public void setCreditDebitIndicator(CreditDebitIndicatorModel creditDebitIndicator) {
        this.creditDebitIndicator = creditDebitIndicator;
    }

    /**
     * Gets transaction type.
     *
     * @return transaction type
     */
    @Nullable
    public TransactionTypeModel getTransactionType() {
        return transactionType;
    }

    /**
     * Sets transaction type.
     *
     * @param transactionType transaction type
     */
    public void setTransactionType(TransactionTypeModel transactionType) {
        this.transactionType = transactionType;
    }

    /**
     * Gets transaction amount.
     *
     * @return transaction amount
     */
    @Nullable
    public CurrencyAmountModel getAmount() {
        return amount;
    }

    /**
     * Sets transaction amount.
     *
     * @param amount transaction amount
     */
    public void setAmount(CurrencyAmountModel amount) {
        this.amount = amount;
    }

    /**
     * Gets transaction booking date.
     *
     * @return transaction booking date
     */
    @Nullable
    public LocalDate getBookingDate() {
        return bookingDate;
    }

    /**
     * Sets transaction booking date.
     *
     * @param bookingDate transaction booking date
     */
    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    /**
     * Gets transaction value date.
     *
     * @return transaction value date
     */
    @Nullable
    public LocalDate getValueDate() {
        return valueDate;
    }

    /**
     * Sets transaction value date.
     *
     * @param valueDate transaction value date
     */
    public void setValueDate(LocalDate valueDate) {
        this.valueDate = valueDate;
    }

    /**
     * Gets information about instructed amount.
     *
     * @return information about instructed amount
     */
    @Nullable
    public CurrencyAmountModel getInstructed() {
        return instructed;
    }

    /**
     * Sets information about instructed amount.
     *
     * @param instructed information about instructed amount
     */
    public void setInstructed(CurrencyAmountModel instructed) {
        this.instructed = instructed;
    }

    /**
     * Gets reversal indicator.
     *
     * @return reversal indicator
     */
    @Nullable
    public Boolean getReversalIndicator() {
        return reversalIndicator;
    }

    /**
     * Sets reversal indicator.
     *
     * @param reversalIndicator reversal indicator
     */
    public void setReversalIndicator(Boolean reversalIndicator) {
        this.reversalIndicator = reversalIndicator;
    }

    /**
     * Gets transaction status.
     *
     * @return transaction status
     */
    @Nullable
    public String getStatus() {
        return status;
    }

    /**
     * Sets transaction status.
     *
     * @param status transaction status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Gets information about counter party.
     *
     * @return information about counter party
     */
    @Nullable
    public TransactionCounterpartyModel getCounterParty() {
        return counterParty;
    }

    /**
     * Sets information about counter party.
     *
     * @param counterParty information about counter party
     */
    public void setCounterParty(TransactionCounterpartyModel counterParty) {
        this.counterParty = counterParty;
    }

    /**
     * Gets transaction references.
     *
     * @return transaction references
     */
    @Nullable
    public TransactionReferencesModel getReferences() {
        return references;
    }

    /**
     * Sets transaction references.
     *
     * @param references transaction references
     */
    public void setReferences(TransactionReferencesModel references) {
        this.references = references;
    }

    /**
     * Gets additional transaction information.
     *
     * @return additional transaction information
     */
    @Nullable
    public String getAdditionalTransactionInformation() {
        return additionalTransactionInformation;
    }

    /**
     * Sets additional transaction information.
     *
     * @param additionalTransactionInformation additional transaction information
     */
    public void setAdditionalTransactionInformation(String additionalTransactionInformation) {
        this.additionalTransactionInformation = additionalTransactionInformation;
    }
}
