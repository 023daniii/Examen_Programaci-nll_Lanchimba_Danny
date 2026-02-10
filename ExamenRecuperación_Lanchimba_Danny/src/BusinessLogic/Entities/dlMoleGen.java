package BusinessLogic.Entities;

public class dlMoleGen {
    private dlChip dlChip;
    private dlGen dlGen;

    public dlMoleGen(dlChip dlChip, dlGen dlGen) {
        this.dlChip = dlChip;
        this.dlGen = dlGen;
    }

    public dlChip getDlChip() {
        return dlChip;
    }

    public void setDlChip(dlChip dlChip) {
        this.dlChip = dlChip;
    }

    public dlGen getDlGen() {
        return dlGen;
    }

    public void setDlGen(dlGen dlGen) {
        this.dlGen = dlGen;
    }
}