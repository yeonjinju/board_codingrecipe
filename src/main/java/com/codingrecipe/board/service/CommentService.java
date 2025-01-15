package com.codingrecipe.board.service;

import com.codingrecipe.board.dto.CommentDTO;
import com.codingrecipe.board.entity.BoardEntity;
import com.codingrecipe.board.entity.CommentEntity;
import com.codingrecipe.board.repository.BoardRepository;
import com.codingrecipe.board.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    public Long save(CommentDTO commentDTO) {
        /* 부모 엔티티(BoardEntity)조회 */
        Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(commentDTO.getBoardId());
        if (optionalBoardEntity.isPresent()) {
            /*  클래스 메서드로 사용하는 이유는 엔티티클래스는 철저하게 보호하자라는 취지가 있음
            커멘드엔티티는 어떠한 생성자도외부에서 생성하지말자라는게 있음.
            엔티티를 보호하자라는 취지로 클래스메서드만 하나 따로 만드는것. */
            BoardEntity boardEntity = optionalBoardEntity.get();
            CommentEntity commentEntity = CommentEntity.toSaveEntity(commentDTO, boardEntity);
            return commentRepository.save(commentEntity).getId();
        } else {
            return null;
        }
    }
    public List<CommentDTO> findAll(Long boardId) {
        // select*from comment_table where board_id=? order by id desc;
        BoardEntity boardEntity = boardRepository.findById(boardId).get(); // 부모엔티티 조회
        List<CommentEntity> commentEntityList = commentRepository.findAllByBoardEntityOrderByIdDesc(boardEntity);
        /* EntityList -> DTOList */
        List<CommentDTO> commentDTOList = new ArrayList<>();
        for (CommentEntity commentEntity : commentEntityList) { // 엔티티리스트를 디티오로 변환하기위해 for문을 돌림
            CommentDTO commentDTO = CommentDTO.toCommentDTO(commentEntity, boardId);
            commentDTOList.add(commentDTO);
        }
        return commentDTOList;
    }
}