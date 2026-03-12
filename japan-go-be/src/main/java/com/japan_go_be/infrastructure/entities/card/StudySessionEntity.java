package com.japan_go_be.infrastructure.entities.card;

import com.japan_go_be.contract.enums.StudySessionStatusEnum;
import com.japan_go_be.infrastructure.entities.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "study_session")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudySessionEntity extends BaseEntity {
    @One
    @JoinColumn(name = "deck_id")
    DeckEntity deck;

    @Enumerated(EnumType.STRING)
    @Column(name = "study_session_status")
    StudySessionStatusEnum studySessionStatus;

    @Column(name = "is_shuffled")
    Boolean isShuffled;

    @OneToOne
    @JoinColumn(name = "current_session_card_id")
    CardEntity currentSessionCard;

    @Column(name = "current_round")
    Integer currentRound;

    @Column(name = "started_time")
    Instant startedTime;

    @Column(name = "completed_time")
    Instant completedTime;
}
