package com.banking.transaction_service.entity;

/**
 * Transaction Lifecycle flow:
 * PENDING->PROCESSING->COMPLETED(clean transaction)
 *                    ->PENDING-VERIFICATION(suspicious detected)
 *                    ->COMPLETED(verified)
 *                    ->FLAGGED(SAGA refund)
 *                 ->FAILED
 *                 ->FLAGGED
 */
public enum TransactionStatus {
    PENDING,
    PROCESSING,
    PENDING_VERIFICATION,
    COMPLETED,
    FAILED,
    FLAGGED
}
