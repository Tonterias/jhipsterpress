package com.jhipsterpress.web.repository;

import com.jhipsterpress.web.domain.Vtopic;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the Vtopic entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VtopicRepository extends JpaRepository<Vtopic, Long>, JpaSpecificationExecutor<Vtopic> {

    @Query("select vtopic from Vtopic vtopic where vtopic.user.login = ?#{principal.username}")
    List<Vtopic> findByUserIsCurrentUser();

}
