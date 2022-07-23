package ro.andreimihalcea.food_app.enums;

public enum ItemSize {

    S(250, 350),
    M(330, 450),
    L(500, 550),
    XL(1000, 750);

    private int sizeOfDrink;
    private int sizeOfDish;

    ItemSize(int sizeOfDrink, int sizeOfDish) {
        this.sizeOfDrink = sizeOfDrink;
        this.sizeOfDish = sizeOfDish;
    }
}
