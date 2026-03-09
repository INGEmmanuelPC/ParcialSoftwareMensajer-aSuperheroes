package model;

import java.util.Set;

public class Hero {

    private final String name;
    private final Set<Skill> skills;

    public Hero(String name, Set<Skill> skills) {
        this.name = name;
        this.skills = skills;
    }

    public String getName() {
        return name;
    }

    public boolean hasSkill(Skill skill) {
        return skills.contains(skill);
    }
}