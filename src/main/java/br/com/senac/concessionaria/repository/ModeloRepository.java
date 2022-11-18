package br.com.senac.concessionaria.repository;

import br.com.senac.concessionaria.modelo.Modelo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModeloRepository extends JpaRepository<Modelo, Long> {
}
