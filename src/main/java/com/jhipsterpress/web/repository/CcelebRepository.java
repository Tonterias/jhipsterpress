package com.jhipsterpress.web.repository;

import com.jhipsterpress.web.domain.Cceleb;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Cceleb entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CcelebRepository extends JpaRepository<Cceleb, Long>, JpaSpecificationExecutor<Cceleb> {

    @Query(value = "select distinct cceleb from Cceleb cceleb left join fetch cceleb.communities",
        countQuery = "select count(distinct cceleb) from Cceleb cceleb")
    Page<Cceleb> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct cceleb from Cceleb cceleb left join fetch cceleb.communities")
    List<Cceleb> findAllWithEagerRelationships();

    @Query("select cceleb from Cceleb cceleb left join fetch cceleb.communities where cceleb.id =:id")
    Optional<Cceleb> findOneWithEagerRelationships(@Param("id") Long id);

}
