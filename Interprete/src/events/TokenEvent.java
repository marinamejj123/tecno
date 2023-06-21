/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package events;

import analex.utils.Token;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;

/**
 *
 * @author HP
 */
public class TokenEvent extends EventObject {

    private int action;//accion de caso de uso
    private List<String> params;//lista de parametros

    private String sender;

    public TokenEvent(Object source) {
        super(source);
        params = new ArrayList<>();
    }

    public TokenEvent(Object source, String sender) {

        super(source);
        this.sender = sender;
        params = new ArrayList<>();
    }

    public TokenEvent(Object source, List<String> params, int action) {

        super(source);
        this.action = action;
        this.params = params;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public List<String> getParams() {
        return params;
    }

    public void setParams(List<String> params) {
        this.params = params;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    @Override
    public Object getSource() {
        return source;
    }

    public void setSource(Object source) {
        this.source = source;
    }

    public void addParams(String param) {
        if (!param.isEmpty()) {
            params.add(param);
        }
    }

    public String getParams(int pos) {
        return params.get(pos);
    }

    public int countParams() {
        return params.size();
    }

    @Override
    public String toString() {
        Token token = new Token();
        String s = "";
        s = s + "Remitente" + sender + "\n";
        s = s + "Action" + token.getStringToken(action) + "\n";
        s = s + "Params" + "\n";

        for (String param : params) {
            s = s +  "  "+ param+"\n";
        }

        return s;
    }

}
