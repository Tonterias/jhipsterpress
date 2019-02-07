package com.jhipsterpress.web.repository;

import com.jhipsterpress.web.domain.Cinterest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Cinterest entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CinterestRepository extends JpaRepository<Cinterest, Long>, JpaSpecificationExecutor<Cinterest> {

    @Query(value = "select distinct cinterest from Cinterest cinterest left join fetch cinterest.communities",
        countQuery = "select count(distinct cinterest) from Cinterest cinterest")
    Page<Cinterest> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct cinterest from Cinterest cinterest left join fetch cinterest.communities")
    List<Cinterest> findAllWithEagerRelationships();

    @Query("select cinterest from Cinterest cinterest left join fetch cinterest.communities where cinterest.id =:id")
    Optional<Cinterest> findOneWithEagerRelationships(@Param("id") Long id);

}
