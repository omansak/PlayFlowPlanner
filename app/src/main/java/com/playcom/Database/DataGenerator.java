package com.playcom.Database;

import com.playcom.Database.Model.Action;
import com.playcom.Database.Model.FunctionCategory;
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
    public List<FunctionCategory> GenerateDefaultFunctionCategories() {
        String names[] = new String[]{"Email",};
        String explanations[] = new String[]{"Set A Action For Email Template Any Time"};
        List<FunctionCategory> categories = new ArrayList<>();
        for (int i = 0; i < names.length; i++) {
            FunctionCategory functionCategory = new FunctionCategory();
            functionCategory.setName(names[i]);
            functionCategory.setExplanation(explanations[i]);
            categories.add(functionCategory);
        }
        return categories;
    }
}
