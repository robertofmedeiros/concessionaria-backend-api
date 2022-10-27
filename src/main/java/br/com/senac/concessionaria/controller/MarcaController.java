package br.com.senac.concessionaria.controller;

import br.com.senac.concessionaria.dto.MarcaRequest;
import br.com.senac.concessionaria.modelo.Marca;
import br.com.senac.concessionaria.repository.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/marca"})
public class MarcaController {

    @Autowired
    private MarcaRepository marcaRepository;

    @PostMapping
    public ResponseEntity<Void> criarMarca(@RequestBody MarcaRequest marca){
        Marca marcaModel = new Marca();

        marcaModel.setNome(marca.getNome());
        marcaModel.setDescricao(marca.getDescricao());

        marcaRepository.save(marcaModel);

        return ResponseEntity.ok().body(null);
    }
}
