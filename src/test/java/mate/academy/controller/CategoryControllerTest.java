package mate.academy.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import mate.academy.model.Category;
import mate.academy.service.CategoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@RunWith(SpringRunner.class)
//@WebMvcTest(CategoryController.class)
public class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryService categoryService;

    private ObjectMapper om = new ObjectMapper();

    @Test
    public void getAllCategories() throws Exception {
        Category category = new Category();
        category.setId(1L);
        category.setName("test");
        category.setDescription("test");
        List<Category> categories = Collections.singletonList(category);

        when(categoryService.getAll()).thenReturn(categories);

        String jsonCategory = om.writeValueAsString(categories);

        mockMvc.perform(get("/api/category")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(jsonCategory));
    }

    @Test
    public void getCategoryByIdWithCorrect() throws Exception {
        Category category = new Category();
        category.setId(1L);
        category.setName("test");
        category.setDescription("test");

        when(categoryService.getById(anyLong())).thenReturn(Optional.of(category));

        String jsonCategory = om.writeValueAsString(category);

        mockMvc.perform(get("/api/category/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(jsonCategory));
    }

    @Test
    public void getCategoryByIdWithNotCorrect() throws Exception {
        when(categoryService.getById(anyLong())).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/category/10")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isNotFound());
    }
}
