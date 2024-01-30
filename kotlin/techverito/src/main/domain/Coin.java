package domain;

public enum Coin {
    ONE(1), FIVE(5), TEN(10), TWENTY_FIVE(25);

    private int num;

    Coin(int num){
        this.num = num;
    }

    public int getNum() {
        return num;
    }
}
