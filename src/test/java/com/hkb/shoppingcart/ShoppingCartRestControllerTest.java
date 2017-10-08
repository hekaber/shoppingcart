package com.hkb.shoppingcart;

import com.hkb.shoppingcart.model.Product;
import com.hkb.shoppingcart.model.ShoppingCart;
import com.hkb.shoppingcart.repo.ProductRepository;
import com.hkb.shoppingcart.repo.ShoppingCartRepository;
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
import java.util.*;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ShoppingcartApplication.class)
@WebAppConfiguration
public class ShoppingCartRestControllerTest {
    private static final Logger logger = LoggerFactory.getLogger(ShoppingCartRestControllerTest.class);

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    private MockMvc mockMvc;
    private HttpMessageConverter mappingJackson2HttpMessageConverter;

    private List<ShoppingCart> cartList = new ArrayList<ShoppingCart>();
    private List<Product> productList = new ArrayList<Product>();
    private SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

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

        this.shoppingCartRepository.deleteAll();
        this.productRepository.deleteAll();

        Date prodOneRelease, prodTwoRelease, orderDate;

        prodOneRelease = this.ft.parse("2017-01-04T15:15:55.570Z");


        productRepository.save(
                new Product(
                        "colgate white",
                        "GE-1206",
                        395,
                        prodOneRelease,
                        "dentifrice",
                        4.5f,
                        "http://colgate.com/2/",
                        250
                ));

        prodTwoRelease = this.ft.parse("2017-02-04T17:15:55.570Z");
        productRepository.save(
                new Product(
                        "gilette mousse",
                        "GE-1206",
                        295,
                        prodTwoRelease,
                        "mousse a raser",
                        4.2f,
                        "http://gilette.com/2/",
                        1000
                ));

        productList = productRepository.findAll();
        orderDate = this.ft.parse("2017-10-15T17:15:55.570Z");
        HashMap<String, Product> productHashMap = new HashMap<String, Product>();
        HashMap<String, Integer> productQuantities = new HashMap<String, Integer>();
        int amount = 1;
        for(Product prod : productList) {
            productHashMap.put(prod.getId(), prod);
            productQuantities.put(prod.getId(), amount);
            amount++;
        }

        this.cartList.add(shoppingCartRepository.save(
                new ShoppingCart(
                        "pending",
                        "testuser",
                        productHashMap,
                        productQuantities,
                        orderDate,
                        orderDate,
                        0
                )));
    }

    @Test
    public void shoppingCartNotFound() throws Exception {
        logger.debug("---Testing shopping cart not found response---");
        mockMvc.perform(get("/carts/0")
                .content(this.json(new ShoppingCart()))
                .contentType(contentType))
                .andExpect(status().isNotFound());
    }

    @Test
    public void createCart() throws Exception {
        logger.debug("---Testing product creation---");

        Date date = this.ft.parse("2017-07-04T20:15:55.570Z");
        List<ShoppingCart> previousTest = this.shoppingCartRepository.findByUserName("testuser");

        for(ShoppingCart cart : previousTest) this.shoppingCartRepository.delete(cart);

        List<Product> previousProdTest = this.productRepository.findByProductCode("GE-test");
        for(Product prod : previousProdTest) this.productRepository.delete(prod);


        this.productRepository.save(new Product(
                "sechoir",
                "GE-test",
                1995,
                date,
                "sechoir a cheveux",
                3.0f,
                "http://openclipart.org/image/300px/svg_to_png/26215/Anonymous_Leaf_Rake.png",
                250
        ));

        this.productRepository.save(new Product(
                "sechoir2",
                "GE-test",
                1995,
                date,
                "sechoir a cheveux2",
                3.0f,
                "http://openclipart.org/image/300px/svg_to_png/26215/Anonymous_Leaf_Rake.png",
                250
        ));

        List<Product> prodTest = this.productRepository.findByProductCode("GE-test");
        HashMap<String, Product> productHashMap = new HashMap<String, Product>();
        HashMap<String, Integer> productQuantities = new HashMap<String, Integer>();
        int amount = 1;

        for(Product prod : prodTest) {
            productHashMap.put(prod.getId(), prod);
            productQuantities.put(prod.getId(), amount);
            amount++;
        }

        String cartJson = this.json(
                new ShoppingCart(
                        "pending",
                        "testuser",
                        productHashMap,
                        productQuantities,
                        date,
                        date,
                        0
                        ));

        this.mockMvc.perform(post("/carts")
                .contentType(contentType).content(cartJson))
                .andExpect(status().isCreated());
    }

    protected String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }
}
