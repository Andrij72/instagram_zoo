package com.akul.instagramzoo.web;

import com.akul.instagramzoo.dto.CommentDTO;
import com.akul.instagramzoo.entity.Comment;
import com.akul.instagramzoo.facade.CommentFacade;
import com.akul.instagramzoo.payload.response.MessageResponse;
import com.akul.instagramzoo.services.CommentService;
import com.akul.instagramzoo.validations.ResponseErrorValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/comment")
@CrossOrigin
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private CommentFacade commentFacade;
    @Autowired
    private ResponseErrorValidation responseErrorValidation;

    @PostMapping("/{postId}/create")
    private ResponseEntity<Object> createComment(@Valid @RequestBody CommentDTO commentDTO,
                                                 @PathVariable("postId") String postId,
                                                 BindingResult bindingResult,
                                                 Principal principal) {
        ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(bindingResult);
        if (!ObjectUtils.isEmpty(errors)) return errors;

        Comment comment = commentService.saveComment(Long.parseLong(postId), commentDTO, principal);

        CommentDTO createdComment = commentFacade.commentToCommentDTO(comment);

        return new ResponseEntity<>(createdComment, HttpStatus.OK);
    }

    @GetMapping("/{postId}/all")
    public ResponseEntity<List<CommentDTO>> getAllComentsToPost(@PathVariable String postId) {
        List<CommentDTO> commentDTOlist = commentService.getAllCommentsForPost(Long.parseLong(postId))
                .stream()
                .map(commentFacade::commentToCommentDTO)
                .collect(Collectors.toList());

        return new ResponseEntity<>(commentDTOlist, HttpStatus.OK);
    }

    @PostMapping("/{commentId}/delete")
    public ResponseEntity<MessageResponse> deleteComment(@PathVariable("commentId") String commentId) {
        commentService.deleteComment(Long.parseLong(commentId));

        return new ResponseEntity(new MessageResponse("Comment was deleted for post"), HttpStatus.OK);
    }
}
