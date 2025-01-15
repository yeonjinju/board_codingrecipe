# 개발환경
1. IDE: IntelliJ IDEA Community
2. Spring Boot 3.4.1
3. JDK 11
4. mysql
5. Spring Data JPA
6. Thymeleaf

# 게시판 주요기능
1. 글쓰기(/board/save)
2. 글목록(/board/)
3. 글조회(/board/{id})
4. 글수정(/board/update/{id})
   - 상세화면에서 수정버튼 클릭
   - 서버에서 해당 게시글의 정보를 가지고 수정화면 출력
5. 글삭제(/board/delete/{id})
6. 페이징처리(/board/paging)
   - /board/paging?page=2
   - /board/paging/2
   - 게시글이 14개
     - 한페이지에 5개씩 글을볼때 필요한 페이징 수 -> 3개
     - 한페이지 3개식 -> 5개 페이지가 됨
7. 파일 첨부하기
   - 단일 파일 첨부
   - 다중 파일 첨부
   - 파일 첨부와 관련해 추가될 부분들
     - save.html
     - BoardService.save()
     - BoardEntity
     - BoardFileEntity, BoardFileRepository 푸가
     - detail.html
8. 댓글 처리하기
   - 글 상세 페이지에서 댓글 입력(ajax)
     - ajax 다뤄보기 재생목록
   - 상세조회할때 기존에 작성된 댓글목록이 보임
   - 댓글을 입력하면 기존댓글 목록에 새로 작성한 댓글추가
   - 댓글용 테이블 필요



    - board_table(부모) - board_file_table(자식)
```
create table board_table
(
id             bigint auto_increment primary key,
created_time   datetime     null,
updated_time   datetime     null,
board_contents varchar(500) null,
board_hits     int          null,
board_pass     varchar(255) null,
board_title    varchar(255) null,
board_writer   varchar(20)  not null,
file_attached  int          null
);

create table board_file_table
(
id                 bigint auto_increment primary key,
created_time       datetime     null,
updated_time       datetime     null,
original_file_name varchar(255) null,
stored_file_name   varchar(255) null,
board_id           bigint       null,
constraint FKcfxqly70ddd02xbou0jxgh4o3
    foreign key (board_id) references board_table (id) on delete cascade
);
레퍼런스를 통한 부모자식관계 생성 on delete (연쇄삭제)
```


## mysql DataBase 계정 생성 및 권한 부여
```
create database db_codingrecipe;
create user user_codingrecipe@localhost identified by '1234';
grant all privileges on db_codingrecipe.* to user_codingrecipe@localhost;
```