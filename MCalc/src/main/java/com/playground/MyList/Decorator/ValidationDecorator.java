package com.playground.MyList.Decorator;

import com.playground.MyList.Decorator.api.MyListV3;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class ValidationDecorator<T> extends MyListV3Decorator<T>{
    protected ValidationDecorator(MyListV3<T> decoratedMyList) {
        super(decoratedMyList);
    }

    @Override
    public boolean add(T item) {
        validate(item);
        return super.add(item);
    }

    @Override
    public boolean addAll(List<T> items) {
        for (T item : items) {
            validate(item);
        }
        return super.addAll(items);
    }

    private void validate(T item) {
        log.info("Validating item: {}", item);
        // validation logic
    }
}
