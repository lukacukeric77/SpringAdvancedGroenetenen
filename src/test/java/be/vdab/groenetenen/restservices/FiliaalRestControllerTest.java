package be.vdab.groenetenen.restservices;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Sql("/insertFiliaal.sql")
class FiliaalRestControllerTest extends AbstractTransactionalJUnit4SpringContextTests {

    private final MockMvc mvc;

    public FiliaalRestControllerTest(MockMvc mvc) {
        this.mvc = mvc;
    }

    private long idVanTestFiliaal(){
        return super.jdbcTemplate.queryForObject("select id from filialen where naam='test'", Long.class);
    }

    @Test
    void filiaalLezenDatNietBestaat() throws Exception{
        mvc.perform(get("/filialen/-1")
        .accept(MediaType.APPLICATION_XML))
        .andExpect(status().isNotFound());
    }

    //Does not work because support for XML HATEOAS is stopped in JDK9
    @Test
    void filiaalDatBestaatLezenInXMLFormaat() throws Exception{
        long id = idVanTestFiliaal();
        mvc.perform(get("/filialen/"+id)
        .accept(MediaType.APPLICATION_XML))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_XML))
                .andExpect(xpath("/filiaalModel/filiaal/id")
                .string(String.valueOf(id)));
    }

    @Test
    void filiaalDatBestaatLezenInJSONFormaat() throws Exception {
        long id = idVanTestFiliaal();
        mvc.perform(get("/filialen/"+id)
        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.filiaal.id").value((int) id));
    }
}
