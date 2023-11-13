package com.gabriel.cadastropessoas.error;

public class ErroBuilder {
    private String tipo;
    private String mensagem;

    public ErroBuilder() {
        this.tipo = " ";
        this.mensagem = " ";
    }

    public ErroBuilder tipo(String tipo){
        this.tipo = tipo;
        return this;
    }

    public ErroBuilder mensagem(String mensagem){
        this.mensagem = mensagem;
        return this;
    }

    public Erro build(){
        return new Erro(this.tipo,this.mensagem);
    }
}
