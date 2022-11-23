package br.com.senac.concessionaria.controller;

import br.com.senac.concessionaria.dto.MarcaRequest;
import br.com.senac.concessionaria.dto.MarcaResponse;
import br.com.senac.concessionaria.modelo.Marca;
import br.com.senac.concessionaria.repository.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity<Void> removerMarca(@PathVariable Long id){

        marcaRepository.deleteById(id);

        return ResponseEntity.ok().body(null);
    }

    @DeleteMapping
    public ResponseEntity<Void> removerMarcaAll(){

        marcaRepository.deleteAll();

        return ResponseEntity.ok().body(null);
    }

    @PutMapping(path = {"/{id}"})
    public ResponseEntity<Void> atualizarMarca(@RequestBody MarcaRequest marcaRequest, @PathVariable Long id){
        Optional<Marca> marca;

        marca = marcaRepository.findById(id)
                .map(record -> {
                   record.setDescricao(marcaRequest.getDescricao());
                   record.setNome(marcaRequest.getNome());
                   return marcaRepository.save(record);
                });

        return ResponseEntity.ok().body(null);
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<MarcaResponse> carregarMarcaById(@PathVariable Long id){

        Optional<Marca> marca = marcaRepository.findById(id);

        MarcaResponse marcaResponse = new MarcaResponse();
        marcaResponse.setId(marca.get().getId());
        marcaResponse.setNome(marca.get().getNome());
        marcaResponse.setDescricao(marca.get().getDescricao());

        return ResponseEntity.ok().body(marcaResponse);
    }
}
