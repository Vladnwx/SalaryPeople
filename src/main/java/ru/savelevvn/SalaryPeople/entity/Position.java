package ru.savelevvn.SalaryPeople.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.Arrays;
import java.util.List;

@Getter
@AllArgsConstructor
public enum Position {

    DEV("DEV",true),
    BOSS("BOSS",true),
    PROJECT_MANAGER("PROJECT_MANAGER",true),
    ADMIN("ADMIN",true),
    FOREMAN( "FOREMAN",true),
    ELECTRICIAN("ELECTRICIAN",false),
    ACCOUNTANT( "ACCOUNTANT",false),
    OFFICE( "OFFICE",false),
    NOT_INITIALIZED( "NOT_INITIALIZED",false)
    ;
    private final String name;
    private final boolean isItr;


    Position() {
        this.name = "NOT_INITIALIZED";
        this.isItr = false;
    }

    public static List<String> getAll(){
        return List.of(Arrays.stream(Position.values()).toList().toString());
    }


}
