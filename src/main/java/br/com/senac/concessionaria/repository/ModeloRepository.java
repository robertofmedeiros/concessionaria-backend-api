package br.com.senac.concessionaria.repository;

import br.com.senac.concessionaria.modelo.Modelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ModeloRepository extends JpaRepository<Modelo, Long> {
    @Query("select a from modelo a where a.marca.id = :idMarca")
    public List<Modelo> getModelos(@Param("idMarca") Long idMarca);
}
