package br.com.senac.concessionaria.repository;

import br.com.senac.concessionaria.modelo.Placa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlacaRepository extends JpaRepository<Placa, Long> {
}
