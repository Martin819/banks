package cz.polreich.banks.model.erste;



public class ErsteCashWithdrawal  {
    private int limit;
    private String excessDeadline;

    public ErsteCashWithdrawal() {
    }

    public ErsteCashWithdrawal(int limit, String excessDeadline) {
        this.limit = limit;
        this.excessDeadline = excessDeadline;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getExcessDeadline() {
        return excessDeadline;
    }

    public void setExcessDeadline(String excessDeadline) {
        this.excessDeadline = excessDeadline;
    }
}
