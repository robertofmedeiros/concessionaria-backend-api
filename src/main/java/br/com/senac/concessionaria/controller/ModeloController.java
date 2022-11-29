package br.com.senac.concessionaria.controller;

import br.com.senac.concessionaria.dto.MarcaResponse;
import br.com.senac.concessionaria.dto.ModeloRequest;
import br.com.senac.concessionaria.dto.ModeloResponse;
import br.com.senac.concessionaria.dto.PlacaResponse;
import br.com.senac.concessionaria.modelo.Marca;
import br.com.senac.concessionaria.modelo.Modelo;
import br.com.senac.concessionaria.modelo.Placa;
import br.com.senac.concessionaria.repository.MarcaRepository;
import br.com.senac.concessionaria.repository.ModeloRepository;
import br.com.senac.concessionaria.repository.PlacaRepository;
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

    @Autowired
    private PlacaRepository placaRepository;

    @PostMapping
    public ResponseEntity<Void> criarModelo(@RequestBody ModeloRequest modeloRequest){
        Modelo modeloModel = new Modelo();

        Optional<Marca> marca = marcaRepository.findById(modeloRequest.getIdMarca());

        Optional<Placa> placa = placaRepository.findById(modeloRequest.getIdPlaca());

        modeloModel.setNome(modeloRequest.getNome());
        modeloModel.setMarca(marca.get());
        modeloModel.setPlaca(placa.get());

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

            Placa placa = dadoModelo.getPlaca();

            MarcaResponse marcaResponse = new MarcaResponse();
            if(marca != null){
                marcaResponse.setId(marca.getId());
                marcaResponse.setDescricao(marca.getDescricao());
                marcaResponse.setNome(marca.getNome());
            }

            PlacaResponse placaResponse = new PlacaResponse();
            if(placa != null){
                placaResponse.setId(placa.getId());
                placaResponse.setNumero(placa.getNumero());
            }

            marcaResponseList.add(new ModeloResponse(dadoModelo.getId(), dadoModelo.getNome(), marcaResponse, placaResponse));
        }

        return ResponseEntity.ok().body(marcaResponseList);
    }
}
