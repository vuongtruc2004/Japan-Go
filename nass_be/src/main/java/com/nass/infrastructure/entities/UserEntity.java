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
@Table(name = "user")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserEntity extends BaseEntity<Integer> {
    String email;
    String password;

    @Column(name = "refresh_token")
    String refreshToken;

    @Column(name = "first_name")
    String firstName;

    @Column(name = "last_name")
    String lastName;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_type")
    AccountTypeEnum accountType;

    @OneToOne
    @JoinColumn(name = "avatar_image_id", unique = true)
    ImageEntity avatar;

    @ManyToOne
    @JoinColumn(name = "role_id")
    RoleEntity role;
}
