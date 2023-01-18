/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Agentes;

import GUI.GUIFrame;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author LENOVO
 */
public class AgenteEstadistico extends Agent {

    public  GUIFrame UI;

    //Todo agente debe iniciarse con el método setup()
    @Override
    public void setup() {       
        JFrame frame = new JFrame("Recursos");
        UI = new GUIFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        UI.btnBuscar.addActionListener((e) -> {
            if (UI.textRecurso.getText().isEmpty()
                    || (Integer) UI.SpinnerTope.getValue() > 2021 || (Integer) UI.SpinnerInicio.getValue() < 2000) {
                JOptionPane.showMessageDialog(null, "¡Debe completar cada ítem para proceder a la búsqueda y/o llenar correctamente!");
            } else {
                GetResources(
                        UI.textRecurso.getText(), UI.textAutor.getText(), UI.SpinnerInicio.getValue().toString(),
                        UI.SpinnerTope.getValue().toString(), UI.buttonGroup1.getSelection().getActionCommand()
                );
                JOptionPane.showMessageDialog(null, "¡Búsqueda realizada con éxito!");
            }
        });

        UI.pack();
        UI.setVisible(true);

        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                ACLMessage msg = receive();

                if (msg != null) {
                    try {
                        String content = msg.getContent();
                        System.out.println(content);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    block();
                }
            }
        });
    }

    //Método que permite eliminar al agente y liberar recursos
    @Override
    protected void takeDown() {
        try {
            DFService.deregister(this);
        } catch (FIPAException e) {
            e.printStackTrace();
        }
        /*
        Vamos a cerrar la interfaz
         */
        UI.dispose();
        /*
        Mensaje de despedida
         */
        System.out.println("Gracias " + this.getName() + " por interactuar con nuestro sistema de Gestión de Recursos Acedémicos!!!");

    }

    /*
PARÁMETROS A BUSCAR -> nombre del recurso, autor, fecha inicio de búsqueda y final, lenguaje
     */
    public void GetResources(String resources, String author, String yearInit, String yearLast, String language) {

        Map<String, String> map = new HashMap<>();
        map.put("recursos", resources);
        map.put("autor", author);
        map.put("yearInit", yearInit);
        map.put("yearLast", yearLast);
        map.put("language", language);

        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
        msg.addReceiver(new AID("agenterepositorio", AID.ISLOCALNAME));
        msg.setContent(
                map.get("recursos").toString() + "," + map.get("autor").toString() + "," +
                map.get("yearInit").toString() + ","+ 
                map.get("yearLast").toString() + "," + map.get("language").toString()
        );
        send(msg);
    }
}
