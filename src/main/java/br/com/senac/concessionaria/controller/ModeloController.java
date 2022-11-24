package br.com.senac.concessionaria.controller;

import br.com.senac.concessionaria.dto.MarcaResponse;
import br.com.senac.concessionaria.dto.ModeloRequest;
import br.com.senac.concessionaria.dto.ModeloResponse;
import br.com.senac.concessionaria.modelo.Marca;
import br.com.senac.concessionaria.modelo.Modelo;
import br.com.senac.concessionaria.repository.MarcaRepository;
import br.com.senac.concessionaria.repository.ModeloRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping({"/modelo"})
public class ModeloController {
    @Autowired
    private ModeloRepository modeloRepository;

    @Autowired
    private MarcaRepository marcaRepository;

    @PostMapping
    public ResponseEntity<Void> criarModelo(@RequestBody ModeloRequest modeloRequest){
        Modelo modeloModel = new Modelo();

        Optional<Marca> marca = marcaRepository.findById(modeloRequest.getIdMarca());

        modeloModel.setNome(modeloRequest.getNome());
        modeloModel.setMarca(marca.get());

        modeloRepository.save(modeloModel);

        return ResponseEntity.ok().body(null);
    }

    @GetMapping
    public ResponseEntity<List<ModeloResponse>> retornarModelo(){
        // criar lista vazia
        List<Modelo> marcaList = new ArrayList<>();

        // carrega informações do banco de dados
        // adiciona na lista vazia
        marcaList = modeloRepository.findAll();

        // cria lista vazia para retorno
        List<ModeloResponse> marcaResponseList = new ArrayList<>();

        // laço de repetição para percorrer a lista de modelos
        for(Modelo dadoModelo : marcaList){

            Marca marca = dadoModelo.getMarca();

            MarcaResponse marcaResponse = new MarcaResponse();
            if(marca != null){
                marcaResponse.setId(marca.getId());
                marcaResponse.setDescricao(marca.getDescricao());
                marcaResponse.setNome(marca.getNome());
            }

            marcaResponseList.add(new ModeloResponse(dadoModelo.getId(), dadoModelo.getNome(), marcaResponse));
        }

        return ResponseEntity.ok().body(marcaResponseList);
    }
}
