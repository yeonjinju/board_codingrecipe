package com.codingrecipe.board.repository;

import com.codingrecipe.board.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

// 웹 개발에서 레포지토리 인터페이스는 데이터베이스와 애플리케이션 간의 상호작용을 단순화하고 구조화하는 데 중요한 역할을 합니다.


public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
    // update board_table set board_hits=board_hits+1 where id=?
    // 현재 가지고있는 조회수에서 1을 증가시켜서 조회수값으로 바꾼다 (조회수 1올리기) id=?조건절
    @Modifying
    @Query(value = "update BoardEntity b set b.boardHits=b.boardHits+1 where b.id=:id")
    void updateHits(@Param("id")Long id); //(id가 위 맨끝 아이디와 일치해야함)
}


