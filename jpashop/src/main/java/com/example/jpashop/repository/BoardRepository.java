package com.example.jpashop.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.example.jpashop.entity.Board;

public interface BoardRepository extends PagingAndSortingRepository<Board, Integer> {
   @Query(value = "select * from boarb b join meber m on b.userid=m.userid where m.name like %:keyword% or b.title like %:keyword% or b.contents like %:keyword%", nativeQuery =true)
   Page<Board> findByAll(@Param("keyword") String keyword, Pageable pageable);
   
   @Query(value = "select * from board b join member m on b.userid=m.userid where m.name like %:keyword% ", nativeQuery = true)
   Page<Board> findByKeyword(@Param("keyword") String keyword, Pageable pageable);
   
   Page<Board> findByTitleContaining(String title, Pageable pageable);
   
   Page<Board> findByContentsContaining(String content, Pageable pageable);
   
   Board save(Board b);
   
   Optional<Board> findById(int idx);
   
   void deleteById(int idx);


}