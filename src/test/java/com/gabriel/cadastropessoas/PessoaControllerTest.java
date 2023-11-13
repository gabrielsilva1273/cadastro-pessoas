package com.gabriel.cadastropessoas;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.gabriel.cadastropessoas.model.Contato;
import com.gabriel.cadastropessoas.model.Pessoa;
import com.gabriel.cadastropessoas.repository.ContatoRepository;
import com.gabriel.cadastropessoas.repository.PessoaRepository;
import com.gabriel.cadastropessoas.service.PessoaService;
import com.gabriel.cadastropessoas.utils.PessoasUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class PessoaControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private ContatoRepository contatoRepository;

    @Autowired
    private PessoaService pessoaService;

    @Test
    public void deveCriarPessoa() throws Exception {
        Pessoa pessoaValida = PessoasUtils.getPessoaValida();
        mockMvc.perform(post("/pessoas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(pessoaValida)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.contatos[:1].id").value(1))
                .andExpect(jsonPath("$.contatos[:1].nome").value("André"));
    }

    @Test
    public void deveConsultarTodos() throws Exception {
        List<Pessoa> listaDePessoas = PessoasUtils.getListaDePessoas(10);
        pessoaRepository.saveAll(listaDePessoas);

        mockMvc.perform(get("/pessoas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalDePessoas").value(10))
                .andExpect(jsonPath("$.pessoas[:1].nome").value("Nero10"))
                .andExpect(jsonPath("$.pessoas[:1].contatos[:1].nome").value("André"));
    }

    @Test
    public void deveAlterar() throws Exception {
        Pessoa pessoaValida = PessoasUtils.getPessoaValida();
        pessoaRepository.save(pessoaValida);

        mockMvc.perform(get("/pessoas/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Nero"));

        pessoaValida.setNome("Lorena");

        List<Contato> contatoList = pessoaValida.getContatos();
        Contato contato = contatoList.get(0);
        contato.setNome(contato.getNome() + " Teste");
        contatoList.set(0, contato);
        pessoaValida.setContatos(contatoList);

        mockMvc.perform(put("/pessoas/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(pessoaValida)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Lorena"))
                .andExpect(jsonPath("$.contatos[:1].nome").value("André Teste"));

    }

    @Test
    public void deveExcluir() throws Exception {
        Pessoa pessoaValida = PessoasUtils.getPessoaValida();
        pessoaRepository.save(pessoaValida);

        mockMvc.perform(delete("/pessoas/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void deveConsultarUnico() throws Exception {
        Pessoa pessoaValida = PessoasUtils.getPessoaValida();
        pessoaRepository.save(pessoaValida);

        mockMvc.perform(get("/pessoas/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Nero"));
    }

    private static String asJsonString(final Object obj) throws JsonProcessingException {
        ObjectMapper mapper = JsonMapper.builder().addModule(new JavaTimeModule()).build();
        return mapper.writeValueAsString(obj);
    }
}
