package com.example.homeworktask3;

import java.util.HashMap;
import java.util.List;

public class FakeDatabase {
    public static HashMap<String, Cat> cats = new HashMap<>();

    public static Cat getCatById(String catId) {
        return cats.get(catId);
    }
    public static void saveCatToFakeDatabase(List<Cat> catsToSave) {
        for(int i = 0; i < catsToSave.size(); i++) {
            Cat cat = catsToSave.get(i);
            cats.put(cat.getCatId(), cat);
        }
    }
}