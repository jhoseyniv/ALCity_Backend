package com.alcity.entity.alenum;

public enum SkillType {
    Skill,
    SubSetSkill,
    MicroSkill,
    Root;

    public static SkillType getById(long id)
    {
        for (SkillType e : SkillType.values())
        {
            if (id == e.ordinal()) return e;
        }
        throw new IllegalArgumentException("no");
    }
    public static SkillType getByTitle(String title)
    {
        for (SkillType e : SkillType.values())
        {
            if (title.equalsIgnoreCase(e.name())) return e;
        }
        throw new IllegalArgumentException("no");
    }

}
