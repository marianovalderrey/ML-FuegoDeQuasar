package com.ml.springbootml.services;

import com.ml.springbootml.exceptions.MessageException;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class MessageService {
    
    public String getMessage(List<List<String>> messages) throws MessageException {
        if (messages.size() < 3){
            throw new MessageException("El número de satélites no es correcto, tienen que ser 3");
        }
        
        messages = this.removeGap(messages);

        if (!this.checkLists(messages)) {
            throw new MessageException("La lista de mensajes tienen tamaños incorrectos");
        }
        List<String> messageList = this.buildMessage(messages);
        String message = "";
        for(String word : messageList){
            message += word + " ";
        }
        return message.substring(0, message.length()-1);
    }
    private boolean checkLists(List<List<String>> messages){
        boolean correct = false;

        if (messages.get(0).size() == messages.get(1).size() && messages.get(1).size() == messages.get(2).size()){
            correct = true;
        }

        return correct;
    }
    /**
     * @return
     * @throws MessageException
     */
    private List<String> buildMessage(List<List<String>> messages) throws MessageException{
        List<String> messageList = new ArrayList<String>();

        for (int i = 0; i < messages.get(0).size(); i++) {
            String word = "";
            if (messages.get(0).get(i) != ""){
                word = messages.get(0).get(i);
            }
            else if (messages.get(1).get(i) != ""){
                word = messages.get(1).get(i);
            }
            else if (messages.get(2).get(i) != ""){
                word = messages.get(2).get(i);
            }
            else {
                throw new MessageException("El mensaje no pudo ser completo por falta de una de las palabras");
            }
            messageList.add(word);
            //messageList = Stream.concat(messageList.stream(), message.stream()).distinct().collect(Collectors.toList());
            //messageList = Stream.of(messageList, message).flatMap(Collection::stream).collect(Collectors.toList());
        }
        return messageList;
    }
    /**
     * Funcion que elimina el desfasaje (gap) que hay en los mensajes. Para ello recorro cada uno y veo el string que hay en la misma posición.
     * En caso que los tres sean "" los elimino, de no ser así (en el caso que alguno ya tenga una palabra del mensaje) elimino los vacíos y termino la ejecución.
     * Para terminar tengo que controlar que los tres List<String> tengan el mismo tamaño, sino devuelvo un error.
     * @param messages List<List<String>>
     * @return void
     */
    private List<List<String>> removeGap(List<List<String>> messages){
        List<List<String>> noGapMessages = new ArrayList<List<String>>();
        int minSize = messages.get(0).size();
        
        /**
         * Antes de calcular el gap corroboro que no haya strings vacios en todas las n primeras posiciones de los mensajes... de ser asi las elimino
         * como para que cuando calcule el gap me de bien y no eliminar posiciones vacias que correspondan a un mensaje que se perdio.
         */
        
        for(int i = 0; i<minSize; i++){
            if (messages.get(0).get(0) == "" && messages.get(1).get(0) == "" && messages.get(2).get(0) == ""){
                messages.get(0).remove(0);
                messages.get(1).remove(0);
                messages.get(2).remove(0);
            }
            else {
                i = minSize;
            }
        }

        for (List<String> listMessaje : messages) {
            if (listMessaje.size() < minSize){
                minSize = listMessaje.size();
            }
        }
        /**
         * Una vez que elimine todas las posiciones vacias de las primeras y mismas posiciones de los lists de mensajes, elimino los gaps.
         * Para esto hago una sublist desde el tamaño del mensaje menos el tamaño minimo (que va a ser el total de un mensaje completo) hasta el final.
         * De esta manera corro todos los mensajes desfasados que tenga la lista sin importar la cantidad que sean.
         */
        for (List<String> listMessaje : messages) {
            if (listMessaje.size()>minSize){
                listMessaje=listMessaje.subList(listMessaje.size()-minSize, listMessaje.size());
            }
            noGapMessages.add(listMessaje);
        }
        
        return noGapMessages;
    }
}