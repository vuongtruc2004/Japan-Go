package com.japan_go_be.infrastructure.entities.card;

import com.japan_go_be.contract.enums.StudySessionCardStatusEnum;
import com.japan_go_be.infrastructure.entities.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "study_session_cards")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudySessionCardEntity extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "study_session_id")
    StudySessionEntity studySession;

    @ManyToOne
    @JoinColumn(name = "card_id")
    CardEntity card;

    @Column(name = "order_index")
    Integer orderIndex;

    @Enumerated(EnumType.STRING)
    @Column(name = "study_session_card_status")
    StudySessionCardStatusEnum studySessionCardStatus;
}
