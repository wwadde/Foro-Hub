package com.api.forohub.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicoRepository extends JpaRepository<TopicoEntity,Long> {

    @Query("""
            select t from TopicoEntity t
            where t.status=true
            """)
    Page<TopicoEntity> listarTopicosActivos(Pageable pageable);
}
