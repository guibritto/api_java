package br.com.fiap.apisecurity.repository;
import br.com.fiap.apisecurity.model.Patio;
import br.com.fiap.apisecurity.model.Vaga;
import br.com.fiap.apisecurity.model.enums.StatusVaga;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface VagaRepository extends JpaRepository<Vaga, UUID> {

    boolean existsByPatioIdAndIdentificacaoIgnoreCase(UUID patioId, String identificacao);

    Optional<Vaga> findByPatioIdAndIdentificacaoIgnoreCase(UUID patioId, String identificacao);

    List<Vaga> findByPatioIdAndStatus(UUID patioId, StatusVaga status);

    // se houver algo como findByPatioIdAndNomeContainingIgnoreCase, ajuste:
    Page<Vaga> findByPatioId(UUID patioId, Pageable pageable); // exemplo

    long countByPatioId(UUID patioId);

    @Modifying
    @Query("update Vaga v set v.moto = null, v.status = br.com.fiap.apisecurity.model.enums.StatusVaga.LIVRE where v.moto.id = :motoId")
    int liberarPorMotoId(UUID motoId);
}

