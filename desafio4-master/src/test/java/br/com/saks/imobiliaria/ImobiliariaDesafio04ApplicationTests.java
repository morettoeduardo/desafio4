package br.com.saks.imobiliaria;

import static br.com.saks.imobiliaria.controller.ClienteController.getSHA256;
import br.com.saks.imobiliaria.model.Administrador;
import br.com.saks.imobiliaria.model.Cliente;
import br.com.saks.imobiliaria.model.Imovel;
import br.com.saks.imobiliaria.model.TipoImovel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.yml")
class ImobiliariaDesafio04ApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void contextLoads() {
    }

    @Test
    void createCliente() throws Exception {
        Cliente cliente = new Cliente();
        cliente.setNome("Teste");
        cliente.setSenha(getSHA256("Teste"));
        cliente.setEmail("Teste");
        cliente.setTelefone("Teste");
        mockMvc.perform(MockMvcRequestBuilders.post("/cliente")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cliente)))
                .andExpect(status().isOk());
    }

    @Test
    void updateCliente() throws Exception {
        Cliente cliente = new Cliente();
        cliente.setNome("Teste1");
        cliente.setSenha(getSHA256("Teste1"));
        cliente.setEmail("Teste1");
        cliente.setTelefone("Teste1");
        mockMvc.perform(MockMvcRequestBuilders.put("/cliente/{id}", 3)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cliente)))
                .andExpect(status().isOk());
    }

    @Test
    void deleteCliente() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/cliente/{id}", 4))
                .andExpect(status().isOk());
    }

    @Test
    void getAllCliente() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/cliente")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void getByIdCliente() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/cliente/{id}", 5)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }
    
    @Test
    void createImovel() throws Exception {
        Imovel imovel = new Imovel();
        imovel.setTitulo("Apartamento");
        imovel.setDescricao("Belo apartamento beira a mar");
        imovel.setValor(2000.00f);
        imovel.setStatus(1);
        mockMvc.perform(MockMvcRequestBuilders.post("/imovel")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(imovel)))
                .andExpect(status().isOk());
    }

    @Test
    void updateImovel() throws Exception {
        Imovel imovel = new Imovel();
        imovel.setTitulo("Casa");
        imovel.setDescricao("Bela casa a beira a mar");
        imovel.setValor(6000.00f);
        imovel.setStatus(1);
        mockMvc.perform(MockMvcRequestBuilders.put("/imovel/{id}", 4)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(imovel)))
                .andExpect(status().isOk());
    }

    @Test
    void deleteImovel() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/imovel/{id}", 2))
                .andExpect(status().isOk());
    }

    @Test
    void getAllImovel() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/imovel")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void getByIdImovel() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/imovel/{id}", 4)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }
    
    @Test
    void createTipoImovel() throws Exception {
        TipoImovel tipoimovel = new TipoImovel();
        tipoimovel.setNome("casa");
        mockMvc.perform(MockMvcRequestBuilders.post("/tipoimovel")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(tipoimovel)))
                .andExpect(status().isOk());
    }

    @Test
    void updateTipoImovel() throws Exception {
        TipoImovel tipoimovel = new TipoImovel();
        tipoimovel.setNome("casa");
        mockMvc.perform(MockMvcRequestBuilders.put("/tipoimovel/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(tipoimovel)))
                .andExpect(status().isOk());
    }

    @Test
    void deleteTipoImovel() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/tipoimovel/{id}", 3))
                .andExpect(status().isOk());
    }

    @Test
    void getAllTipoImovel() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/tipoimovel")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void getByIdTipoImovel() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/tipoimovel/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }
    
    @Test
    void createAdministrador() throws Exception {
        Administrador administrador = new Administrador();
        administrador.setNome("Jorge");
        administrador.setEmail("jorge@email.com");
        administrador.setSenha(getSHA256("Teste"));
        administrador.setStatus(1);
        mockMvc.perform(MockMvcRequestBuilders.post("/administrador")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(administrador)))
                .andExpect(status().isOk());
    }

    @Test
    void updateAdministrador() throws Exception {
        Administrador administrador = new Administrador();
        administrador.setNome("Kleber");
        administrador.setEmail("kleber@email.com");
        administrador.setSenha(getSHA256("Teste"));
        administrador.setStatus(1);
        mockMvc.perform(MockMvcRequestBuilders.put("/administrador/{id}", 3)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(administrador)))
                .andExpect(status().isOk());
    }

    @Test
    void deleteAdministrador() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/administrador/{id}", 2))
                .andExpect(status().isOk());
    }

    @Test
    void getAllAdministrador() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/administrador")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void getByIdAdministrador() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/administrador/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }
    
    

}
