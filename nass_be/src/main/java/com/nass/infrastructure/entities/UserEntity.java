package com.nass.infrastructure.entities;

import com.nass.contract.enums.AccountTypeEnum;
import com.nass.infrastructure.entities.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserEntity extends BaseEntity<Integer> {
    String email;
    String password;
    String refreshToken;
    String firstName;
    String lastName;

    @Enumerated(EnumType.STRING)
    AccountTypeEnum accountType;

    @OneToOne
    @JoinColumn(name = "avatar_image_id", unique = true)
    ImageEntity avatar;

    @ManyToOne
    @JoinColumn(name = "role_id")
    RoleEntity role;
}
