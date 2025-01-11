import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.app.controller.ExampleController;
import com.example.app.model.ExampleModel;
import com.example.app.service.ExampleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ExampleController.class)
public class ExampleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ExampleService exampleService;

    @InjectMocks
    private ExampleController exampleController;

    private ExampleModel exampleModel;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        exampleModel = new ExampleModel();
        exampleModel.setId(1L);
        exampleModel.setName("Test Example");
    }

    @Test
    public void testGetExample() throws Exception {
        when(exampleService.getExampleById(1L)).thenReturn(exampleModel);

        mockMvc.perform(get("/api/examples/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test Example"));
    }

    @Test
    public void testCreateExample() throws Exception {
        when(exampleService.createExample(any(ExampleModel.class))).thenReturn(exampleModel);

        mockMvc.perform(post("/api/examples")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Test Example\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Test Example"));
    }

    @Test
    public void testUpdateExample() throws Exception {
        when(exampleService.updateExample(anyLong(), any(ExampleModel.class))).thenReturn(exampleModel);

        mockMvc.perform(put("/api/examples/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Updated Example\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Example"));
    }

    @Test
    public void testDeleteExample() throws Exception {
        doNothing().when(exampleService).deleteExample(1L);

        mockMvc.perform(delete("/api/examples/1"))
                .andExpect(status().isNoContent());
    }
}