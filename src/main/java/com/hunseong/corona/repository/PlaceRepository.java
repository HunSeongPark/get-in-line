package com.hunseong.corona.repository;

import com.hunseong.corona.domain.Place;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by Hunseong on 2022/05/02
 */
public interface PlaceRepository extends JpaRepository<Place, Long> {

    @Override
    @EntityGraph(attributePaths = {"events"})
    Optional<Place> findById(Long aLong);
}
