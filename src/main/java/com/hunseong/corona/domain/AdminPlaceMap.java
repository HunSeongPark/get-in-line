package com.hunseong.corona.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;

/**
 * Created by Hunseong on 2022/05/02
 */
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class AdminPlaceMap extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id")
    private Admin admin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id")
    private Place place;

    protected AdminPlaceMap(Admin admin, Place place) {
        this.admin = admin;
        this.place = place;
    }

    public static AdminPlaceMap of(Admin admin, Place place) {
        return new AdminPlaceMap(admin, place);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AdminPlaceMap)) return false;
        AdminPlaceMap that = (AdminPlaceMap) o;
        return Objects.equals(admin, that.admin) && Objects.equals(place, that.place);
    }

    @Override
    public int hashCode() {
        return Objects.hash(admin, place);
    }
}
