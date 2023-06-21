/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analex;

import analex.utils.Cinta;
import analex.utils.TSParams;
import analex.utils.Token;
import java.util.LinkedList;
import java.util.List;
/**
 *
 * @author TecnoWeb
 */
public class Analex {
   
    public static final char EOF=0;
    public static final char space=32;
    public static final char tab=9;
    public static final char nl=10;
    
    
    
    private Cinta cinta;
    private String ac;
    private Token r;
    private TSParams tsp;
    
    public Analex(String cinta) {
        this.cinta=new Cinta(cinta);
        tsp=new TSParams();
        init();
    }

    public void init(){
        this.cinta.init();
        tsp.init();
        dt();
    }
    
    public Token preAnalisis(){
        return r;
    }
    public void next(){
        dt();
    }

    public void showParams(){
        System.out.println(tsp);
    }
    
    public String getParam(int pos){
        return tsp.getStr(pos);
    }

    public String lexeme(){
        return ac;
    }

    private boolean isSpace(char c){
        return (c==space || c==tab || c==nl);
    }

    private boolean isDigit(char c){
        return ('0' <=c && c<= '9');
    }

    private boolean isLetter(char c){
        return (('A' <=c && c<= 'Z') ||('a' <=c && c<= 'z'));
    }

    private boolean isBeginParams(char c){
        return c=='(';
    }

    private boolean isEndParams(char c){
        return c==')';
    }


    private boolean isComa(char c){
        return c==44;
    }


    private boolean isDate(char c){
        return c=='-' || c=='/' || c==':';
    }


    private boolean isEmail(char c){
        return c==49 || c=='.' || c=='_' ||c=='-';
    }


    private void dt(){

        int status=0;
        int index=-1;
        while(true){
            char c = cinta.cc();
            switch (status) {
                case 0:
                    if (isSpace(c)) {
                        cinta.forward();
                        status=0;
                    } else if (isLetter(c)) {
                        ac=c+"";
                        cinta.forward();
                        status=1;
                    }else if (isBeginParams(c) || isComa(c)){
                        ac="";
                        cinta.forward();
                        status=33;
                    }else if(c==EOF || isEndParams(c)){
                        ac="";
                        status=6;
                    }else{
                        ac=""+c;
                        status=7;
                    }
                    break;
                case 1:
                    if (isLetter(c)) {
                        ac=ac+c;
                        cinta.forward();
                        status=1;
                    } else if(isSpace(c) || isBeginParams(c)){
                        status=2;
                    }else{
                        ac=""+c;
                        status=7;
                    }
                    break;
                case 2: 
                    r=new Token(ac);
                    return;
                case 33:
                    if (isSpace(c)) {
                        cinta.forward();
                        status = 33;
                    }else if (isLetter(c)){
                        ac=ac+c;
                        cinta.forward();
                        status=3;
                    }else if (isDigit(c)){
                        ac=ac+c;
                        cinta.forward();
                        status=34;
                    }else if(isEndParams(c)){
                        status=5;
                    }else{
                        ac=""+c;
                        status=8;
                    }
                    break;
                case 34:
                    if (isSpace(c)) {
                        cinta.forward();
                        status = 34;
                    }else if (isDigit(c)){
                        ac=ac+c;
                        cinta.forward();
                        status=34;
                    }else if(isEndParams(c)){
                        status=5;
                    }else if (isComa(c)) {
                        status=4;
                    }else if (isLetter(c)){
                        status=3;
                    }else{
                        status=3;
                    }
                    break;

                case 3:
                    if (isLetter(c) || isDigit(c) || isDate(c) || isEmail(c)) {
                        ac=ac+c;
                        cinta.forward();
                        status = 3;
                    }else if (isEndParams(c)){
                        status=5;
                    }else if(c== space){
                        ac=ac+c;
                        cinta.forward();
                        status=3;
                    }else if (isSpace(c)) {
                        cinta.forward();
                        status=3;
                    }else if (isComa(c)){
                        status=4;
                    }else{
                        ac=""+c;
                        status=8;
                    }
                    break;

                case 4:
                    index=tsp.add(ac);
                    r=new Token(Token.params, index);
                    return;
                case 5:
                    index=tsp.add(ac);
                    r=new Token(Token.params, index);
                    return;
                case 6:
                    r=new Token(Token.end);
                    return;
                case 7:
                    r=new Token(Token.error, Token.error_Command);
                    return;
                case 8:
                    r=new Token(Token.error, Token.error_Character);
                    return;
                    
                default:
                    throw new AssertionError();
            }
        }
        
    }


}
