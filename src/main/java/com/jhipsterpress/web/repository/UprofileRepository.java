package com.jhipsterpress.web.repository;

import com.jhipsterpress.web.domain.Uprofile;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Uprofile entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UprofileRepository extends JpaRepository<Uprofile, Long>, JpaSpecificationExecutor<Uprofile> {

}
