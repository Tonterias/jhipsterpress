package com.jhipsterpress.web.repository;

import com.jhipsterpress.web.domain.Cactivity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Cactivity entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CactivityRepository extends JpaRepository<Cactivity, Long>, JpaSpecificationExecutor<Cactivity> {

    @Query(value = "select distinct cactivity from Cactivity cactivity left join fetch cactivity.communities",
        countQuery = "select count(distinct cactivity) from Cactivity cactivity")
    Page<Cactivity> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct cactivity from Cactivity cactivity left join fetch cactivity.communities")
    List<Cactivity> findAllWithEagerRelationships();

    @Query("select cactivity from Cactivity cactivity left join fetch cactivity.communities where cactivity.id =:id")
    Optional<Cactivity> findOneWithEagerRelationships(@Param("id") Long id);

}
