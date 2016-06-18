package com.songokute.services;

import com.songokute.entities.Item;
import com.songokute.repo.MongoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Admin on 6/18/2016.
 */
@RestController
public class ItemService {
    @Autowired
    MongoUtils mongoUtils;

    @RequestMapping("item/countall")
    public int getCountItem() {
        return mongoUtils.getTotalItem();
    }

    @RequestMapping("item/list/{page}")
    public List<Item> getPagedListItem(@PathVariable("page") Integer page) {
        List<Item> listItem;
        listItem = mongoUtils.getPagedListItem(page);
        return listItem;
    }

    @RequestMapping(value = "item/add", method = RequestMethod.POST)
    public String insertNewItem(@RequestBody Item item) {
        return mongoUtils.addNewItem(item);
    }
}
