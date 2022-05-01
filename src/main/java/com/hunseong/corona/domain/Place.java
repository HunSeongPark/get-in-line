package com.hunseong.corona.domain;

import com.hunseong.corona.constant.PlaceType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
public class Place extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "place_id")
    private Long id;

    @Column(nullable = false, columnDefinition = "varchar(20) default 'COMMON'")
    @Enumerated(EnumType.STRING)
    private PlaceType placeType;

    @Column(nullable = false)
    private String placeName;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false, columnDefinition = "integer default 0")
    private Integer capacity;

    private String memo;

    @ToString.Exclude
    @OrderBy("id")
    @OneToMany(mappedBy = "place")
    private final Set<Event> events = new LinkedHashSet<>();

    @ToString.Exclude
    @OrderBy("id")
    @OneToMany(mappedBy = "place", cascade = CascadeType.REMOVE)
    private final Set<AdminPlaceMap> adminPlaceMaps = new LinkedHashSet<>();

    protected Place(PlaceType placeType, String placeName, String address,
                    String phoneNumber, Integer capacity, String memo) {
        this.placeType = placeType;
        this.placeName = placeName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.capacity = capacity;
        this.memo = memo;
    }

    public static Place of(
            PlaceType placeType, String placeName, String address,
            String phoneNumber, Integer capacity, String memo
    ) {
        return new Place(placeType, placeName, address, phoneNumber, capacity, memo);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Place)) return false;
        Place place = (Place) o;
        return Objects.equals(placeName, place.placeName) && Objects.equals(address, place.address) && Objects.equals(phoneNumber, place.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(placeName, address, phoneNumber);
    }
}
