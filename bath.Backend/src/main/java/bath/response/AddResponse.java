package bath.response;

public class AddResponse extends Response{
    private String id;

    public AddResponse() {
    }

    public AddResponse(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
