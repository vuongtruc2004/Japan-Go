package com.japan_go_be.infrastructure.entities.card;

import com.japan_go_be.contract.enums.StudySessionStatusEnum;
import com.japan_go_be.infrastructure.entities.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "study_sessions")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudySessionEntity extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "deck_id")
    DeckEntity deck;

    @Enumerated(EnumType.STRING)
    @Column(name = "study_session_status")
    StudySessionStatusEnum studySessionStatus;

    @Column(name = "is_shuffled")
    Boolean isShuffled;

    @Column(name = "current_round")
    Integer currentRound;

    @Column(name = "current_index")
    Integer currentIndex;

    @Column(name = "started_time")
    Instant startedTime;

    @Column(name = "completed_time")
    Instant completedTime;

    @Builder.Default
    @OneToMany(mappedBy = "studySession", cascade = CascadeType.ALL, orphanRemoval = true)
    List<StudySessionCardEntity> studySessionCards = new ArrayList<>();
}
