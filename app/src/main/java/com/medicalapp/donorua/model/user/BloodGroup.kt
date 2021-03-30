package com.medicalapp.donorua.model.user

enum class BloodGroup(val nameId: String) {
    APlus("A+"),
    AMinus("A-"),
    BPlus("B+"),
    BMinus("B-"),
    ABPlus("AB+"),
    ABMinus("AB-"),
    AOnePlus("A1+"),
    AOneMinus("A1-"),
    AOneBPlus("A1B+"),
    AOneBMinus("A1B-"),
    ATwoPlus("A2+"),
    ATwoMinus("A2-"),
    ATwoBPlus("A2B+"),
    ATwoBMinus("A2B-"),
    OPlus("O+"),
    OMinus("O-"),
    BombayBloodGroup("BOMBAY BLOOD GROUP"),
    Inra("INRA"),
    DontKnow("Don't know"),
    Other("Other")
}