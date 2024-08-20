package com.maids.maidstask.borrowings.repositories;

import com.maids.maidstask.borrowings.models.BorrowingRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BorrowingRecordRepository extends JpaRepository<BorrowingRecord, Integer> {
    Optional<BorrowingRecord> findFirstByPatronIdAndBookIdAndReturnDateIsNull(int bookId, int patronId);

}
