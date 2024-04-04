package entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Operation {

    private Operation operationType;
    private BigDecimal amount;
    private LocalDateTime timestamp;

    public Operation(Operation operationType, BigDecimal amount, LocalDateTime timestamp) {
        this.operationType = operationType;
        this.amount = amount;
        this.timestamp = timestamp;
    }

    public Operation getOperationType() {
        return operationType;
    }

    public void setOperationType(Operation operationType) {
        this.operationType = operationType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
