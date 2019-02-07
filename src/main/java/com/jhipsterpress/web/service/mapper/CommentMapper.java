package com.jhipsterpress.web.service.mapper;

import com.jhipsterpress.web.domain.*;
import com.jhipsterpress.web.service.dto.CommentDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Comment and its DTO CommentDTO.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class, PostMapper.class, UprofileMapper.class})
public interface CommentMapper extends EntityMapper<CommentDTO, Comment> {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.firstName", target = "commenterFirstName")
    @Mapping(source = "user.lastName", target = "commenterLastName")
    @Mapping(source = "user.uprofile.id", target = "uprofileId")
    @Mapping(source = "user.uprofile.image", target = "commenterImage")
    @Mapping(source = "user.uprofile.imageContentType", target = "commenterImageContentType")
    @Mapping(source = "post.id", target = "postId")
    CommentDTO toDto(Comment comment);

    @Mapping(source = "userId", target = "user")
    @Mapping(source = "postId", target = "post")
    Comment toEntity(CommentDTO commentDTO);

    default Comment fromId(Long id) {
        if (id == null) {
            return null;
        }
        Comment comment = new Comment();
        comment.setId(id);
        return comment;
    }
}
