package com.Learning.learnspringframework.businesslogicexample;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component

public class MongoDBDataService implements DataService{
    @Override
    public int[] retrieveData() {
        return new int[] {11,22,33,44,55};
    }
}
