package com.jhipsterpress.web.repository;

import com.jhipsterpress.web.domain.Follow;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the Follow entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FollowRepository extends JpaRepository<Follow, Long>, JpaSpecificationExecutor<Follow> {

    @Query("select follow from Follow follow where follow.followed.login = ?#{principal.username}")
    List<Follow> findByFollowedIsCurrentUser();

    @Query("select follow from Follow follow where follow.following.login = ?#{principal.username}")
    List<Follow> findByFollowingIsCurrentUser();

}
