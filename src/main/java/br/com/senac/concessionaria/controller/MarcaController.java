package br.com.senac.concessionaria.controller;

import br.com.senac.concessionaria.dto.MarcaRequest;
import br.com.senac.concessionaria.dto.MarcaResponse;
import br.com.senac.concessionaria.modelo.Marca;
import br.com.senac.concessionaria.repository.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping({"/marca"})
public class MarcaController {

    @Autowired
    private MarcaRepository marcaRepository;

    @CrossOrigin(origins = "*")
    @PostMapping
    public ResponseEntity<Void> criarMarca(@RequestBody MarcaRequest marcaRequest){
        Marca marcaModel = new Marca();

        marcaModel.setNome(marcaRequest.getNome());
        marcaModel.setDescricao(marcaRequest.getDescricao());
        try {
            marcaRepository.save(marcaModel);

            return ResponseEntity.ok().body(null);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(null);
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping
    public ResponseEntity<List<MarcaResponse>> retornarMarcas(){
        // criar lista vazia
        List<Marca> marcaList = new ArrayList<>();

        // carrega informações do banco de dados
        // adiciona na lista vazia
        marcaList = marcaRepository.findAll();

        // cria lista vazia para retorno
        List<MarcaResponse> marcaResponseList = new ArrayList<>();

        // laço de repetição para percorrer a lista de marcas
        for(Marca dadoMarca : marcaList){
            marcaResponseList.add(new MarcaResponse(dadoMarca.getId(), dadoMarca.getNome(), dadoMarca.getDescricao()));
        }

        return ResponseEntity.ok().body(marcaResponseList);
    }
}
