package cz.kb.openbanking.adaa.example.springboot.web.model;

import java.time.LocalDate;
import javax.annotation.Nullable;

/**
 * Represents account statement.
 *
 * @author <a href="mailto:aleh_kuchynski@kb.cz">Aleh Kuchynski</a>
 * @since 1.1
 */
public class StatementModel {
    /**
     * Statement issue date.
     */
    private LocalDate issued;
    /**
     * Statement sequence number (can be 0 for one-time statements).
     */
    private Integer sequenceNumber;
    /**
     * Statement total page count.
     */
    private Integer pagesCount;
    /**
     * Unique ID of the PDF statement.
     */
    private Long statementId;
    /**
     * Archived statement flag (regular statements = false; archived = true).
     */
    private Boolean archive;

    /**
     * Gets statement issue date.
     *
     * @return statement issue date
     */
    @Nullable
    public LocalDate getIssued() {
        return issued;
    }

    /**
     * Sets statement issue date.
     *
     * @param issued statement issue date
     */
    public void setIssued(LocalDate issued) {
        if (issued == null) {
            throw new IllegalArgumentException("issued must not be null");
        }

        this.issued = issued;
    }

    /**
     * Gets statement sequence number.
     *
     * @return statement sequence number
     */
    @Nullable
    public Integer getSequenceNumber() {
        return sequenceNumber;
    }

    /**
     * Sets statement sequence number.
     *
     * @param sequenceNumber statement sequence number
     */
    public void setSequenceNumber(Integer sequenceNumber) {
        if (sequenceNumber == null) {
            throw new IllegalArgumentException("sequenceNumber must not be null");
        }

        this.sequenceNumber = sequenceNumber;
    }

    /**
     * Gets statement total page count.
     *
     * @return statement total page count
     */
    @Nullable
    public Integer getPagesCount() {
        return pagesCount;
    }

    /**
     * Sets statement total page count.
     *
     * @param pagesCount statement total page count
     */
    public void setPagesCount(Integer pagesCount) {
        if (pagesCount == null) {
            throw new IllegalArgumentException("pagesCount must not be null");
        }

        this.pagesCount = pagesCount;
    }

    /**
     * Gets unique ID of the PDF statement.
     *
     * @return unique ID of the PDF statement
     */
    @Nullable
    public Long getStatementId() {
        return statementId;
    }

    /**
     * Sets unique ID of the PDF statement.
     *
     * @param statementId unique ID of the PDF statement
     */
    public void setStatementId(Long statementId) {
        if (statementId == null) {
            throw new IllegalArgumentException("statementId must not be null");
        }

        this.statementId = statementId;
    }

    /**
     * Gets archived statement flag.
     *
     * @return archived statement flag
     */
    @Nullable
    public Boolean getArchive() {
        return archive;
    }

    /**
     * Sets archived statement flag.
     *
     * @param archive archived statement flag
     */
    public void setArchive(Boolean archive) {
        if (archive == null) {
            throw new IllegalArgumentException("archive must not be null");
        }

        this.archive = archive;
    }
}
