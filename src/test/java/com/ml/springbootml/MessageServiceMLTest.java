package com.ml.springbootml;

import com.ml.springbootml.exceptions.MessageException;
import com.ml.springbootml.services.MessageService;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MessageServiceMLTest {
    @Autowired
    MessageService messageServiceTest;

    @Test
	public void testGetMessageCorrect () throws MessageException{
		List<List<String>> messages = new ArrayList<List<String>>();
		List<String> message1 = new ArrayList<String>(Arrays.asList("", "", "este", "es", "", "", ""));
        List<String> message2 = new ArrayList<String>(Arrays.asList("", "este", "", "un", "mensaje", ""));
        List<String> message3 = new ArrayList<String>(Arrays.asList("", "", "", "", "", "", "secreto"));

        messages.add(message1);
        messages.add(message2);
        messages.add(message3);

        String messageExpected = "este es un mensaje secreto";
        String messageReceived = messageServiceTest.getMessage(messages);
        assertEquals(messageExpected, messageReceived);
	}
    @Test
	public void testGetMessage3SatNoMsg (){
		List<List<String>> messages = new ArrayList<List<String>>();
		List<String> message1 = new ArrayList<String>(Arrays.asList("", "", "este", "es", "", "", ""));
        List<String> message2 = new ArrayList<String>(Arrays.asList("", "este", "", "", "mensaje", ""));
        List<String> message3 = new ArrayList<String>(Arrays.asList("", "", "", "", "", "", "secreto"));

        messages.add(message1);
        messages.add(message2);
        messages.add(message3);

        try {
            String messageReceived = messageServiceTest.getMessage(messages);
        } catch (MessageException e) {
            assertEquals("El mensaje no pudo ser completo por falta de una de las palabras", e.getMessage());
        }
	}
    @Test
	public void testGetMessage2Sat () {
		List<List<String>> messages = new ArrayList<List<String>>();
		List<String> message1 = new ArrayList<String>(Arrays.asList("", "", "este", "es", "", "", "secreto"));
        List<String> message2 = new ArrayList<String>(Arrays.asList("", "", "este", "", "un", "mensaje", ""));
        
        messages.add(message1);
        messages.add(message2);

        try {
            String messageReceived = messageServiceTest.getMessage(messages);
        } catch (MessageException e) {
            assertEquals("El número de satélites no es correcto, tienen que ser 3", e.getMessage());
        }
	}
    @Test
	public void testGetMessage3SatBadPositions () throws MessageException{
		List<List<String>> messages = new ArrayList<List<String>>();
		List<String> message1 = new ArrayList<String>(Arrays.asList("", "", "este", "es", "", "", ""));
        List<String> message2 = new ArrayList<String>(Arrays.asList("", "este", "", "", "mensaje", "", ""));
        List<String> message3 = new ArrayList<String>(Arrays.asList("", "", "", "", "un", "", "secreto"));

        messages.add(message1);
        messages.add(message2);
        messages.add(message3);

        String messageExpected = "este es un mensaje secreto";
        try {
            String messageReceived = messageServiceTest.getMessage(messages);
        } catch (MessageException e) {
            assertEquals("El mensaje no pudo ser completo por falta de una de las palabras", e.getMessage());
        }
	}
}
