package com.hkb.shoppingcart;

import com.hkb.shoppingcart.controller.ProductRestController;
import com.hkb.shoppingcart.model.Product;
import com.hkb.shoppingcart.repo.ProductRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ShoppingcartApplication.class)
@WebAppConfiguration
public class ProductRestControllerTest {
    private static final Logger logger = LoggerFactory.getLogger(ProductRestControllerTest.class);

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    private MockMvc mockMvc;
    private HttpMessageConverter mappingJackson2HttpMessageConverter;

    private List<Product> productList = new ArrayList<>();
    private SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {

        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
                .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
                .findAny()
                .orElse(null);

        assertNotNull("the JSON message converter must not be null",
                this.mappingJackson2HttpMessageConverter);
    }

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();

        this.productRepository.deleteAll();

        Date prodOneRelease, prodTwoRelease;

        prodOneRelease = new Date(1507220850000L);

        this.productList.add(productRepository.save(
                new Product(
                        "colgate white",
                        "GE-1206",
                        3.95f,
                        prodOneRelease,
                        "dentifrice",
                        4.5f,
                        "http://colgate.com/2/",
                        250
                        )));

        prodTwoRelease = new Date(1486312050000L);
        this.productList.add(productRepository.save(
                new Product(
                        "gilette mousse",
                        "GE-1206",
                        2.95f,
                        prodTwoRelease,
                        "mousse a raser",
                        4.2f,
                        "http://gilette.com/2/",
                        1000
                )));
    }

    @Test
    public void productNotFound() throws Exception {
        logger.debug("---Testing product not found response---");
        mockMvc.perform(get("/products/0/")
                .content(this.json(new Product()))
                .contentType(contentType))
                .andExpect(status().isNotFound());
    }

    @Test
    public void createProduct() throws Exception {
        logger.debug("---Testing product creation---");

        Date prodRelease = this.ft.parse("2017-07-04T20:15:55.570Z");

        String productJson = this.json(
                new Product(
                        "sechoir",
                        "GE-1203",
                        19.95f,
                        prodRelease,
                        "sechoir a cheveux",
                        3.0f,
                        "http://openclipart.org/image/300px/svg_to_png/26215/Anonymous_Leaf_Rake.png",
                        600
                        ));

        this.mockMvc.perform(post("/products")
                .contentType(contentType).content(productJson))
        .andExpect(status().isCreated());
    }

//    @Test
//    public void updateProduct() throws Exception {
//        logger.debug("---Testing product update---");
//        //will update the product sechoir description
//    }

    protected String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }
}
