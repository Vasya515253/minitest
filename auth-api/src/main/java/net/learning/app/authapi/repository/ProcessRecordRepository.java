package net.learning.app.authapi.repository;

import lombok.RequiredArgsConstructor;
import net.learning.app.authapi.entity.ProcessingLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProcessRecordRepository extends JpaRepository<ProcessingLog, UUID> {
}
