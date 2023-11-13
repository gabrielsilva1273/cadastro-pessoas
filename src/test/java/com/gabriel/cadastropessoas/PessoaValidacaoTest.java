package com.gabriel.cadastropessoas;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.gabriel.cadastropessoas.model.Pessoa;
import com.gabriel.cadastropessoas.utils.PessoasUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PessoaValidacaoTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void dataNascimentoFuturoRequisicaoMalSucedidaTest() throws Exception {
        Pessoa pessoa = PessoasUtils.getPessoaValida();
        pessoa.setDataDeNascimento(LocalDate.of(2050, 6, 2));
        mockMvc.perform(post("/pessoas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(pessoa)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void cpfInvalidoRequisicaoMalSucedidaTest() throws Exception {
        Pessoa pessoa = PessoasUtils.getPessoaValida();
        pessoa.setCpf("1067773");
        mockMvc.perform(post("/pessoas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(pessoa)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void pessoaSemContatoRequisicaoMalSucedidaTest() throws Exception {
        Pessoa pessoa = PessoasUtils.getPessoaValida();
        pessoa.setContatos(null);

        mockMvc.perform(post("/pessoas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(pessoa)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void pessoaSemDataDeNascimentoRequisicaoMalSucedida() throws Exception {
        Pessoa pessoa = PessoasUtils.getPessoaValida();
        pessoa.setDataDeNascimento(null);

        mockMvc.perform(post("/pessoas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(pessoa)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void pessoaSemCpfRequisicaoMalSucedida() throws Exception {
        Pessoa pessoa = PessoasUtils.getPessoaValida();
        pessoa.setCpf(null);

        mockMvc.perform(post("/pessoas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(pessoa)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void pessoaSemNomeRequisicaoMalSucedida() throws Exception {
        Pessoa pessoa = PessoasUtils.getPessoaValida();
        pessoa.setNome(null);

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
