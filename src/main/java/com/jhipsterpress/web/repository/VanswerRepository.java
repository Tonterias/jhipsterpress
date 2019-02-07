package com.jhipsterpress.web.repository;

import com.jhipsterpress.web.domain.Vanswer;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the Vanswer entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VanswerRepository extends JpaRepository<Vanswer, Long>, JpaSpecificationExecutor<Vanswer> {

    @Query("select vanswer from Vanswer vanswer where vanswer.user.login = ?#{principal.username}")
    List<Vanswer> findByUserIsCurrentUser();

}
