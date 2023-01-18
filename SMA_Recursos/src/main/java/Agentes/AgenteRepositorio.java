/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Agentes;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

/**
 *
 * @author LENOVO
 */
public class AgenteRepositorio extends Agent{
    @Override
    protected void setup() {
        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                ACLMessage msg = receive();
                if (msg != null) {
                    
                    String recurso = msg.getContent().split(",", 5)[0].trim();
                    //String autor = msg.getContent().split(",", 5)[1].trim();
                    //String anioInicio = msg.getContent().split(",", 5)[2].trim();
                    //String anioFinal = msg.getContent().split(",", 5)[3].trim();
                    //String lenguaje = msg.getContent().split(",", 5)[4].trim();
                    
                    String URL = "https://serpapi.com/search.json?engine=google_scholar&q="+recurso+"&hl=es&api_key=c1b339cc8366e838747393bf32b2a2f5a7c11256f7221e87a6df657e450bd382";
                    //System.out.println("Recurso: "+recurso +"\n"+"autor: "+autor);
                    
                    ACLMessage msgCla  = new ACLMessage(ACLMessage.INFORM);
                    msgCla.addReceiver(new AID("agenteclasificador2", AID.ISLOCALNAME));
                    msgCla.setContent(URL);
                    send(msgCla);
                } else {
                    block();
                }
            }
        });
    }
}
