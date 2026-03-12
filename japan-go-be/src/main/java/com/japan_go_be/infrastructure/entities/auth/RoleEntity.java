package com.japan_go_be.infrastructure.entities.auth;

import com.japan_go_be.contract.enums.auth.RoleNameEnum;
import com.japan_go_be.infrastructure.entities.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleEntity extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "role_name")
    RoleNameEnum roleName;

    @OneToMany(mappedBy = "role")
    List<UserEntity> users;

    @ManyToMany
    @JoinTable(name = "role_permission",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id"))
    List<PermissionEntity> permissions;
}
