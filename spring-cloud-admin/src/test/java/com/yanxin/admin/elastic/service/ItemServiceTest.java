package com.yanxin.admin.elastic.service;


import com.yanxin.admin.elastic.repository.ItemRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(properties = {"spring.profiles.active=dev"})
public class ItemServiceTest {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void create() {
        /*Item item = new Item();
        item.setId("sda111");
        IndexQuery indexQuery = new IndexQueryBuilder()
                .withId(item.getId())
                .withObject(item)
                .build();
        final Item index = elasticsearchRestTemplate.queryForObject(GetQuery.getById("sda111"), Item.class);*/
        Assert.assertNotNull(itemRepository.count());

        // elasticsearchRestTemplate.createIndex(Item.class);

    }
}