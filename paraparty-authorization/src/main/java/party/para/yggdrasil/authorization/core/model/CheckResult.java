package party.para.yggdrasil.authorization.core.model;

public class CheckResult {
    private String message;

    private int status;

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return this.message;
    }

    public int getStatus() {
        return this.status;
    }
}
