package com.gabriel.cadastropessoas;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.gabriel.cadastropessoas.model.Contato;
import com.gabriel.cadastropessoas.model.Pessoa;
import com.gabriel.cadastropessoas.utils.PessoasUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ContatoValidacaoTest {

    @Autowired
    private MockMvc mockMvc;
    @Test
    public void emailInvalidoRequisicaoMalSucedidaTest() throws Exception {
        Pessoa pessoa = PessoasUtils.getPessoaValida();
        List<Contato> contatos = pessoa.getContatos();
        Contato contato = contatos.get(0);
        contato.setEmail("8094823098420");
        contatos.set(0,contato);
        pessoa.setContatos(contatos);

        mockMvc.perform(post("/pessoas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(pessoa)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void telefoneLongoRequisicaoMalSucedidaTest() throws Exception {
        Pessoa pessoa = PessoasUtils.getPessoaValida();
        List<Contato> contatos = pessoa.getContatos();
        Contato contato = contatos.get(0);
        contato.setTelefone("8094823098420");
        contatos.set(0,contato);
        pessoa.setContatos(contatos);

        mockMvc.perform(post("/pessoas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(pessoa)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void telefoneCurtoRequisicaoMalSucedidaTest() throws Exception {
        Pessoa pessoa = PessoasUtils.getPessoaValida();
        List<Contato> contatos = pessoa.getContatos();
        Contato contato = contatos.get(0);
        contato.setTelefone("449930");
        contatos.set(0,contato);
        pessoa.setContatos(contatos);

        mockMvc.perform(post("/pessoas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(pessoa)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void nomeNullRequisicaoMalSucedida() throws Exception {
        Pessoa pessoa = PessoasUtils.getPessoaValida();
        List<Contato> contatos = pessoa.getContatos();
        Contato contato = contatos.get(0);
        contato.setNome(null);
        contatos.set(0,contato);
        pessoa.setContatos(contatos);

        mockMvc.perform(post("/pessoas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(pessoa)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void telefoneNullRequisicaoMalSucedida() throws Exception {
        Pessoa pessoa = PessoasUtils.getPessoaValida();
        List<Contato> contatos = pessoa.getContatos();
        Contato contato = contatos.get(0);
        contato.setTelefone(null);
        contatos.set(0,contato);
        pessoa.setContatos(contatos);

        mockMvc.perform(post("/pessoas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(pessoa)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void emailNullRequisicaoMalSucedida() throws Exception {
        Pessoa pessoa = PessoasUtils.getPessoaValida();
        List<Contato> contatos = pessoa.getContatos();
        Contato contato = contatos.get(0);
        contato.setEmail(null);
        contatos.set(0,contato);
        pessoa.setContatos(contatos);

        mockMvc.perform(post("/pessoas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(pessoa)))
                .andExpect(status().isBadRequest());
    }

    private static String asJsonString(final Object obj) throws JsonProcessingException {
        ObjectMapper mapper = JsonMapper.builder().addModule(new JavaTimeModule()).build();
        return mapper.writeValueAsString(obj);
    }
}
