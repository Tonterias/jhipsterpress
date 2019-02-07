package com.jhipsterpress.web.repository;

import com.jhipsterpress.web.domain.Newsletter;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Newsletter entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NewsletterRepository extends JpaRepository<Newsletter, Long>, JpaSpecificationExecutor<Newsletter> {

}
