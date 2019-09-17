package za.co.jaredfishy.susan.domain;

public class JZTaskResponse<T> {
    private boolean success;
    private T result;
    private String error;

    public JZTaskResponse(){
    }
    public void success(T result){
        this.success = true;
        this.result = result;
    }
    public void fail(String error){
        this.success = false;
        this.error = error;
    }

    public boolean isSuccess() {
        return success;
    }

    public T getResult() {
        return result;
    }

    public String getError() {
        return error;
    }
}
