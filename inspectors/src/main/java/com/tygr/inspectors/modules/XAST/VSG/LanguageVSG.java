package com.tygr.inspectors.modules.XAST.VSG;

public enum LanguageVSG {

    CPP("CPP"), PLSQL("PLSQL"), JAVA("JAVA"), CS("CS"), VB("VB"), PHP("PHP"), COBOL("COBOL");

    private String argument;

    LanguageVSG(String argument) {
        this.argument = argument;
    }

    public String getArgument() {
        return argument;
    }

}
