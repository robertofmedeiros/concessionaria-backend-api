package br.com.senac.concessionaria.dto;

public class ModeloResponse {
    private Long id;
    private String nome;

    private MarcaResponse marca;

    public ModeloResponse(Long id, String nome, MarcaResponse marca) {
        this.id = id;
        this.nome = nome;
        this.marca = marca;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public MarcaResponse getMarca() {
        return marca;
    }

    public void setMarca(MarcaResponse marca) {
        this.marca = marca;
    }
}
