/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package analex.models;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */
public class TokenCommand {
 private int name ; //casos de uso 
 private int action;
 private List<Integer> params;
 
 public TokenCommand(){
     params = new ArrayList<>();
 }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public List<Integer> getParams() {
        return params;
    }

    public void setParams(List<Integer> params) {
        this.params = params;
    }
 
    public void addParams(int pos){
        params.add(pos);
    }
    
    public int getParams(int pos){
        return params.get(pos);
    }

    public int countParams(){
        return params.size();
    }
    
}
