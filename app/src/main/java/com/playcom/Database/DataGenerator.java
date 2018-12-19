package com.playcom.Database;

import com.playcom.Database.Model.Action;
import com.playcom.Database.Model.FunctionCategory;
import com.playcom.Database.Model.PlanCategory;

import java.util.ArrayList;
import java.util.List;

public class DataGenerator {
    public List<PlanCategory> GenerateDefaultCategories() {
        String names[] = new String[]{"School", "Work", "Daily","Personal","Other"};
        String explanations[] = new String[]{"To do list for school.", "To do list for work","Your Daily Activities","Your Personal Works", "Other"};
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
        String names[] = new String[]{"Email","Sms","Alert","Gift"};
        String explanations[] = new String[]{"Set A Action For Email Template Any Time","Set A Action For Sms Template Any Time","Set A Reminder for you","Buy a birtday gift"};
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
