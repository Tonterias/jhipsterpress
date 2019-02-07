package com.jhipsterpress.web.repository;

import com.jhipsterpress.web.domain.Vquestion;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the Vquestion entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VquestionRepository extends JpaRepository<Vquestion, Long>, JpaSpecificationExecutor<Vquestion> {

    @Query("select vquestion from Vquestion vquestion where vquestion.user.login = ?#{principal.username}")
    List<Vquestion> findByUserIsCurrentUser();

}
