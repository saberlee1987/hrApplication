package model;

public enum ContractType {
    FullTime(0), PartTime(1), Contractual(2);
    private int value;

    ContractType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static ContractType getInstance(int value) {
        for (ContractType contractType : ContractType.values()) {
            if (contractType.getValue() == value) {
                return contractType;
           }
        }
        return null;
    }
}
