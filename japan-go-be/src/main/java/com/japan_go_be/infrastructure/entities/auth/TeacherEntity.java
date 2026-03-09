package com.japan_go_be.infrastructure.entities.auth;

import com.japan_go_be.infrastructure.entities.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "teacher")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TeacherEntity extends BaseEntity {
    @OneToOne
    @JoinColumn(name = "user_id")
    UserEntity user;
}
