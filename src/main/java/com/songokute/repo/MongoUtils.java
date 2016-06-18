package com.songokute.repo;

import com.mongodb.*;
import com.songokute.common.Consts;

import static com.songokute.common.DataUtils.*;

import com.songokute.entities.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 6/18/2016.
 */
@Component
public class MongoUtils {
    private final MongoDbFactory mongo;
    DB db;
    DBCollection itemCollection;

    @Autowired
    public MongoUtils(MongoDbFactory mongo) {
        this.mongo = mongo;
        db = mongo.getDb();
        itemCollection = db.getCollection("item");
    }

    /**
     * Count number of item in ItemCollection
     *
     * @return
     */
    public int getTotalItem() {
        int ret;
        ret = (int) itemCollection.getCount();
        return ret;
    }

    /**
     * Get all item in a page
     *
     * @param page
     * @return
     */
    public List<Item> getPagedListItem(int page) {
        List<Item> ret = new ArrayList<>();
        DBCursor cursor = itemCollection.find().skip((page - 1) * Consts.ITEM.ITEM_PER_PAGE).limit(Consts.ITEM.ITEM_PER_PAGE);
        while (cursor.hasNext()) {
            DBObject curr = cursor.next();
            if (curr != null) {
                Item item = new Item()
                        .setDescription((String) curr.get("description"))
                        .setName((String) curr.get("name"))
                        .setCode((String) curr.get("code"))
                        .setPrice(((Number) curr.get("price")).floatValue());
                ret.add(item);
            }
        }
        return ret;
    }

    /**
     * Save new item to DB
     *
     * @param item
     */
    public String addNewItem(Item item) {
        if (item == null || validateItem(item)) {
            return "Input Item is not valid";
        }
        DBObject existing = itemCollection.findOne(new BasicDBObject().append("code", item.getCode()));
        if (!isObjectNull(existing)) {
            return "Item with code " + item.getCode() + " does exist in DB";
        }
        BasicDBObject obj = new BasicDBObject();
        obj.put("name", item.getName());
        obj.put("code", item.getCode());
        obj.put("description", item.getName());
        obj.put("price", item.getPrice());
        itemCollection.insert(obj);
        return "";
    }

    private boolean validateItem(Item item) {
        return isObjectNull(item) || isStringEmpty(item.getName())
                || isStringEmpty(item.getCode())
                || isStringEmpty(item.getDescription())
                || (item.getPrice() == 0f);
    }
}
