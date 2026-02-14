package com.japan_go_be.features.auth.entity;

import com.japan_go_be.common.persistence.BaseEntity;
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
