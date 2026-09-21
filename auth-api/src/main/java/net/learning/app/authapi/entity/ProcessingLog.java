package net.learning.app.authapi.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "processing_log")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProcessingLog {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column(columnDefinition = "TEXT")
    private String inputText;
    @Column(columnDefinition = "TEXT")
    private String outputText;
    private Instant createdAt;
}
