package com.jhipsterpress.web.repository;

import com.jhipsterpress.web.domain.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Topic entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TopicRepository extends JpaRepository<Topic, Long>, JpaSpecificationExecutor<Topic> {

    @Query(value = "select distinct topic from Topic topic left join fetch topic.posts",
        countQuery = "select count(distinct topic) from Topic topic")
    Page<Topic> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct topic from Topic topic left join fetch topic.posts")
    List<Topic> findAllWithEagerRelationships();

    @Query("select topic from Topic topic left join fetch topic.posts where topic.id =:id")
    Optional<Topic> findOneWithEagerRelationships(@Param("id") Long id);

}
