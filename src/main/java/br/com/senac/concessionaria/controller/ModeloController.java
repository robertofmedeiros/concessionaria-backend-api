package br.com.senac.concessionaria.controller;

import br.com.senac.concessionaria.dto.ModeloRequest;
import br.com.senac.concessionaria.dto.ModeloResponse;
import br.com.senac.concessionaria.modelo.Modelo;
import br.com.senac.concessionaria.repository.ModeloRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping({"/modelo"})
public class ModeloController {
    @Autowired
    private ModeloRepository modeloRepository;

    @PostMapping
    public ResponseEntity<Void> criarModelo(@RequestBody @NotNull ModeloRequest modeloRequest){
        Modelo modeloModel = new Modelo();

        modeloModel.setNome(modeloRequest.getNome());

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

        // laço de repetição para percorrer a lista de marcas
        for(Modelo dadoMarca : marcaList){
            marcaResponseList.add(new ModeloResponse(dadoMarca.getId(), dadoMarca.getNome()));
        }

        return ResponseEntity.ok().body(marcaResponseList);
    }
}
