package com.jhipsterpress.web.repository;

import com.jhipsterpress.web.domain.Blockuser;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the Blockuser entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BlockuserRepository extends JpaRepository<Blockuser, Long>, JpaSpecificationExecutor<Blockuser> {

    @Query("select blockuser from Blockuser blockuser where blockuser.blockeduser.login = ?#{principal.username}")
    List<Blockuser> findByBlockeduserIsCurrentUser();

    @Query("select blockuser from Blockuser blockuser where blockuser.blockinguser.login = ?#{principal.username}")
    List<Blockuser> findByBlockinguserIsCurrentUser();

}
