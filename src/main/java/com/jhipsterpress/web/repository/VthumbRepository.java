package com.jhipsterpress.web.repository;

import com.jhipsterpress.web.domain.Vthumb;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the Vthumb entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VthumbRepository extends JpaRepository<Vthumb, Long>, JpaSpecificationExecutor<Vthumb> {

    @Query("select vthumb from Vthumb vthumb where vthumb.user.login = ?#{principal.username}")
    List<Vthumb> findByUserIsCurrentUser();

}
