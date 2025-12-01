package com.solano.pos.backend.repository;

import com.solano.pos.backend.model.MotivoMerma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MotivoMermaRepository extends JpaRepository<MotivoMerma,Long> {
    List<MotivoMerma> findByNegocioId(Long negocioId);
}
