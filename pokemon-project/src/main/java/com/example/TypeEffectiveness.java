package com.example;

import java.util.ArrayList;
import java.util.List;

public class TypeEffectiveness {
    private List<String[]> doubleDamageTo;
    private List<String[]> halfDamageTo;
    private List<String[]> noDamageTo;

    public TypeEffectiveness() {
        doubleDamageTo = new ArrayList<>();
        halfDamageTo = new ArrayList<>();
        noDamageTo = new ArrayList<>();
    }

    public void addDoubleDamageTo(String attackType, String defenseType) {
        doubleDamageTo.add(new String[]{attackType, defenseType});
    }

    public void addHalfDamageTo(String attackType, String defenseType) {
        halfDamageTo.add(new String[]{attackType, defenseType});
    }

    public void addNoDamageTo(String attackType, String defenseType) {
        noDamageTo.add(new String[]{attackType, defenseType});
    }

    public double getEffectiveness(String attackType, String defenseType) {
        for (String[] pair : doubleDamageTo) {
            if (pair[0].equals(attackType) && pair[1].equals(defenseType)) {
                return 2.0;
            }
        }
        for (String[] pair : halfDamageTo) {
            if (pair[0].equals(attackType) && pair[1].equals(defenseType)) {
                return 0.5;
            }
        }
        for (String[] pair : noDamageTo) {
            if (pair[0].equals(attackType) && pair[1].equals(defenseType)) {
                return 0.0;
            }
        }
        return 1.0;
    }
}
