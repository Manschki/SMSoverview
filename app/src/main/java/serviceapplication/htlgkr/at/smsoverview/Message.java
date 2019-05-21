package serviceapplication.htlgkr.at.smsoverview;

import java.io.Serializable;

public class Message implements Serializable {
    private String message;
    private String number;
    private String time;

    public Message() {
    }

    public Message(String message, String number, String time) {
        this.message = message;
        this.number = number;
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Message{" +
                "message='" + message + '\'' +
                ", number='" + number + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
