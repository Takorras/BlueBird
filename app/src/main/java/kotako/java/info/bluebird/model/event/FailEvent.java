package kotako.java.info.bluebird.model.event;


public class FailEvent {
    private String text;
    public FailEvent(String text){
        this.text = text;
    }

    public String getText(){
        return text;
    }
}

