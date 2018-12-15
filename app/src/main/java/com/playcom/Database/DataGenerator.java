package com.playcom.Database;

import com.playcom.Database.Model.PlanCategory;

import java.util.ArrayList;
import java.util.List;

public class DataGenerator {
    public List<PlanCategory> GenerateDefaultCategories() {
        String names[] = new String[]{"School", "Work", "Other"};
        String explanations[] = new String[]{"To do list for school.", "To do list for work", "Other"};
        List<PlanCategory> categories = new ArrayList<>();
        for (int i = 0; i < names.length; i++) {
            PlanCategory planCategory = new PlanCategory();
            planCategory.setName(names[i]);
            planCategory.setExplanation(explanations[i]);
            categories.add(planCategory);
        }
        return categories;
    }
}
