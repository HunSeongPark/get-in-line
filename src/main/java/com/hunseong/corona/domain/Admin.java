package com.hunseong.corona.domain;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Created by Hunseong on 2022/05/02
 */
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Admin extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String nickname;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String phoneNumber;

    private String memo;

    @ToString.Exclude
    @OrderBy("id")
    @OneToMany(mappedBy = "admin")
    private final Set<AdminPlaceMap> adminPlaceMaps = new LinkedHashSet<>();

    protected Admin(String email, String nickname, String password, String phoneNumber, String memo) {
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.memo = memo;
    }

    public static Admin of(String email, String nickname, String password, String phoneNumber, String memo) {
        return new Admin(email, nickname, password, phoneNumber, memo);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Admin)) return false;
        Admin admin = (Admin) o;
        return Objects.equals(email, admin.email) && Objects.equals(nickname, admin.nickname) && Objects.equals(phoneNumber, admin.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, nickname, phoneNumber);
    }
}
