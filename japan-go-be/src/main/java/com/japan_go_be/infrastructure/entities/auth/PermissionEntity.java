package com.japan_go_be.infrastructure.entities.auth;

import com.japan_go_be.infrastructure.entities.base.BaseEntity;
import com.japan_go_be.contract.enums.auth.ApiMethodEnum;
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
@Table(name = "permission")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PermissionEntity extends BaseEntity {
    @Column(name = "api_path")
    String apiPath;

    @Enumerated(EnumType.STRING)
    @Column(name = "api_method")
    ApiMethodEnum apiMethod;

    @ManyToMany(mappedBy = "permissions")
    List<RoleEntity> roles;
}
