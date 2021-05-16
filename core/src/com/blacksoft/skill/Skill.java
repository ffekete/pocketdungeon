package com.blacksoft.skill;

public interface Skill<T> {

    void select(T target);

    void act();

}
