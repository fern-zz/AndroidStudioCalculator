package Controller;

public class OperandBuffer {
    public long numeric = 0;
    public boolean isNegative = false;
    public int base = 10;
    public String magnitude = "";
    public String display = "0";

    public void setBase(int base) {
        this.base = base == 10 ? 10 : 2;
        getNum();
    }

    public void negate() {
        isNegative = !isNegative;
        getNum();
    }
    public void extend(int digit) {
        numeric *= 10;
        numeric += isNegative ? -1 * digit : digit;
        getNum();
    }


    private void getNum() {
        if(isNegative) numeric = Math.abs(numeric) * -1;
        else numeric = Math.abs(numeric);
        display = String.valueOf(numeric);
        magnitude = String.valueOf(numeric);
    }

    public void clear() {
        numeric = 0;
        display = "0";
        magnitude = "";
        isNegative = false;
    }
}
