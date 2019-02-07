package com.jhipsterpress.web.repository;

import com.jhipsterpress.web.domain.Celeb;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Celeb entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CelebRepository extends JpaRepository<Celeb, Long>, JpaSpecificationExecutor<Celeb> {

    @Query(value = "select distinct celeb from Celeb celeb left join fetch celeb.uprofiles",
        countQuery = "select count(distinct celeb) from Celeb celeb")
    Page<Celeb> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct celeb from Celeb celeb left join fetch celeb.uprofiles")
    List<Celeb> findAllWithEagerRelationships();

    @Query("select celeb from Celeb celeb left join fetch celeb.uprofiles where celeb.id =:id")
    Optional<Celeb> findOneWithEagerRelationships(@Param("id") Long id);

}
