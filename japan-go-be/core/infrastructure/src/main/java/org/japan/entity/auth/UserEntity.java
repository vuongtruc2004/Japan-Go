package org.japan.entity.auth;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.japan.entity.base.BaseEntity;
import org.japan.entity.common.ImageEntity;
import org.japan.enums.auth.AccountTypeEnum;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserEntity extends BaseEntity {
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
